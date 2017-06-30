package com.hbh.cl.carfriends.talkcar.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by hbh on 2017/3/6.
 * Í¼Æ¬ä¯ÀÀÆ÷
 */

public class ImageBrowseAdapter extends PagerAdapter {

    PhotoViewAttacher mAttacher;
    private Context context;
    private String[] imagePath;

    public ImageBrowseAdapter(Context context, String[] urls) {
        this.context = context.getApplicationContext();
        this.imagePath = urls;
    }

    @Override
    public int getCount() {
        return imagePath.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        ImageView imageView = new ImageView(context);
        Glide.with(context).load(imagePath[position]).skipMemoryCache(true).into(imageView);
        view.addView(imageView);
        return imageView;
    }
}
