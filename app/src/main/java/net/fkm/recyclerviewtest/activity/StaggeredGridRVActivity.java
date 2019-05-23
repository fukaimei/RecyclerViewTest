package net.fkm.recyclerviewtest.activity;

import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import net.fkm.recyclerviewtest.R;
import net.fkm.recyclerviewtest.adapter.GridLayoutRVAdapter;
import net.fkm.recyclerviewtest.adapter.StaggeredGridRVAdapter;
import net.fkm.recyclerviewtest.bean.MovieBaseModel;
import net.fkm.recyclerviewtest.bean.MovieDataModel;
import net.fkm.recyclerviewtest.bean.OtherBaseModel;
import net.fkm.recyclerviewtest.utils.BaseTools;
import net.fkm.recyclerviewtest.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StaggeredGridRVActivity extends BaseActivity {

    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.loading_view_ll)
    LinearLayout loading_view_ll;
    @BindView(R.id.loading_view)
    ImageView mLoadingView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.rvMovieList)
    RecyclerView rvMovieList;

    private boolean refreshType;
    private int page;
    private int oldListSize;
    private int newListSize;
    private int addListSize;
    private StaggeredGridRVAdapter adapter;

    private List<MovieDataModel> mList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_grid_view;
    }

    @Override
    protected void initView() {

        ButterKnife.bind(this);
        mTitleBar.setTitle("瀑布流布局样式");
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {

            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {
            }

            @Override
            public void onRightClick(View v) {
            }
        });
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();

    }

    @Override
    protected void initData() {

        // 开启自动加载功能（非必须）
        refreshLayout.setEnableAutoLoadMore(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshType = true;
                        page = 1;
                        parsingMovieListJson();
                        refreshLayout.finishRefresh();
                        refreshLayout.resetNoMoreData();//setNoMoreData(false);
                    }
                }, 2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshType = false;
                        if (page > 2) {
                            ToastUtil.showToast("暂无更多的数据啦");
                            // 将不会再次触发加载更多事件
                            refreshLayout.finishLoadMoreWithNoMoreData();
                            return;
                        }
                        parsingMovieListJson();
                        refreshLayout.setEnableLoadMore(true);
                        refreshLayout.finishLoadMore();
                    }
                }, 2000);
            }
        });
        //触发自动刷新
        refreshLayout.autoRefresh();

    }

    private void parsingMovieListJson() {

        try {
            String jsonData = BaseTools.getAssetsJson(this, "movie1" + page + ".json");
            if (refreshType && mList != null) {
                mList.clear();
                oldListSize = 0;
            } else {
                oldListSize = mList.size();
            }
            Gson gson = new Gson();
            MovieBaseModel movieBaseModel = gson.fromJson(jsonData, MovieBaseModel.class);
            List<MovieDataModel> movieDataModelList = movieBaseModel.getData();
            for (MovieDataModel movieDataModel : movieDataModelList) {
                MovieDataModel data = new MovieDataModel();
                data.setDownLoadName(movieDataModel.getDownLoadName());
                data.setDownimgurl(movieDataModel.getDownimgurl());
                data.setDownLoadUrl(movieDataModel.getDownLoadUrl());
                data.setMvdesc(movieDataModel.getMvdesc());
                OtherBaseModel otherModelDesc = gson.fromJson(movieDataModel.getMvdesc(), OtherBaseModel.class);
                List<String> headerList = otherModelDesc.getHeader();
                data.setRegions(headerList.get(4));
                mList.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        newListSize = mList.size();
        addListSize = newListSize - oldListSize;

        if (refreshType) {
            // 设置RecyclerView样式为瀑布流布局，一行显示2列
            rvMovieList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            adapter = new StaggeredGridRVAdapter(this, mList);
            rvMovieList.setAdapter(adapter);
        } else {
            adapter.notifyItemRangeInserted(mList.size() - addListSize, mList.size());
            adapter.notifyItemRangeChanged(mList.size() - addListSize, mList.size());
        }
        page++;

        rvMovieList.setVisibility(View.VISIBLE);
        loading_view_ll.setVisibility(View.GONE);

        adapter.setItemClikListener(new StaggeredGridRVAdapter.OnItemClikListener() {
            @Override
            public void onItemClik(View view, int position) {
                String videoTitle = mList.get(position).getDownLoadName();
                ToastUtil.showToast(videoTitle);
            }

            @Override
            public void onItemLongClik(View view, int position) {

            }
        });

    }

}


