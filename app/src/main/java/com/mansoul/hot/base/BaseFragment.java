package com.mansoul.hot.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mansoul.hot.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by Mansoul on 16/8/11.
 */
public abstract class BaseFragment extends Fragment {

    private boolean isViewInitiated;
    private boolean isVisibleToUser;
    private boolean isDataInitiated;

    protected Context mContext;
    protected MainActivity mActivity;
    protected boolean isEmpty = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (MainActivity) context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }


    //强制加载数据 prepareFetchData(true)
    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceData) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceData)) {
            ((MainActivity) this.mContext).setToolbar(getToolbarTitle());

            fetchData();
            isDataInitiated = true;
            return true;
        } else {
            if (isDataInitiated) {
                ((MainActivity) this.mContext).setToolbar(getToolbarTitle());
            }
        }
        return false;
    }

    //    public abstract void fetchData();
    public void fetchData() {
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    public abstract int getLayoutId();

    public abstract String getToolbarTitle();

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //加载新闻时，视图销毁重新进入加载数据
        isDataInitiated = false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
        isDataInitiated = false;
        isEmpty = true;
    }
}
