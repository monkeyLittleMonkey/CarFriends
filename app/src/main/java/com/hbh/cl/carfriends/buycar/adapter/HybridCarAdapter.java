package com.hbh.cl.carfriends.buycar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.buycar.bean.CarNews;
import com.hbh.cl.carfriends.buycar.bean.HybridCar;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by hbh on 2017/3/23.
 */

public class HybridCarAdapter extends RecyclerView.Adapter<HybridCarAdapter.MyViewHolder> {

    private Context mContext;
    private HybridCar mHybridCar;
    private LayoutInflater mInflater;

    public HybridCarAdapter(Context context) {
        this.mContext = context.getApplicationContext();
        this.mInflater = LayoutInflater.from(context.getApplicationContext());
    }

    public void setDate(HybridCar hybridCar){
        this.mHybridCar = hybridCar;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.buycar_pager_fragment_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(mContext).load(mHybridCar.Data.get(position).CoverPhoto).into(holder.mImageView);
        holder.name_txt.setText(mHybridCar.Data.get(position).AliasName);
        holder.money_txt.setText(mHybridCar.Data.get(position).DealerPrice);
        holder.time_txt.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        if(mHybridCar == null){
            return 0;
        }
        return mHybridCar.Data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @ViewInject(R.id.buycar_img_view)
        private ImageView mImageView;
        @ViewInject(R.id.name_txt)
        private TextView name_txt;
        @ViewInject(R.id.money_txt)
        private TextView money_txt;
        @ViewInject(R.id.time_txt)
        private TextView time_txt;

        public MyViewHolder(View itemView) {
            super(itemView);
            x.view().inject(this, itemView);
        }
    }
}
