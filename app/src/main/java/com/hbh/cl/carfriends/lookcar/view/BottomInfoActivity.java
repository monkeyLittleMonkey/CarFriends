package com.hbh.cl.carfriends.lookcar.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.lookcar.adapter.BottomInfoAdapter;
import com.hbh.cl.carfriends.lookcar.bean.LookCar;
import com.hbh.cl.carfriends.lookcar.presenter.LookCarPresenter;
import com.hbh.cl.carfriends.util.SpacesItemDecoration;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by hbh on 2017/3/19.
 * 视频类别列表
 */
@ContentView(R.layout.activity_bottom_info)
public class BottomInfoActivity extends AppCompatActivity implements ILookCarView {

    @ViewInject(R.id.bottom_title)
    private TextView mTextView;
    @ViewInject(R.id.bottom_info_recycle_view)
    private RecyclerView mRecyclerView;

    private LookCarPresenter mPresenter;
    private String id, title;
    private BottomInfoAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        id = getIntent().getStringExtra("catids");
        title = getIntent().getStringExtra("title");
        mPresenter = new LookCarPresenter(this);
        mPresenter.loadBottomData(id);
        mTextView.setText(title);
    }

    @Override
    public void showLoadDialog() {

    }

    @Override
    public void hideLoadDialog() {

    }

    @Override
    public void loadData(final LookCar lookCar) {
        mAdapter = new BottomInfoAdapter(this, lookCar);
        mAdapter.setOnClickListener(new BottomInfoAdapter.OnMyClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Intent intent = new Intent(x.app(), VideoInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url",lookCar.Data.get(0).CatList.get(pos).Mp4Link);
                bundle.putString("title", lookCar.Data.get(0).CatList.get(pos).Title);
                bundle.putString("summary", lookCar.Data.get(0).CatList.get(pos).Summary);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(x.app(),"视频加载失败...",Toast.LENGTH_SHORT).show();
    }
}
