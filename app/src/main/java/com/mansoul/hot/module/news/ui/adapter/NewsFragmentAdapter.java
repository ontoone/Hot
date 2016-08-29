package com.mansoul.hot.module.news.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mansoul.hot.module.news.model.bean.MyChannel;
import com.mansoul.hot.module.news.ui.NewsListFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mansoul on 16/8/18.
 */
public class NewsFragmentAdapter extends FragmentPagerAdapter {
    List<MyChannel> channels;

    int id = 1;

    Map<String, Integer> IdsMap = new HashMap<>();

    List<String> preIds = new ArrayList<>();

    public NewsFragmentAdapter(FragmentManager fm, List<MyChannel> channels) {
        super(fm);
        this.channels = channels;
    }

    @Override
    public Fragment getItem(int position) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NewsListFragment.KEY_URL, channels.get(position).getUrl());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return channels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return channels.get(position).getTitle();
    }

    @Override
    public long getItemId(int position) {
        return IdsMap.get(getPageTitle(position));
    }

    @Override
    public int getItemPosition(Object object) {
        NewsListFragment fragment = (NewsListFragment) object;
        int preId = preIds.indexOf(fragment.getUrl());
        int newId = -1;
        int i = 0;
        int size = getCount();
        for (; i < size; i++) {
            if (getPageTitle(i).equals(fragment.getUrl())) {
                newId = i;
                break;
            }
        }
        if (newId != -1 && newId == preId) {
            return POSITION_UNCHANGED;
        }
        if (newId != -1) {
            return newId;
        }
        return POSITION_NONE;
    }

    @Override
    public void notifyDataSetChanged() {
        for (MyChannel info : channels) {
            if (!IdsMap.containsKey(info.getTitle())) {
                IdsMap.put(info.getTitle(), id++);
            }
        }
        super.notifyDataSetChanged();
        preIds.clear();
        int size = getCount();
        for (int i = 0; i < size; i++) {
            preIds.add((String) getPageTitle(i));
        }
    }
}
