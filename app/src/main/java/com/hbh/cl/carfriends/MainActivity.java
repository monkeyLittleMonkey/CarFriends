package com.hbh.cl.carfriends;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hbh.cl.carfriends.aboutme.view.AboutMeFragment;
import com.hbh.cl.carfriends.buycar.view.BuyCarFragment;
import com.hbh.cl.carfriends.homepage.view.HomePageFragment;
import com.hbh.cl.carfriends.lookcar.view.LookCarFragment;
import com.hbh.cl.carfriends.talkcar.view.TalkCarFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;
    private HomePageFragment mHomePageFragment;
    private TalkCarFragment mTalkCarFragment;
    private LookCarFragment mLookCarFragment;
    private BuyCarFragment mBuyCarFragment;
    private AboutMeFragment mAboutMeFragment;
    @ViewInject(R.id.homePage)
    private RadioButton mHomePage;
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
    private long currenttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){//4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        mHomePage.performClick();
    }

    @Event(value = {R.id.homePage, R.id.talk_car, R.id.look_car, R.id.buy_car, R.id.aboutme}, type = View.OnClickListener.class)
    private void onClick(View v){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        switch (v.getId()){
            case R.id.homePage:
                setRadioState(mHomePage);
                mHomePageFragment = new HomePageFragment();
                transaction.replace(R.id.center_frame, mHomePageFragment);
                transaction.commit();
                toolbar.setVisibility(View.VISIBLE);
                break;
            case R.id.talk_car:
                setRadioState(mTalkCar);
                mTalkCarFragment = new TalkCarFragment();
                transaction.replace(R.id.center_frame, mTalkCarFragment);
                transaction.commit();
                toolbar.setVisibility(View.INVISIBLE);
                break;
            case R.id.look_car:
                setRadioState(mLookCar);
                mLookCarFragment = new LookCarFragment();
                transaction.replace(R.id.center_frame, mLookCarFragment);
                transaction.commit();
                toolbar.setVisibility(View.INVISIBLE);
                break;
            case R.id.buy_car:
                setRadioState(mBuyCar);
                mBuyCarFragment = new BuyCarFragment();
                transaction.replace(R.id.center_frame, mBuyCarFragment);
                transaction.commit();
                toolbar.setVisibility(View.VISIBLE);
                break;
            case R.id.aboutme:
                setRadioState(mAboutme);
                mAboutMeFragment = new AboutMeFragment();
                transaction.replace(R.id.center_frame, mAboutMeFragment);
                transaction.commit();
                toolbar.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void setRadioState(RadioButton radioButton) {
        if (radioButton == mHomePage) {
            mHomePage.setClickable(false);
            mTalkCar.setClickable(true);
            mLookCar.setClickable(true);
            mBuyCar.setClickable(true);
            mAboutme.setClickable(true);
        } else if (radioButton == mTalkCar) {
            mHomePage.setClickable(true);
            mTalkCar.setClickable(false);
            mLookCar.setClickable(true);
            mBuyCar.setClickable(true);
            mAboutme.setClickable(true);
        } else if (radioButton == mLookCar) {
            mHomePage.setClickable(true);
            mTalkCar.setClickable(true);
            mLookCar.setClickable(false);
            mBuyCar.setClickable(true);
            mAboutme.setClickable(true);
        } else if (radioButton == mBuyCar) {
            mHomePage.setClickable(true);
            mTalkCar.setClickable(true);
            mLookCar.setClickable(true);
            mBuyCar.setClickable(false);
            mAboutme.setClickable(true);
        } else if (radioButton == mAboutme) {
            mHomePage.setClickable(true);
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
