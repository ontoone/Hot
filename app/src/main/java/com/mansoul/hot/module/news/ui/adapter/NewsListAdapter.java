package com.mansoul.hot.module.news.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mansoul.hot.R;
import com.mansoul.hot.http.LoadImage;
import com.mansoul.hot.module.news.model.bean.NewsListBean;
import com.mansoul.hot.util.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 新闻列表适配器
 * Created by Mansoul on 16/9/7.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListVH> {

    private LayoutInflater mInflater;
    private ArrayList<NewsListBean.bean> mData = new ArrayList<>();
    private Context mContext;

    public NewsListAdapter(Context context, ArrayList<NewsListBean.bean> mResult) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mData = mResult;

    }

    @Override
    public NewsListVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_news_list, parent, false);
        return new NewsListVH(view);
    }

    @Override
    public void onBindViewHolder(NewsListVH holder, final int position) {
        LoadImage.getInstance().display(mContext, mData.get(position).imgsrc, holder.mNewsImg);
        holder.mNewsTitle.setText(mData.get(position).title);
        holder.mNewsSource.setText(mData.get(position).source);
        holder.mNewsData.setText(mData.get(position).lmodify);

        holder.mDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(mContext, position + "被点击了");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class NewsListVH extends RecyclerView.ViewHolder {
        @BindView(R.id.news_img)
        ImageView mNewsImg;
        @BindView(R.id.news_title)
        TextView mNewsTitle;
        @BindView(R.id.news_source)
        TextView mNewsSource;
        @BindView(R.id.news_data)
        TextView mNewsData;
        @BindView(R.id.news_content)
        View mDetail;

        public NewsListVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
