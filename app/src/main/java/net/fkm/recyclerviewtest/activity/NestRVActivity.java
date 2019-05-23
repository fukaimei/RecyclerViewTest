package net.fkm.recyclerviewtest.activity;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import net.fkm.recyclerviewtest.R;
import net.fkm.recyclerviewtest.adapter.MovieTypesAdapter;
import net.fkm.recyclerviewtest.bean.MovieTypesDataModel;
import net.fkm.recyclerviewtest.bean.MovieTypesModel;
import net.fkm.recyclerviewtest.utils.BaseTools;
import net.fkm.recyclerviewtest.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NestRVActivity extends BaseActivity {

    public static NestRVActivity instance;
    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.loading_view_ll)
    LinearLayout loading_view_ll;
    @BindView(R.id.loading_view)
    ImageView mLoadingView;
    @BindView(R.id.rvMovieList)
    RecyclerView rvMovieList;

    private MovieTypesModel movieTypesModel;
    private MovieTypesAdapter adapter;
    private List<MovieTypesDataModel> mList = new ArrayList<>();
    private List<MovieTypesDataModel.MovieTypesContentModel> mListClild;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_nest_view;
    }

    @Override
    protected void initView() {

        ButterKnife.bind(this);
        instance = this;
        mTitleBar.setTitle("嵌套布局样式");
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
        parsingMovieListJson();
    }

    private void parsingMovieListJson() {

        try {
            String jsonData = BaseTools.getAssetsJson(this, "movieTypes.json");
            Gson gson = new Gson();
            movieTypesModel = gson.fromJson(jsonData, MovieTypesModel.class);
            List<MovieTypesDataModel> movieTypesDataModelList = movieTypesModel.getData();
            for (MovieTypesDataModel movieTypesDataModel : movieTypesDataModelList) {
                MovieTypesDataModel data = new MovieTypesDataModel();
                data.setTitle(movieTypesDataModel.getTitle());
                List<MovieTypesDataModel.MovieTypesContentModel> movieTypesContentModelList = movieTypesDataModel.getContent();
                mListClild = new ArrayList<>();
                for (MovieTypesDataModel.MovieTypesContentModel movieTypesContentModel : movieTypesContentModelList) {
                    MovieTypesDataModel.MovieTypesContentModel childData = new MovieTypesDataModel.MovieTypesContentModel();
                    childData.setThumbnail_pic_s(movieTypesContentModel.getThumbnail_pic_s());
                    childData.setName(movieTypesContentModel.getName());
                    childData.setTag(movieTypesContentModel.getTag());
                    mListClild.add(childData);
                }
                data.setContent(mListClild);
                mList.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        rvMovieList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MovieTypesAdapter(this, mList);
        rvMovieList.setAdapter(adapter);

        rvMovieList.setVisibility(View.VISIBLE);
        loading_view_ll.setVisibility(View.GONE);

    }

    // item条目点击时调用的方法
    public void OnClickListener(int position, int tag) {

        final List<MovieTypesDataModel> movieTypesDataModelList = movieTypesModel.getData();
        for (int i = 0; i < movieTypesDataModelList.size(); i++) {
            if (i == position) {
                List<MovieTypesDataModel.MovieTypesContentModel> movieTypesContentModelList = movieTypesDataModelList.get(i).getContent();
                String movieName = movieTypesContentModelList.get(tag).getName();
                ToastUtil.showToast(movieName);
            }
        }
    }

}


