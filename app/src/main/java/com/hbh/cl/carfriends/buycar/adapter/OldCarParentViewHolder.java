package com.hbh.cl.carfriends.buycar.adapter;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.buycar.bean.OldCar;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 优质二手车 下拉父布局
 */
@ContentView(R.layout.item_recycler_parent)
public class OldCarParentViewHolder extends BaseViewHolder {

	private Context mContext;
	@ViewInject(R.id.container)
	private RelativeLayout relativeLayout;
	@ViewInject(R.id.oldcar_parent_brandname)
	private TextView brandname;
	@ViewInject(R.id.expend)
	private ImageView expand;
	@ViewInject(R.id.old_car_img)
	private ImageView oldCarImg;
	@ViewInject(R.id.oldcar_parent_carname)
	private TextView carName;
	@ViewInject(R.id.oldcar_parent_displayprice)
	private TextView displayprice;
	@ViewInject(R.id.oldcar_parent_drivingmileage)
	private TextView drivingmileage;
	@ViewInject(R.id.oldcar_parent_cityname)
	private TextView cityName;
	@ViewInject(R.id.parent_deshed_view)
	private View deshedView;
	@ViewInject(R.id.renzheng)
	private ImageView renzheng;
	@ViewInject(R.id.zhibao)
	private ImageView zhibao;

	public OldCarParentViewHolder(Context context, View itemView) {
		super(itemView);
		this.mContext = context;
		x.view().inject(this, itemView);
	}

	public void bindView(final OldCar.CarListBean oldCar, final int position,
						 final OldCarItemDataClickListener imageClickListener) {
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) expand
				.getLayoutParams();
		expand.setLayoutParams(params);
		Glide.with(mContext).load(oldCar.ImageURL.split("\\|")[0]).into(oldCarImg);
		brandname.setText(oldCar.BrandName);
		carName.setText(oldCar.CarName);
		if(oldCar.Authenticated.contains("非")){
			renzheng.setVisibility(View.GONE);
		}else{
			renzheng.setVisibility(View.VISIBLE);
		}
		if(oldCar.IsZhiBao.contains("非")){
			zhibao.setVisibility(View.GONE);
		}else{
			zhibao.setVisibility(View.VISIBLE);
		}
		displayprice.setText(oldCar.DisPlayPrice + "万");
		drivingmileage.setText(oldCar.BuyCarDate_New + "/" + Double.parseDouble(oldCar.DrivingMileage)/10000 + "万公里");
		cityName.setText(oldCar.CityName);
		if (oldCar.isExpand()) {
			expand.setRotation(90);
			deshedView.setVisibility(View.INVISIBLE);
		} else {
			expand.setRotation(0);
			deshedView.setVisibility(View.VISIBLE);
		}
		relativeLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (imageClickListener != null) {
					if (oldCar.isExpand()) {
						imageClickListener.onHideChildren(oldCar);
						deshedView.setVisibility(View.VISIBLE);
						oldCar.setExpand(false);
						rotationExpandIcon(90, 0);
					} else {
						imageClickListener.onExpandChildren(oldCar);
						deshedView.setVisibility(View.INVISIBLE);
						oldCar.setExpand(true);
						rotationExpandIcon(0, 90);
					}
				}

			}
		});
//		image.setOnLongClickListener(new OnLongClickListener() {
//
//			@Override
//			public boolean onLongClick(View view) {
//				Toast.makeText(view.getContext(), "longclick",
//						Toast.LENGTH_SHORT).show();
//				return false;
//			}
//		});
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void rotationExpandIcon(float from, float to) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
			valueAnimator.setDuration(150);
			valueAnimator.setInterpolator(new DecelerateInterpolator());
			valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

				@Override
				public void onAnimationUpdate(ValueAnimator valueAnimator) {
					expand.setRotation((Float) valueAnimator.getAnimatedValue());
				}
			});
			valueAnimator.start();
		}
	}
}
