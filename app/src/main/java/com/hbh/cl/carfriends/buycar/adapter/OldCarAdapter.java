package com.hbh.cl.carfriends.buycar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.buycar.bean.OldCar;

import java.util.ArrayList;
import java.util.List;

/**
 * 优质二手车 适配器
 */
public class OldCarAdapter extends RecyclerView.Adapter<BaseViewHolder> {

	private Context mContext;
	private List<OldCar.CarListBean> mDataSet;
	private OnScrollToListener onScrollToListener;

	public void setOnScrollToListener(OnScrollToListener onScrollToListener) {
		this.onScrollToListener = onScrollToListener;
	}

	public OldCarAdapter(Context context) {
		mContext = context.getApplicationContext();
		mDataSet = new ArrayList<>();
	}

	@Override
	public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = null;
		switch (viewType) {
			case OldCar.CarListBean.ITEM_TYPE_PARENT:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.item_recycler_parent, parent, false);
				return new OldCarParentViewHolder(mContext, view);
			case OldCar.CarListBean.ITEM_TYPE_CHILD:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.item_recycler_child, parent, false);
				return new OldCarChildViewHolder(mContext, view);
			default:
				view = LayoutInflater.from(mContext).inflate(
						R.layout.item_recycler_parent, parent, false);
				return new OldCarParentViewHolder(mContext, view);
		}
	}

	@Override
	public void onBindViewHolder(BaseViewHolder holder, int position) {
		switch (getItemViewType(position)) {
			case OldCar.CarListBean.ITEM_TYPE_PARENT:
				OldCarParentViewHolder imageViewHolder = (OldCarParentViewHolder) holder;
				imageViewHolder.bindView(mDataSet.get(position), position,
						imageClickListener);
				break;
			case OldCar.CarListBean.ITEM_TYPE_CHILD:
				OldCarChildViewHolder textViewHolder = (OldCarChildViewHolder) holder;
				textViewHolder.bindView(mDataSet.get(position), position);
				break;
			default:
				break;
		}
	}

	private OldCarItemDataClickListener imageClickListener = new OldCarItemDataClickListener() {

		@Override
		public void onExpandChildren(OldCar.CarListBean oldCar) {
			int position = getCurrentPosition(oldCar.UcarID);
			OldCar.CarListBean children = getChildren(oldCar.getChildren(), 0);
			if (children == null) {
				return;
			}
			add(children, position + 1);// 插入到点击点的下方
			if (position == mDataSet.size() - 2 && onScrollToListener != null) {
				onScrollToListener.scrollTo(position + 1);
			}
		}

		@Override
		public void onHideChildren(OldCar.CarListBean oldCar) {
			int position = getCurrentPosition(oldCar.UcarID);
			OldCar.CarListBean children = oldCar.getChildren();
			if (children == null) {
				return;
			}
			removeAll(position + 1, 1);
			if (onScrollToListener != null) {
				onScrollToListener.scrollTo(position);
			}
		}
	};

	@Override
	public int getItemCount() {
		return mDataSet.size();
	}

	public OldCar.CarListBean getChildren(OldCar.CarListBean bean, int type){
		OldCar.CarListBean parent = new OldCar.CarListBean();
		parent.setType(1);
		parent.MainBrandName = bean.MainBrandName;
		parent.BrandName = bean.BrandName;
		parent.CarName = bean.CarName;
		parent.Exhaust = bean.Exhaust;
		parent.CityName = bean.CityName;
		parent.ProvinceName = bean.ProvinceName;
		parent.CarPublishTime = bean.CarPublishTime;
		parent.DisPlayPrice = bean.DisPlayPrice;
		parent.DrivingMileage = bean.DrivingMileage;
		parent.BuyCarDate = bean.BuyCarDate;
		parent.ImageURL = bean.ImageURL;
		parent.Authenticated = bean.Authenticated;
		parent.IsDealerAuthorized = bean.IsDealerAuthorized;
		parent.IsZhiBao = bean.IsZhiBao;
		parent.CarDetailUrl = bean.CarDetailUrl;
		parent.BuyCarDate_New = bean.BuyCarDate_New;
		parent.NewCarPrice = bean.NewCarPrice;
		return parent;
	}

	/**
	 * 从position开始删除，删除
	 *
	 * @param position
	 * @param itemCount
	 *            删除的数目
	 */
	protected void removeAll(int position, int itemCount) {
		for (int i = 0; i < itemCount; i++) {
			mDataSet.remove(position);
		}
		notifyItemRangeRemoved(position, itemCount);
	}

	protected int getCurrentPosition(String uuid) {
		for (int i = 0; i < mDataSet.size(); i++) {
			if (uuid.equalsIgnoreCase(mDataSet.get(i).UcarID)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int getItemViewType(int position) {
		return mDataSet.get(position).getType();
	}

	public void add(OldCar.CarListBean bean, int position) {
		mDataSet.add(position, bean);
		notifyItemInserted(position);
	}

	public void addAll(List<OldCar.CarListBean> list) {
		mDataSet.clear();
		mDataSet.addAll(list);
		notifyDataSetChanged();
	}
}
