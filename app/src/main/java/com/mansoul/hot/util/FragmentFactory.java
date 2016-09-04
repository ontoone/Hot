package com.mansoul.hot.util;

import com.mansoul.hot.module.news.ui.NewsFragment;
import com.mansoul.hot.module.about.view.About;
import com.mansoul.hot.module.video.ui.VideoFragment;
import com.mansoul.hot.module.photo.ui.PhotoListViewFragment;
import com.mansoul.hot.base.BaseFragment;

import java.util.HashMap;

/**
 * Created by Mansoul on 16/8/11.
 */
public class FragmentFactory {
    private static HashMap<Integer, BaseFragment> fragments = new HashMap<>();

    public static BaseFragment createFragment(int id) {
        BaseFragment fragment = fragments.get(id);
        if (fragment == null) {
            switch (id) {
                case 0:
                    fragment = new NewsFragment();
                    break;
                case 1:
                    fragment = new PhotoListViewFragment();
                    break;
                case 2:
                    fragment = new VideoFragment();
                    break;
                case 3:
                    fragment = new About();
                    break;
            }
            fragments.put(id, fragment);
        }
        return fragment;
    }
}
