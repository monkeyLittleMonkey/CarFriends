package com.hbh.cl.carfriends.buycar.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.buycar.adapter.NewCarAdapter;
import com.hbh.cl.carfriends.buycar.bean.NewCar;
import com.hbh.cl.carfriends.buycar.presenter.NewCarPresenter;
import com.hbh.cl.carfriends.util.SpacesItemDecoration;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by hbh on 2017/3/22.
 * �����³� fragment
 */
@ContentView(R.layout.buycar_pager_new_fragment)
public class NewCarPagerNewFragment extends Fragment implements INewCarView, SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.swipe_refresh_widget)
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @ViewInject(R.id.pager_new_recycle_view)
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private NewCarPresenter mPresenter;
    private NewCarAdapter mAdapter;

    public static NewCarPagerNewFragment newInstance(int type){
        Bundle bundle = new Bundle();
        NewCarPagerNewFragment fragment = new NewCarPagerNewFragment();
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
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewCarAdapter(x.app());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(4));
        mPresenter = new NewCarPresenter(this);
        onRefresh();
    }

    @Override
    public void showLoadDialog() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoadDialog() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadData(NewCar newCar) {
        mAdapter.setData(newCar);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onRefresh() {
        mPresenter.loadNewCarData();
    }
}