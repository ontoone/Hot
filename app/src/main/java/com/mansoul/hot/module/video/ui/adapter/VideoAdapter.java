package com.mansoul.hot.module.video.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mansoul.hot.R;
import com.mansoul.hot.http.LoadImage;
import com.mansoul.hot.module.video.model.bean.VideoListBean;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Mansoul on 16/8/29.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {
    private List<VideoListBean.V9LG4B3A0Bean> data;
    private Fragment fragment;


    private LayoutInflater inflater;

    public VideoAdapter(List<VideoListBean.V9LG4B3A0Bean> data, Fragment fragment) {
        this.data = data;
        this.fragment = fragment;
        inflater = LayoutInflater.from(fragment.getActivity());
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_video, parent, false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        String url;

        url = data.get(position).mp4Hd_url;
        if (url == null || url.equals("null")) {
            url = data.get(position).mp4_url;
        }

        boolean setUp = holder.mVideoPlayer.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST,
                data.get(position).title);
        if (setUp) {
            LoadImage.getInstance().display(fragment, data.get(position).cover,
                    holder.mVideoPlayer.thumbImageView);
        }

        LoadImage.getInstance().display(fragment, data.get(position).topicImg,
                holder.mImg);
        holder.mVideoSource.setText(data.get(position).videosource);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class VideoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_player)
        JCVideoPlayerStandard mVideoPlayer;
        @BindView(R.id.topic_img)
        CircleImageView mImg;
        @BindView(R.id.tv_source)
        TextView mVideoSource;

        public VideoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
