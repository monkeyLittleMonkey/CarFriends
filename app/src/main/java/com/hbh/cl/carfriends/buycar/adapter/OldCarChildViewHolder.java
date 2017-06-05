package com.hbh.cl.carfriends.buycar.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.buycar.bean.OldCar;
import com.hbh.cl.carfriends.buycar.view.CarDetailActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.DecimalFormat;

/**
 * 优质二手车 下拉子布局
 */
@ContentView(R.layout.item_recycler_child)
public class OldCarChildViewHolder extends BaseViewHolder {

	private Context mContext;
	@ViewInject(R.id.MainBrandName)
	private TextView MainBrandName;
	@ViewInject(R.id.child_img_layout)
	private LinearLayout childLayout;
	@ViewInject(R.id.childcontainer)
	private RelativeLayout relativeLayout;
	@ViewInject(R.id.Exhaust)
	private TextView Exhaust;
	@ViewInject(R.id.CityName)
	private TextView CityName;
	@ViewInject(R.id.CarPublishTime)
	private TextView CarPublishTime;
	@ViewInject(R.id.BuyCarDate_New)
	private TextView BuyCarDate_New;
	@ViewInject(R.id.DrivingMileage)
	private TextView DrivingMileage;
	@ViewInject(R.id.NewCarPrice)
	private TextView NewCarPrice;
	@ViewInject(R.id.DisPlayPrice)
	private TextView DisPlayPrice;
	@ViewInject(R.id.IsZhiBao)
	private TextView IsZhiBao;
	@ViewInject(R.id.IsDealerAuthorized)
	private TextView IsDealerAuthorized;
	@ViewInject(R.id.more)
	private TextView more;

	public OldCarChildViewHolder(Context context, View itemView) {
		super(itemView);
		this.mContext = context;
		x.view().inject(this, itemView);
	}

	public void bindView(final OldCar.CarListBean oldCar, int position) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		childLayout.removeAllViews();
		for (int i = 0; i < oldCar.ImageURL.split("\\|").length; i++) {
			ImageView imageView = new ImageView(mContext);
			imageView.setLayoutParams(params);
			childLayout.addView(imageView);
			Glide.with(mContext).load(oldCar.ImageURL.split("\\|")[i])
//					.centerCrop()
//					.override(300,200)
					.into(imageView);
		}
		MainBrandName.setText("品牌："+oldCar.MainBrandName + "(" + oldCar.Authenticated +")");
		Exhaust.setText("排量：" + oldCar.Exhaust);
		CityName.setText("所在地：" + oldCar.ProvinceName + "/" + oldCar.CityName);
		CarPublishTime.setText("发布时间："+ oldCar.CarPublishTime);
		DrivingMileage.setText("行驶里程："+oldCar.DrivingMileage + "公里");
		NewCarPrice.setText("新车价格：" + oldCar.NewCarPrice + "万");
		DisPlayPrice.setText(oldCar.DisPlayPrice+"万  (省"+ new DecimalFormat("0.00").format(Double.parseDouble(oldCar.NewCarPrice) - Double.parseDouble(oldCar.DisPlayPrice))+"万)");
		BuyCarDate_New.setText("购车时间：" + oldCar.BuyCarDate_New);
		IsZhiBao.setText(oldCar.IsZhiBao);
		IsDealerAuthorized.setText(oldCar.IsDealerAuthorized);

		more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(x.app(), CarDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("url", oldCar.CarDetailUrl);
				intent.putExtras(bundle);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				x.app().startActivity(intent);
			}
		});
	}

}
