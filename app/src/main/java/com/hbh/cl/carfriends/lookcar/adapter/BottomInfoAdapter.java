package com.hbh.cl.carfriends.lookcar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.lookcar.bean.LookCar;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by hbh on 2017/3/20.
 */

public class BottomInfoAdapter extends RecyclerView.Adapter<BottomInfoAdapter.MyViewHolder> {

    private Context mContext;
    private LookCar mLookCar;
    private LayoutInflater mInflater;
    private OnMyClickListener mMyClickListener;

    public BottomInfoAdapter(Context context, LookCar lookCar) {
        this.mContext = context;
        this.mLookCar = lookCar;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_bottom_info_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv_title.setText(mLookCar.Data.get(0).CatList.get(position).Title);
        Glide.with(mContext).load(mLookCar.Data.get(0).CatList.get(position).ImageLink)
                .placeholder(R.drawable.default_image)
                .into(holder.tv_img);
        holder.tv_summary.setText(mLookCar.Data.get(0).CatList.get(position).Summary);
        if (mMyClickListener != null) {
            holder.relative_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMyClickListener.onClick(holder.relative_layout, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mLookCar.Data.get(0).CatList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @ViewInject(R.id.tv_title)
        private TextView tv_title;
        @ViewInject(R.id.tv_img)
        private ImageView tv_img;
        @ViewInject(R.id.tv_summary)
        private TextView tv_summary;
        @ViewInject(R.id.relative_layout_TV)
        private RelativeLayout relative_layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            x.view().inject(this,itemView);
        }
    }

    public interface OnMyClickListener{
        void onClick(View view, int pos);
    }

    public void setOnClickListener(OnMyClickListener onClickListener){
        this.mMyClickListener = onClickListener;
    }
}
