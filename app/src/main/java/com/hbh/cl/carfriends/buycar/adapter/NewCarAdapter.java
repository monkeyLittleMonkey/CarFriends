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
import com.hbh.cl.carfriends.buycar.bean.NewCar;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by hbh on 2017/3/22.
 */

public class NewCarAdapter extends RecyclerView.Adapter<NewCarAdapter.MyViewHolder>{

    private Context mContext;
    private NewCar mNewCar;
    private LayoutInflater mInflater;

    public NewCarAdapter(Context context) {
        this.mContext = context.getApplicationContext();
        this.mInflater = LayoutInflater.from(context.getApplicationContext());
    }

    public void setData(NewCar newCar){
        this.mNewCar = newCar;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.buycar_pager_fragment_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(mContext).load(mNewCar.Data.get(position).Pic)
                .into(holder.img_view);
        holder.name_txt.setText(mNewCar.Data.get(position).ShowName);
        holder.money_txt.setText(mNewCar.Data.get(position).Price);
        holder.time_txt.setText(mNewCar.Data.get(position).MakeDay + "…œ –");
    }

    @Override
    public int getItemCount() {
        if (mNewCar == null) {
            return 0;
        }
        return mNewCar.Data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        @ViewInject(R.id.buycar_img_view)
        private ImageView img_view;
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
