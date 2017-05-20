package com.hbh.cl.carfriends.buycar.adapter;


import com.hbh.cl.carfriends.buycar.bean.OldCar;

/**
 */
public interface OldCarItemDataClickListener {

	public void onExpandChildren(OldCar.CarListBean oldCar);

	public void onHideChildren(OldCar.CarListBean oldCar);

}
