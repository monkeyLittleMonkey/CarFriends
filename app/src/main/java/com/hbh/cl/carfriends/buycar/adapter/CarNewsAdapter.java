package com.hbh.cl.carfriends.buycar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.buycar.bean.CarNews;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by hbh on 2017/3/23.
 */

public class CarNewsAdapter extends RecyclerView.Adapter<CarNewsAdapter.MyViewHolder> {

    private Context mContext;
    private CarNews mCarNews;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    public CarNewsAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDate(CarNews carNews){
        this.mCarNews = carNews;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.buycar_pager_fragment_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Glide.with(mContext).load(mCarNews.Data.get(position).PicCover).into(holder.mImageView);
        holder.name_txt.setText(mCarNews.Data.get(position).title);
        holder.money_txt.setVisibility(View.INVISIBLE);
        holder.time_txt.setText(mCarNews.Data.get(position).publishtime);
        if(mOnItemClickListener != null){
            holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(holder.mLinearLayout, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mCarNews == null) {
            return 0;
        }
        return mCarNews.Data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @ViewInject(R.id.item_linearLayout)
        private LinearLayout mLinearLayout;
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

    public interface OnItemClickListener{
        void onClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }
}
