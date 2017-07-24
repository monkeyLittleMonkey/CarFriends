package com.hbh.cl.carfriends.lookcar.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hbh.cl.carfriends.lookcar.bean.LookCar;
import com.hbh.cl.carfriends.lookcar.view.VideoInfoActivity;

/**
 * Created by hbh on 2017/3/9.
 */

public class HeaderAdapter extends PagerAdapter {

    private Context context;
    private LookCar mLookCar;
    private int height;
    private int width;


    public HeaderAdapter(Activity context, LookCar lookCar, int height, int width) {
        this.context = context.getApplicationContext();
        if (lookCar.Data.get(0).CatList == null || lookCar.Data.get(0).CatList.size() == 0) {
            this.mLookCar = new LookCar();
        } else {
            this.mLookCar = lookCar;
        }
        this.height = height;
        this.width = width;

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        Glide.with(context).load(mLookCar.Data.get(0).CatList.get(position).ImageLink)
                .skipMemoryCache(true)
                .centerCrop()
                .override(width,height)
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url",mLookCar.Data.get(0).CatList.get(position).Mp4Link);
                bundle.putString("title", mLookCar.Data.get(0).CatList.get(position).Title);
                bundle.putString("summary", mLookCar.Data.get(0).CatList.get(position).Summary);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mLookCar.Data.get(0).CatList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }
}
