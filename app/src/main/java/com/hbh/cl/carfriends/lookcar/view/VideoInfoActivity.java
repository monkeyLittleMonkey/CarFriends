package com.hbh.cl.carfriends.lookcar.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hbh.cl.carfriends.R;
import com.hbh.cl.carfriends.util.CustomMediaController;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by hbh on 2017/3/17.
 * ��Ƶ������
 */
@ContentView(R.layout.activity_header_info)
public class VideoInfoActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private TextView downloadRateView, loadRateView;
    private ProgressBar pb;
    private CustomMediaController mCustomMediaController;
    private String url;
    private String title;
    private String summary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //����ȫ������
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //��õ�ǰ�������
        Window window = VideoInfoActivity.this.getWindow();
        //���õ�ǰ����Ϊȫ����ʾ
        window.setFlags(flag, flag);
        //���vitamio����Ƿ����
        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }
        x.view().inject(this);

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        summary = getIntent().getStringExtra("summary");

        initView();
    }

    private void initView(){
        pb = (ProgressBar) findViewById(R.id.probar);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);
        mVideoView = (VideoView) findViewById(R.id.buffer);

        mCustomMediaController = new CustomMediaController(this,mVideoView,this);
        mCustomMediaController.setVideoName(title);
        mCustomMediaController.show(5000); //5s����

        //��ʼ�����ؿ��ļ�
        if (Vitamio.isInitialized(this)) {
            mVideoView.setVideoURI(Uri.parse(url));
            mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
            mVideoView.setMediaController(mCustomMediaController);
            mVideoView.setBufferSize(10240); //������Ƶ�����С
            mVideoView.requestFocus();
            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    // optional need Vitamio 4.0
                    mediaPlayer.setPlaybackSpeed(1.0f);
                }
            });

            mVideoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    loadRateView.setText(percent + "%");
                }
            });
            mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    switch (what) {
                        //��ʼ����
                        case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                            if (mVideoView.isPlaying()) {
                                mVideoView.pause();
                                pb.setVisibility(View.VISIBLE);
                                downloadRateView.setText("");
                                loadRateView.setText("");
                                downloadRateView.setVisibility(View.VISIBLE);
                                loadRateView.setVisibility(View.VISIBLE);
                            }
                            break;
                        //�������
                        case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                            mVideoView.start();
                            pb.setVisibility(View.GONE);
                            downloadRateView.setVisibility(View.GONE);
                            loadRateView.setVisibility(View.GONE);
                            break;
                        //���ڻ���
                        case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                            downloadRateView.setText("" + extra + "kb/s" + "  ");
                            break;
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        //ֹͣ��Ƶ���ţ����ͷ���Դ��
        if(mVideoView != null){
            mVideoView.stopPlayback();
        }

        super.onDestroy();
    }
}
