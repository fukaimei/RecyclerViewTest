package net.fkm.recyclerviewtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import net.fkm.recyclerviewtest.R;
import net.fkm.recyclerviewtest.activity.NestRVActivity;
import net.fkm.recyclerviewtest.bean.MovieTypesDataModel;

import java.util.List;

public class MovieTypesChildAdapter extends RecyclerView.Adapter<MovieTypesChildAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieTypesDataModel.MovieTypesContentModel> childList;
    private int mPosition;
    private MovieTypesDataModel.MovieTypesContentModel data;
    private OnItemClikListener mOnItemClikListener;

    public MovieTypesChildAdapter(Context context, List<MovieTypesDataModel.MovieTypesContentModel> childList) {
        this.mContext = context;
        this.childList = childList;
    }

    public MovieTypesChildAdapter(Context context, List<MovieTypesDataModel.MovieTypesContentModel> childList, int position) {
        this.mContext = context;
        this.childList = childList;
        this.mPosition = position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie_grid, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        data = childList.get(position);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.img_default_movie)
                .error(R.drawable.img_default_movie);
        Glide.with(mContext).load(data.getThumbnail_pic_s()).apply(options).into(holder.iv_photo);
        holder.tv_name.setText(data.getName());
        holder.tv_regions.setText(data.getTag());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                NestRVActivity.instance.OnClickListener(mPosition, pos);

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return childList == null ? 0 : childList.size();
    }

    public void setData(List<MovieTypesDataModel.MovieTypesContentModel> childList) {
        this.childList = childList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_photo;
        private TextView tv_name;
        private TextView tv_regions;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_regions = (TextView) itemView.findViewById(R.id.tv_regions);
        }
    }

    public interface OnItemClikListener {
        void onItemClik(View view, int position);

        void onItemLongClik(View view, int position);
    }

    public void setItemClikListener(OnItemClikListener mOnItemClikListener) {
        this.mOnItemClikListener = mOnItemClikListener;
    }

}
