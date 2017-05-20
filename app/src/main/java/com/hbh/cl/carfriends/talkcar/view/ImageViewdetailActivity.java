package com.hbh.cl.carfriends.talkcar.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.talkcar.adapter.ImageBrowseAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by hbh on 2017/3/6.
 */

@ContentView(R.layout.talkcar_activity_img_detail)
public class ImageViewdetailActivity extends AppCompatActivity {

    @ViewInject(R.id.view_pager)
    private ViewPager mViewPager;

    private String[] url;
    private int pos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        Bundle bundle = getIntent().getExtras();
        url = bundle.get("url").toString().split(",");
        pos = Integer.parseInt(bundle.get("pos").toString());
        mViewPager.setOffscreenPageLimit(3);//设置左右两列缓存的数目
        PagerAdapter adapter = new ImageBrowseAdapter(this, url);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(pos);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }

}
