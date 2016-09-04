package com.mansoul.hot.module.photo.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.mansoul.hot.R;
import com.mansoul.hot.http.LoadImage;
import com.mansoul.hot.module.photo.model.bean.PhotoListBean;
import com.mansoul.hot.widget.ScaleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Mansoul on 16/8/11.
 */
public class PhotoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;

    private List<PhotoListBean.ResultsBean> results;
    private Context context;
    private LayoutInflater inflater;

    public PhotoListAdapter(List<PhotoListBean.ResultsBean> results, Context context) {
        this.results = results;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (results.get(position) == null)
            return TYPE_FOOTER;
        return TYPE_NORMAL;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;
        View view;
        switch (viewType) {
            case TYPE_NORMAL:
                view = inflater.inflate(R.layout.item_photo_list, parent, false);
                holder = new PhotoListHolder(view);
                break;
            case TYPE_FOOTER:
                view = inflater.inflate(R.layout.footer_view, parent, false);
                holder = new FooterViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof FooterViewHolder) {
            ((FooterViewHolder) holder).pbLoadMore.setVisibility(View.VISIBLE);
        }
        if (holder instanceof PhotoListHolder) {
            ImageView imageView = ((PhotoListHolder) holder).girl;
            LoadImage.getInstance().display(context, results.get(position).url, imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    static class PhotoListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_girl)
        ImageView girl;

        public PhotoListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pb_footer)
        ProgressBar pbLoadMore;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnClickListener listener;

    public interface OnClickListener {
        void onClick(View view, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}
