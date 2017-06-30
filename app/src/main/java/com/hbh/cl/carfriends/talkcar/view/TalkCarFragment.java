package com.hbh.cl.carfriends.talkcar.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hbh.cl.carfriends.MyApplication;
import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.talkcar.adapter.TalkCarAdapter;
import com.hbh.cl.carfriends.talkcar.bean.TalkCar;
import com.hbh.cl.carfriends.talkcar.presenter.TalkCarPresenter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by hbh on 2017/3/3.
 */
@ContentView(R.layout.talkcar_fragment)
public class TalkCarFragment extends Fragment implements ITalkCarView, SwipeRefreshLayout.OnRefreshListener{

    @ViewInject(R.id.swipe_refresh_widget)
    private SwipeRefreshLayout mSwipeRefreshWidget;
    @ViewInject(R.id.dialog_frame)
    private FrameLayout dialogFrame;
    @ViewInject(R.id.recycle_view)
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private TalkCarPresenter mPresenter;
    private TalkCarAdapter mAdapter;
    private int pageIndex = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new TalkCarPresenter(this);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshWidget.setOnRefreshListener(this);
        mLinearLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new TalkCarAdapter(this.getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mScrollListener);
        onRefresh();
    }

    @Override
    public void showLoadDialog() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    @Override
    public void hideLoadDialog() {
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void loadData(TalkCar mTalkCar) {
        mAdapter.isShowFooter(true);
        if(mTalkCar == null) {
            mTalkCar = new TalkCar();
        }

        if(pageIndex == 0) {
            mAdapter.setData(mTalkCar);
        } else {
            //如果没有更多数据了,则隐藏footer布局
            if(mTalkCar == null) {
                mAdapter.isShowFooter(false);
            }
            mAdapter.notifyDataSetChanged();
        }
        pageIndex++;
    }

    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;//标记最后一个Item
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            //当不滚动时，且显示为最后的item时
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && mAdapter.isShowFooter()) {
                mPresenter.loadData();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        }
    };

    @Override
    public void showError(String message) {
        Toast.makeText(x.app(), "数据加载失败...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        mPresenter.loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getRefWatcher().watch(this);
    }
}
