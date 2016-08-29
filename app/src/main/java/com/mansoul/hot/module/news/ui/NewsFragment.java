package com.mansoul.hot.module.news.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ActionMenuView;
import android.view.MenuItem;

import com.mansoul.hot.MainActivity;
import com.mansoul.hot.R;
import com.mansoul.hot.base.BaseFragment;
import com.mansoul.hot.module.news.model.bean.MyChannel;
import com.mansoul.hot.module.news.ui.adapter.NewsFragmentAdapter;
import com.mansoul.hot.util.GsonUtil;
import com.mansoul.hot.util.ViewUtil;
import com.trs.channellib.channel.channel.helper.ChannelDataHelper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by Mansoul on 16/8/13.
 */
public class NewsFragment extends BaseFragment implements ChannelDataHelper.ChannelDataRefreshListenter {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private ChannelDataHelper dataHelper;
    private List<MyChannel> mChannels = new ArrayList<>();
    private NewsFragmentAdapter mAdapter;

    private int needShowPosition = -1;
    private TabLayout mTabLayout;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public String getToolbarTitle() {
        return "新闻";
    }

    @Override
    protected void initView() {
        mTabLayout = ((MainActivity) mContext).mTabLayout;
    }

    @Override
    protected void initData() {
        dataHelper = new ChannelDataHelper(mActivity, this, mActivity.mToolbar);
        mActivity.mMenu.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dataHelper.showPop();
                return false;
            }
        });

        mAdapter = new NewsFragmentAdapter(mActivity.supportFragmentManager, mChannels);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
//        ViewUtil.dynamicSetTabLayoutMode(mTabLayout);
        loadData();
    }


    @Override
    public void updateData() {
        loadData();
    }

    @Override
    public void onChannelSeleted(boolean update, int posisiton) {
        if (!update) {
            mViewPager.setCurrentItem(posisiton);
        } else {
            needShowPosition = posisiton;
        }
    }

    private void loadData() {
        Observable.just(getFromRaw())
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, List<MyChannel>>() {
                    @Override
                    public List<MyChannel> call(String data) {
                        List<MyChannel> allData = GsonUtil.jsonToBeanList(data, MyChannel.class);
                        return dataHelper.getShowChannels(allData);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MyChannel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<MyChannel> myChannels) {
                        mChannels.clear();
                        mChannels.addAll(myChannels);

                        mAdapter.notifyDataSetChanged();
                        if (needShowPosition != -1) {
                            mViewPager.setCurrentItem(needShowPosition);
                            needShowPosition = -1;
                        }
                    }
                });
    }

    private String getFromRaw() {
        String result = "";
        try {
            InputStream input = getResources().openRawResource(R.raw.news_channel);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = input.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.close();
            input.close();

            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
