package com.mansoul.hot.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.mansoul.hot.R;
import com.mansoul.hot.util.ViewUtil;

/**
 * 根据状态显示view
 * Created by Mansoul on 16/8/27.
 */
public abstract class LoadingView extends FrameLayout {

    private static final int STATE_LOAD_LOADING = 2;// 正在加载
    private static final int STATE_LOAD_ERROR = 3;// 加载失败
    private static final int STATE_LOAD_EMPTY = 4;// 数据为空
    private static final int STATE_LOAD_SUCCESS = 5;// 加载成功

    private int mCurrentState = STATE_LOAD_LOADING;

    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSuccessView;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        if (mLoadingView == null) {
            mLoadingView = ViewUtil.inflate(context, R.layout.loading_view);
            addView(mLoadingView);
        }
        if (mErrorView == null) {
            mErrorView = ViewUtil.inflate(context, R.layout.error_view);
            addView(mErrorView);
        }
        if (mEmptyView == null) {
            mEmptyView = ViewUtil.inflate(context, R.layout.empty_view);
            addView(mEmptyView);
        }

        showRightView();
    }

    private void showRightView() {
        mLoadingView.setVisibility(mCurrentState == STATE_LOAD_LOADING ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(mCurrentState == STATE_LOAD_ERROR ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(mCurrentState == STATE_LOAD_EMPTY ? View.VISIBLE : View.GONE);

        if (mSuccessView == null && mCurrentState == STATE_LOAD_SUCCESS) {
            mSuccessView = onCreateSuccessView();
            if (mSuccessView != null) {
                addView(mSuccessView);
            }
        }
    }

    public abstract View onCreateSuccessView();
}
