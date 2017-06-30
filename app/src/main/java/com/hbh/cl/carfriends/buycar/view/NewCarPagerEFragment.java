package com.hbh.cl.carfriends.buycar.view;

import android.content.Intent;
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
import android.widget.Button;

import com.hbh.cl.carfriends.MyApplication;
import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.buycar.adapter.CarNewsAdapter;
import com.hbh.cl.carfriends.buycar.adapter.ECarAdapter;
import com.hbh.cl.carfriends.buycar.adapter.HybridCarAdapter;
import com.hbh.cl.carfriends.buycar.bean.CarNews;
import com.hbh.cl.carfriends.buycar.bean.ECar;
import com.hbh.cl.carfriends.buycar.bean.HybridCar;
import com.hbh.cl.carfriends.buycar.presenter.NewEnergyCarPresenter;
import com.hbh.cl.carfriends.util.SpacesItemDecoration;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by hbh on 2017/3/23.
 * 新能源车 fragment
 */
@ContentView(R.layout.buycar_pager_e_fragment)
public class NewCarPagerEFragment extends Fragment implements IECarView, SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.swipe_refresh_widget)
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @ViewInject(R.id.pager_e_recycle_view)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.electriccars_btn)
    private Button mElectriccars_btn;
    @ViewInject(R.id.carsnews_btn)
    private Button mCarsnews_btn;
    @ViewInject(R.id.hybridcars_btn)
    private Button mHybridcars_btn;
    private int refresf = 0;

    private NewEnergyCarPresenter mNewEnergyCarPresenter;
    private LinearLayoutManager mLayoutManager;
    private CarNewsAdapter mCarNewsAdapter;
    private HybridCarAdapter mHybridCarAdapter;
    private ECarAdapter mECarAdapter;

    public static NewCarPagerEFragment newInstance(int type){
        Bundle bundle = new Bundle();
        NewCarPagerEFragment fragment = new NewCarPagerEFragment();
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

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                mSwipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(this.getActivity(), R.dimen.divider_height_1));

        mCarNewsAdapter = new CarNewsAdapter(x.app());
        mHybridCarAdapter = new HybridCarAdapter(x.app());
        mECarAdapter = new ECarAdapter(x.app());

        mElectriccars_btn.setTextColor(getResources().getColor(R.color.gray));
        mElectriccars_btn.setBackgroundResource(R.drawable.textview_left_normal_style);
        mCarsnews_btn.setTextColor(getResources().getColor(R.color.blue));
        mCarsnews_btn.setBackgroundResource(R.drawable.textview_center_select_style);
        mHybridcars_btn.setTextColor(getResources().getColor(R.color.gray));
        mHybridcars_btn.setBackgroundResource(R.drawable.textview_right_normal_style);
        if(mNewEnergyCarPresenter == null){
            mNewEnergyCarPresenter = new NewEnergyCarPresenter(this);
        }
        mRecyclerView.setAdapter(mCarNewsAdapter);
        onRefresh();
    }

    @Event(value = {R.id.electriccars_btn, R.id.carsnews_btn, R.id.hybridcars_btn}, type = View.OnClickListener.class)
    private void onClick(View v){
        switch (v.getId()){
            case R.id.electriccars_btn:
                mElectriccars_btn.setTextColor(getResources().getColor(R.color.blue));
                mElectriccars_btn.setBackgroundResource(R.drawable.textview_left_select_style);
                mCarsnews_btn.setTextColor(getResources().getColor(R.color.gray));
                mCarsnews_btn.setBackgroundResource(R.drawable.textview_center_normal_style);
                mHybridcars_btn.setTextColor(getResources().getColor(R.color.gray));
                mHybridcars_btn.setBackgroundResource(R.drawable.textview_right_normal_style);
                if(mNewEnergyCarPresenter == null){
                    mNewEnergyCarPresenter = new NewEnergyCarPresenter(this);
                }
                refresf = 1;
                mRecyclerView.setAdapter(mECarAdapter);
                mNewEnergyCarPresenter.loadECarData();
                break;
            case R.id.carsnews_btn:
                mElectriccars_btn.setTextColor(getResources().getColor(R.color.gray));
                mElectriccars_btn.setBackgroundResource(R.drawable.textview_left_normal_style);
                mCarsnews_btn.setTextColor(getResources().getColor(R.color.blue));
                mCarsnews_btn.setBackgroundResource(R.drawable.textview_center_select_style);
                mHybridcars_btn.setTextColor(getResources().getColor(R.color.gray));
                mHybridcars_btn.setBackgroundResource(R.drawable.textview_right_normal_style);
                if(mNewEnergyCarPresenter == null){
                    mNewEnergyCarPresenter = new NewEnergyCarPresenter(this);
                }
                refresf = 0;
                mRecyclerView.setAdapter(mCarNewsAdapter);
                mNewEnergyCarPresenter.loadCarNewsData();
                break;
            case R.id.hybridcars_btn:
                mElectriccars_btn.setTextColor(getResources().getColor(R.color.gray));
                mElectriccars_btn.setBackgroundResource(R.drawable.textview_left_normal_style);
                mCarsnews_btn.setTextColor(getResources().getColor(R.color.gray));
                mCarsnews_btn.setBackgroundResource(R.drawable.textview_center_normal_style);
                mHybridcars_btn.setTextColor(getResources().getColor(R.color.blue));
                mHybridcars_btn.setBackgroundResource(R.drawable.textview_right_select_style);
                if(mNewEnergyCarPresenter == null){
                    mNewEnergyCarPresenter = new NewEnergyCarPresenter(this);
                }
                refresf = 2;
                mRecyclerView.setAdapter(mHybridCarAdapter);
                mNewEnergyCarPresenter.loadHCarData();
                break;
        }
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
    public void loadCarNewsData(final CarNews carNews) {
        mCarNewsAdapter.setDate(carNews);
        mCarNewsAdapter.setOnItemClickListener(new CarNewsAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int pos) {
                Intent intent = new Intent(x.app(), CarDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", carNews.Data.get(pos).filepath);
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                x.app().startActivity(intent);
            }
        });
    }

    @Override
    public void loadHybridCarData(HybridCar hybridCar) {
        mHybridCarAdapter.setDate(hybridCar);
    }

    @Override
    public void loadECarData(ECar eCar) {
        mECarAdapter.setData(eCar);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onRefresh() {
        if (refresf == 0) {
            mNewEnergyCarPresenter.loadCarNewsData();
        }else if(refresf == 1){
            mNewEnergyCarPresenter.loadECarData();
        }else{
            mNewEnergyCarPresenter.loadHCarData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getRefWatcher().watch(this);
    }
}
