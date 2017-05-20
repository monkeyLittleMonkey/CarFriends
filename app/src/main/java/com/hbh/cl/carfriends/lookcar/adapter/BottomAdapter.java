package com.hbh.cl.carfriends.lookcar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbh.cl.carfriends.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by hbh on 2017/3/10.
 */

public class BottomAdapter extends RecyclerView.Adapter<BottomAdapter.MyViewHolder> {

    private Context mContext;
    private String[] itemTitles;
    private LayoutInflater mInflater;
    private OnClickListener mOnClickListener;

    public BottomAdapter(Context context, String[] titles) {
        this.mContext = context;
        this.itemTitles = titles;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lookcar_fragment_bottom_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.mImageView.setBackgroundResource(R.drawable.ic_aboutme);
        holder.mTextView.setText(itemTitles[position].split("-")[0]);
        if(mOnClickListener != null){
            holder.item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.onclick(holder.item_layout, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemTitles.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @ViewInject(R.id.img_view)
        ImageView mImageView;
        @ViewInject(R.id.txt_view)
        TextView mTextView;
        @ViewInject(R.id.item_layout)
        LinearLayout item_layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            x.view().inject(this, itemView);
        }
    }

    public interface OnClickListener{
        void onclick(View view, int pos);
    }

    public void setMyOnClickListener(OnClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }
}
