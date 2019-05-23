package net.fkm.recyclerviewtest.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.fkm.recyclerviewtest.R;
import net.fkm.recyclerviewtest.bean.MovieTypesDataModel;

import java.util.List;

public class MovieTypesAdapter extends RecyclerView.Adapter<MovieTypesAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieTypesDataModel> mList;
    private MovieTypesDataModel data;
    private OnItemClikListener mOnItemClikListener;

    public MovieTypesAdapter(Context context, List<MovieTypesDataModel> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_types_title, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        data = mList.get(position);
        holder.movieTypesTitle.setText(data.getTitle());

        MovieTypesChildAdapter childAdapter = (MovieTypesChildAdapter) holder.movieTypesChild.getAdapter();
        //适配器复用
        if (childAdapter == null) {
            RecyclerView.LayoutManager manager = new GridLayoutManager(mContext, 3);
            holder.movieTypesChild.setLayoutManager(manager);
            holder.movieTypesChild.setAdapter(new MovieTypesChildAdapter(mContext, data.getContent(), position));
        } else {
            childAdapter.setData(data.getContent()); //重新设置数据
            childAdapter.notifyDataSetChanged();
        }

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
        return mList == null ? 0 : mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView movieTypesTitle;
        private RecyclerView movieTypesChild;

        public ViewHolder(View itemView) {
            super(itemView);
            movieTypesTitle = (TextView) itemView.findViewById(R.id.movieTypesTitle);
            movieTypesChild = (RecyclerView) itemView.findViewById(R.id.movieTypesChild);
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
