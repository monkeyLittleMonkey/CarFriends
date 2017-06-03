package com.hbh.cl.carfriends.lookcar.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hbh.cl.carfriends.MainActivity;
import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.lookcar.adapter.BottomAdapter;
import com.hbh.cl.carfriends.lookcar.adapter.HeaderAdapter;
import com.hbh.cl.carfriends.lookcar.bean.LookCar;
import com.hbh.cl.carfriends.lookcar.presenter.LookCarPresenter;
import com.hbh.cl.carfriends.util.Constant;
import com.hbh.cl.carfriends.util.SpacesItemDecoration;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hbh on 2017/3/3.
 */
@ContentView(R.layout.lookcar_fragment)
public class LookCarFragment extends Fragment implements ILookCarView {

    private static final int UPTATE_VIEWPAGER = 0;

    @ViewInject(R.id.ic_view_pager)
    private ViewPager mViewPager;
    @ViewInject(R.id.ll_hottest_indicator)
    private LinearLayout mLinearLayout;
    @ViewInject(R.id.ll_title)
    private TextView title_txt;
    @ViewInject(R.id.bottom_recycle_view)
    private RecyclerView mRecyclerView;

    private LookCarPresenter mPresenter;
    private HeaderAdapter mAdapter;
    public int mViewPagerHeight;//viewPager控件的高度
    public int mViewPagerWidth;//viewPager控件的宽度
    private ImageView[] mImageViews;//存放轮播图底部小圆点
    private int autoCurrIndex = 0;//设置当前 第几个图片 被选中
    private Timer timer = new Timer();

    //定时轮播图片，需要在主线程里面修改 UI
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPTATE_VIEWPAGER:
                    if (msg.arg1 != 0) {
                        mViewPager.setCurrentItem(msg.arg1);
                    } else {
                        //false 当从末页调到首页时，不显示翻页动画效果，
                        mViewPager.setCurrentItem(msg.arg1, false);
                    }
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return x.view().inject(this, inflater,container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ViewTreeObserver observer = mViewPager.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    mViewPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    mViewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                mViewPagerHeight = mViewPager.getHeight();
                mViewPagerWidth = mViewPager.getWidth();
            }
        });

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        BottomAdapter adapter = new BottomAdapter(this.getActivity(), Constant.ITEM_TITLE);
        adapter.setMyOnClickListener(new BottomAdapter.OnClickListener() {
            @Override
            public void onclick(View view, int pos) {
                Intent intent = new Intent(x.app(), BottomInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", Constant.ITEM_TITLE[pos].split("-")[0]);
                bundle.putString("catids", Constant.ITEM_TITLE[pos].split("-")[1]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(this.getActivity(), R.dimen.divider_height_1));


        mPresenter = new LookCarPresenter(this);
        mPresenter.loadHeaderData();
    }

    @Override
    public void showLoadDialog() {

    }

    @Override
    public void hideLoadDialog() {

    }

    @Override
    public void loadData(final LookCar lookCar) {

        mViewPager.setOffscreenPageLimit(3);
        mAdapter = new HeaderAdapter(this.getActivity(),lookCar, mViewPagerHeight, mViewPagerWidth);
        mViewPager.setAdapter(mAdapter);

        mImageViews = new ImageView[lookCar.Data.get(0).CatList.size()];
        for (int i = 0; i < mImageViews.length; i++) {
            ImageView imageView = new ImageView(this.getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(12,12);
            params.setMargins(6,0,6,0);
            imageView.setLayoutParams(params);
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.icon_cricle_check);
            } else {
                imageView.setBackgroundResource(R.drawable.icon_cricle_uncheck);
            }

            mImageViews[i] = imageView;
            //把指示作用的原点图片加入底部的视图中
            mLinearLayout.addView(mImageViews[i]);
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                title_txt.setText(lookCar.Data.get(0).CatList.get(position).Title);
            }

            @Override
            public void onPageSelected(int position) {
                int total = mImageViews.length;
                for (int j = 0; j < total; j++) {
                    if (j == position) {
                        mImageViews[j].setBackgroundResource(R.drawable.icon_cricle_check);
                    } else {
                        mImageViews[j].setBackgroundResource(R.drawable.icon_cricle_uncheck);
                    }
                }

                //设置全局变量，currentIndex为选中图标的 index
                autoCurrIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 设置自动轮播图片，5s后执行，周期是5s
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = UPTATE_VIEWPAGER;
                if (autoCurrIndex == lookCar.Data.get(0).CatList.size() - 1) {
                    autoCurrIndex = -1;
                }
                message.arg1 = autoCurrIndex + 1;
                mHandler.sendMessage(message);
            }
        }, 5000, 5000);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(x.app(), "数据加载失败...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
