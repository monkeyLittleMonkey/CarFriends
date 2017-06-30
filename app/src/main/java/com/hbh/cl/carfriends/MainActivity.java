package com.hbh.cl.carfriends;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hbh.cl.carfriends.aboutme.view.AboutMeFragment;
import com.hbh.cl.carfriends.buycar.view.BuyCarFragment;
import com.hbh.cl.carfriends.lookcar.view.LookCarFragment;
import com.hbh.cl.carfriends.talkcar.view.TalkCarFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private TalkCarFragment mTalkCarFragment;
    private LookCarFragment mLookCarFragment;
    private BuyCarFragment mBuyCarFragment;
    private AboutMeFragment mAboutMeFragment;
    @ViewInject(R.id.talk_car)
    private RadioButton mTalkCar;
    @ViewInject(R.id.look_car)
    private RadioButton mLookCar;
    @ViewInject(R.id.buy_car)
    private RadioButton mBuyCar;
    @ViewInject(R.id.aboutme)
    private RadioButton mAboutme;
    @ViewInject(R.id.center_frame)
    private FrameLayout centerFrame;
    @ViewInject(R.id.main_title)
    private TextView title;
    private long currenttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mTalkCar.performClick();
    }

    @Event(value = {R.id.talk_car, R.id.look_car, R.id.buy_car, R.id.aboutme}, type = View.OnClickListener.class)
    private void onClick(View v){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        switch (v.getId()){
            case R.id.talk_car:
                setRadioState(mTalkCar);
                mTalkCarFragment = new TalkCarFragment();
                transaction.replace(R.id.center_frame, mTalkCarFragment);
                transaction.commit();
                break;
            case R.id.look_car:
                setRadioState(mLookCar);
                mLookCarFragment = new LookCarFragment();
                transaction.replace(R.id.center_frame, mLookCarFragment);
                transaction.commit();
                break;
            case R.id.buy_car:
                setRadioState(mBuyCar);
                mBuyCarFragment = new BuyCarFragment();
                transaction.replace(R.id.center_frame, mBuyCarFragment);
                transaction.commit();
                break;
            case R.id.aboutme:
                setRadioState(mAboutme);
                mAboutMeFragment = new AboutMeFragment();
                transaction.replace(R.id.center_frame, mAboutMeFragment);
                transaction.commit();
                break;
        }
    }

    private void setRadioState(RadioButton radioButton) {
        if (radioButton == mTalkCar) {
            title.setText(R.string.talkcar);
            mTalkCar.setClickable(false);
            mLookCar.setClickable(true);
            mBuyCar.setClickable(true);
            mAboutme.setClickable(true);
        } else if (radioButton == mLookCar) {
            title.setText(R.string.lookcar);
            mTalkCar.setClickable(true);
            mLookCar.setClickable(false);
            mBuyCar.setClickable(true);
            mAboutme.setClickable(true);
        } else if (radioButton == mBuyCar) {
            title.setText(R.string.buycar);
            mTalkCar.setClickable(true);
            mLookCar.setClickable(true);
            mBuyCar.setClickable(false);
            mAboutme.setClickable(true);
        } else if (radioButton == mAboutme) {
            title.setText(R.string.aboutme);
            mTalkCar.setClickable(true);
            mLookCar.setClickable(true);
            mBuyCar.setClickable(true);
            mAboutme.setClickable(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
        MyApplication.getRefWatcher().watch(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(System.currentTimeMillis() - currenttime > 2000){
                currenttime = System.currentTimeMillis();
                Toast.makeText(MainActivity.this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
            }else{
                this.finish();
            }
        }
        return false;
    }
}
