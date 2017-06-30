package com.hbh.cl.carfriends.buycar.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hbh.cl.carfriends.MyApplication;
import com.hbh.cl.carfriends.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbh on 2017/3/3.
 */
@ContentView(R.layout.buycar_fragment)
public class BuyCarFragment extends Fragment {

    public static final int CAR_TYPE_NEW = 0;//上市新车
    public static final int CAR_TYPE_E = 1;//新能源车
    public static final int CAR_TYPE_OLD = 2;//优质二手

    @ViewInject(R.id.tab_layout)
    private TabLayout mTablayout;
    @ViewInject(R.id.viewpager)
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewPager.setOffscreenPageLimit(3);
        setupViewPager(mViewPager);
        mTablayout.addTab(mTablayout.newTab().setText(R.string.newcars));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.enewcars));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.oldcars));
        mTablayout.setupWithViewPager(mViewPager);
    }


    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewCarPagerNewFragment.newInstance(CAR_TYPE_NEW), getString(R.string.newcars));
        adapter.addFragment(NewCarPagerEFragment.newInstance(CAR_TYPE_E), getString(R.string.enewcars));
        adapter.addFragment(NewCarPagerOldFragment.newInstance(CAR_TYPE_OLD), getString(R.string.oldcars));
        mViewPager.setAdapter(adapter);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getRefWatcher().watch(this);
    }
}
