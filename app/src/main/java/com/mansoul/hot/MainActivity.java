package com.mansoul.hot;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.mansoul.hot.base.BaseFragment;
import com.mansoul.hot.util.FragmentFactory;
import com.mansoul.hot.widget.ScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.swipeback.SwipeBackActivity;

public class MainActivity extends SwipeBackActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.vp_main)
    ScrollViewPager mViewPager;
    @BindView(R.id.tabs)
    public TabLayout mTabLayout;
    @BindView(R.id.action_menu_view)
    public ActionMenuView mMenu;

    private ArrayList<BaseFragment> mFragments;
    private long exitTime = 0;
    int mCurrentItem = 0; //当前viewpager的索引
    boolean isFirst = true;

    public FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSwipeBackEnable(false);

        getMenuInflater().inflate(R.menu.main, mMenu.getMenu());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isFirst) {
            init();
            isFirst = false;
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main,menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - exitTime) > 2000) {
//                ToastUtil.showShort(this, "在按一次退出程序");
                Snackbar.make(mDrawer, "在按一次退出程序", Snackbar.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        mMenu.getMenu().clear();
        if (id == R.id.nav_news) {
            mTabLayout.setVisibility(View.VISIBLE);
            getMenuInflater().inflate(R.menu.main, mMenu.getMenu());
            mCurrentItem = 0;
        } else if (id == R.id.nav_photo) {
            mTabLayout.setVisibility(View.GONE);
            mCurrentItem = 1;
        } else if (id == R.id.nav_video) {
            mTabLayout.setVisibility(View.GONE);
            mCurrentItem = 2;
        } else if (id == R.id.nav_settings) {
            mTabLayout.setVisibility(View.GONE);
            mCurrentItem = 3;
        }
        mViewPager.setCurrentItem(mCurrentItem, false);
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////

    private void init() {
        setToolbar("新闻");

        mFragments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mFragments.add(FragmentFactory.createFragment(i));
        }

        supportFragmentManager = getSupportFragmentManager();
        mViewPager.setOffscreenPageLimit(3);
        MyAdapter adapter = new MyAdapter(supportFragmentManager);
        mViewPager.setAdapter(adapter);

    }

    public void setToolbar(String title) {
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(mToggle);
        mToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    //    public abstract int getContentId();
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
