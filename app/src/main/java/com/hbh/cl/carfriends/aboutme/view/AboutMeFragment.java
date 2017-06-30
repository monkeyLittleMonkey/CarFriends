package com.hbh.cl.carfriends.aboutme.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hbh.cl.carfriends.MyApplication;
import com.hbh.cl.carfriends.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by hbh on 2017/3/3.
 * ÎÒµÄ
 */
@ContentView(R.layout.aboutme_fragment)
public class AboutMeFragment extends Fragment {

    @ViewInject(R.id.loginOrUsersname)
    private TextView loginOrUsersname;
    @ViewInject(R.id.lottery_draw)
    private TextView lottery_draw;
    @ViewInject(R.id.about)
    private TextView about;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Event(value = {R.id.loginOrUsersname, R.id.lottery_draw, R.id.about}, type = View.OnClickListener.class)
    private void onClick(View v){
        switch (v.getId()){
            case R.id.loginOrUsersname:

                break;
            case R.id.lottery_draw:
                this.startActivity(new Intent(x.app(), LotteryDrawActivity.class));
                break;
            case R.id.about:
                this.startActivity(new Intent(x.app(), AboutActivity.class));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getRefWatcher().watch(this);
    }
}
