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
import net.fkm.recyclerviewtest.bean.MovieDataModel;

import java.util.List;

public class GridLayoutRVAdapter extends RecyclerView.Adapter<GridLayoutRVAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieDataModel> mList;
    private MovieDataModel data;
    private OnItemClikListener mOnItemClikListener;

    public GridLayoutRVAdapter(Context context, List<MovieDataModel> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie_grid, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        data = mList.get(position);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.img_default_movie)
                .error(R.drawable.img_default_movie);
        Glide.with(mContext).load(data.getDownimgurl()).apply(options).into(holder.iv_photo);
        holder.tv_name.setText(data.getDownLoadName());
        holder.tv_regions.setText(data.getRegions());

        if (mOnItemClikListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClikListener.onItemClik(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClikListener.onItemLongClik(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
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
