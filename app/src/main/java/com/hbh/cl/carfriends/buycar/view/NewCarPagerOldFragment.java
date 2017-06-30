package com.hbh.cl.carfriends.buycar.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hbh.cl.carfriends.MyApplication;
import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.buycar.adapter.OldCarAdapter;
import com.hbh.cl.carfriends.buycar.adapter.OnScrollToListener;
import com.hbh.cl.carfriends.buycar.bean.OldCar;
import com.hbh.cl.carfriends.buycar.presenter.OldCarPresenter;
import com.hbh.cl.carfriends.util.SpacesItemDecoration;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by hbh on 2017/3/24.
 * 优质二手车
 */
@ContentView(R.layout.buycar_pager_old_fragment)
public class NewCarPagerOldFragment extends Fragment implements IOldCarView, SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.swipe_refresh_widget)
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @ViewInject(R.id.pager_old_recycle_view)
    private RecyclerView mRecyclerView;
    private OldCarAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private OldCarPresenter mPresenter;

    public static NewCarPagerOldFragment newInstance(int type){
        Bundle bundle = new Bundle();
        NewCarPagerOldFragment fragment = new NewCarPagerOldFragment();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mLayoutManager = new LinearLayoutManager(x.app());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new OldCarAdapter(x.app());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnScrollToListener(new OnScrollToListener() {

            @Override
            public void scrollTo(int position) {
                mRecyclerView.scrollToPosition(position);
            }
        });

        mPresenter = new OldCarPresenter(this);
        onRefresh();
    }

    @Override
    public void LoadOldCarData(OldCar oldCar) {

        mAdapter.addAll(oldCar.CarList);
    }

    @Override
    public void LoadError(String message) {

    }

    @Override
    public void showLoadDialog() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void HideLoadDialog() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadOldCarData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getRefWatcher().watch(this);
    }
}
