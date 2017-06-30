package com.hbh.cl.carfriends.talkcar.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.talkcar.bean.TalkCar;
import com.hbh.cl.carfriends.talkcar.view.ImageViewDetailActivity;
import com.hbh.cl.carfriends.util.CircleTransform;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by hbh on 2017/3/4.
 */

public class TalkCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private TalkCar mTalkCar;
    private LayoutInflater mInflater;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private boolean mShowFooter = true;

    public TalkCarAdapter(Context context) {
        this.mContext = context.getApplicationContext();
        mInflater = LayoutInflater.from(context.getApplicationContext());
    }

    public void setData(TalkCar talkCar){
        this.mTalkCar = talkCar;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.talkcar_fragment_item, parent, false);
            MyViewHolder holder = new MyViewHolder(v);
            return holder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.load_footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof MyViewHolder){
            if (mTalkCar.Data.List.get(position) == null) {
                return;
            }
            Glide.with(mContext).load(mTalkCar.Data.List.get(position).UserAvatar)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .transform(new CircleTransform(mContext))
                    .placeholder(R.drawable.ic_fridents_default_per)
                    .into(((MyViewHolder)holder).useravatar);

            ((MyViewHolder)holder).username.setText(mTalkCar.Data.List.get(position).UserName);
            ((MyViewHolder)holder).update_time.setText(mTalkCar.Data.List.get(position).Uptime);

            if (mTalkCar.Data.List.get(position).Title.equals("")) {
                ((MyViewHolder)holder).title_txt.setVisibility(View.GONE);
            }else{
                ((MyViewHolder)holder).title_txt.setVisibility(View.VISIBLE);
                ((MyViewHolder)holder).title_txt.setText(mTalkCar.Data.List.get(position).Title);
            }

            if(mTalkCar.Data.List.get(position).ImageList.equals("")){
                ((MyViewHolder)holder).image_list_layout.setVisibility(View.GONE);
            }else{
                ((MyViewHolder)holder).image_list_layout.setVisibility(View.VISIBLE);

                if (((MyViewHolder)holder).image_list_layout.getChildCount() > 0) {
                    ((MyViewHolder)holder).image_list_layout.removeAllViews();
                }

                for (int i = 0; i < mTalkCar.Data.List.get(position).ImageList.split(",").length; i++) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setLayoutParams(new RecyclerView.LayoutParams(200, 150));
                    ((MyViewHolder) holder).image_list_layout.addView(imageView);
                    Glide.with(mContext).load(mTalkCar.Data.List.get(position).ImageList.split(",")[i])
                            .centerCrop()
                            .skipMemoryCache(true)
                            .override(100,100)
                            .crossFade(1000)
                            .into(imageView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, ImageViewDetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("url", mTalkCar.Data.List.get(position).ImageList);
                            bundle.putInt("pos", finalI);
                            intent.putExtras(bundle);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        }
                    });
                }
            }

            ((MyViewHolder)holder).summary_txt.setText(mTalkCar.Data.List.get(position).Summary.trim());
            ((MyViewHolder)holder).placename.setText(mTalkCar.Data.List.get(position).PlaceName.trim());
            ((MyViewHolder)holder).likecount.setText(mTalkCar.Data.List.get(position).LikeCount+"");
            ((MyViewHolder)holder).replycount.setText(mTalkCar.Data.List.get(position).ReplyCount+"");
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @ViewInject(R.id.useravatar)
        private ImageView useravatar;
        @ViewInject(R.id.username)
        private TextView username;
        @ViewInject(R.id.update_time)
        private TextView update_time;
        @ViewInject(R.id.title_txt)
        private TextView title_txt;
        @ViewInject(R.id.summary_txt)
        private TextView summary_txt;
        @ViewInject(R.id.image_list_layout)
        private LinearLayout image_list_layout;
        @ViewInject(R.id.placename)
        private TextView placename;
        @ViewInject(R.id.likecount)
        private TextView likecount;
        @ViewInject(R.id.replycount)
        private TextView replycount;

        public MyViewHolder(View itemView) {
            super(itemView);
            x.view().inject(this, itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(!mShowFooter) {
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        int begin = mShowFooter?1:0;
        if(mTalkCar == null) {
            return begin;
        }
        return mTalkCar.Data.List.size() + begin;
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }
}
