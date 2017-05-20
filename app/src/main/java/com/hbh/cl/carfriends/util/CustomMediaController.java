package com.hbh.cl.carfriends.util;

/**
 * Created by hbh on 2017/3/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbh.cl.carfriends.R;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * �Զ�����Ƶ������
 */
public class CustomMediaController extends MediaController {
    //������ʾ���ڵ���ʾ
    private static final int HIDEFRAM = 0;
    private GestureDetector mGestureDetector;
    //���ذ�ť
    private ImageButton img_back;
    private ImageButton zoom_button;
    //�ļ���
    private TextView mFileName;
    private VideoView videoView;
    private Activity activity;
    private Context context;
    //��Ƶ����
    private String videoname;
    //����mediaController�߶�Ϊ��ʹ����ʱtop��ʾ����Ļ����
    private int controllerWidth = 0;

    private View mVolumeBrightnessLayout;
    //��ʾ����
    private ImageView mOperationBg;
    //��ʾͼƬ
    private TextView mOperationTv;
    //��ʾ����
    private AudioManager mAudioManager;
    //�������
    private int mMaxVolume;
    // ��ǰ����
    private int mVolume = -1;
    //��ǰ����
    private float mBrightness = -1f;

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HIDEFRAM:
                    //������ʾ����
                    mVolumeBrightnessLayout.setVisibility(View.GONE);
                    break;
            }
        }
    };

    //videoview ���ڶ���Ƶ���п��Ƶĵȣ�activityΪ���˳�
    public CustomMediaController(Context context, VideoView videoView, Activity activity) {
        super(context);
        this.context = context;
        this.videoView = videoView;
        this.activity = activity;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        controllerWidth = wm.getDefaultDisplay().getWidth();
        mGestureDetector = new GestureDetector(context, new MyGestureListener());
    }

    @Override
    protected View makeControllerView() {
        //�˴���mymediacontrollerΪ�����Զ���������Ĳ����ļ�����
        View v = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.mymediacontroller, this);
        v.setMinimumHeight(controllerWidth);
        //��ȡ�ؼ�
        img_back = (ImageButton) v.findViewById(R.id.mediacontroller_top_back);
        zoom_button = (ImageButton) v.findViewById(R.id.zoom_button);
        zoom_button.setBackgroundResource(R.drawable.fangda_nor);
        mFileName = (TextView) v.findViewById(R.id.mediacontroller_filename);
        if (mFileName != null) {
            mFileName.setText(videoname);
        }
        mVolumeBrightnessLayout = (RelativeLayout) v.findViewById(R.id.operation_volume_brightness);
        mOperationBg = (ImageView) v.findViewById(R.id.operation_bg);
        mOperationTv = (TextView) v.findViewById(R.id.operation_tv);
        //��������
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //ע���¼�����
        img_back.setOnClickListener(backListener);
        zoom_button.setOnClickListener(zoomListener);
        return v;
    }

    //���ؼ���
    private View.OnClickListener backListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (activity != null) {
                activity.finish();
            }
        }
    };

    private View.OnClickListener zoomListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            videoView.pause();
            Configuration configuration = getResources().getConfiguration();
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {

                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                zoom_button.setBackgroundResource(R.drawable.suoxiao_nor);
                videoView.setVideoLayout(1,0);

            }else {

                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                zoom_button.setBackgroundResource(R.drawable.fangda_nor);
                videoView.setVideoLayout(0,0);
            }
        }
    };

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (videoView != null) {
            videoView.start();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        System.out.println("MYApp-MyMediaController-dispatchKeyEvent");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event))
            return true;
        // �������ƽ���
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                endGesture();
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * ���ƽ���
     */
    private void endGesture() {
        mVolume = -1;
        mBrightness = -1f;
        //����
        myHandler.removeMessages(HIDEFRAM);
        myHandler.sendEmptyMessageDelayed(HIDEFRAM, 500);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        /**
         * ��Ϊʹ�õ����Զ����mediaController ����ʾ��mediaController��������Ļ��
         * ����VideoView�ĵ���¼��ᱻ���أ�������д�������������¼���
         * ��ȫ���Ĳ���ȫ��д�ڿ������У�
         * ��Ϊ����¼������������أ��޷����ݵ��²��VideoView��
         * ����ԭ���ĵ������ػ�ʧЧ����Ϊ���棬
         * �����Ƽ�����onSingleTapConfirmed��������Զ��������/��ʾ��
         *
         * @param e
         * @return
         */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            //�����ƽ����������ǵ�������ʱ������������/��ʾ
            toggleMediaControlsVisiblity();
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        //�����¼�����
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float mOldX = e1.getX(), mOldY = e1.getY();
            int y = (int) e2.getRawY();
            int x = (int) e2.getRawX();
            Display disp = activity.getWindowManager().getDefaultDisplay();
            int windowWidth = disp.getWidth();
            int windowHeight = disp.getHeight();
            if (mOldX > windowWidth * 3.0 / 4.0) {
                //�ұ߻��� ������Ļ�Ҳ�3/4
                onVolumeSlide((mOldY - y) / windowHeight);
            } else if (mOldX < windowWidth * 1.0 / 4.0) {
                //��߻��� ��������Ļ���1/4
                onBrightnessSlide((mOldY - y) / windowHeight);
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            playOrPause();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    /**
     * �����ı�������С
     *
     * @param percent
     */
    private void onVolumeSlide(float percent) {
        if (mVolume == -1) {
            mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (mVolume < 0)
                mVolume = 0;
            // ��ʾ
            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
        }
        int index = (int) (percent * mMaxVolume) + mVolume;
        if (index > mMaxVolume) {
            index = mMaxVolume;
        } else if (index < 0) {
            index = 0;
        }
        //�������
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
        mOperationTv.setText((int) (((double) index / mMaxVolume) * 100) + "%");
        if (index >= 10) {
            mOperationBg.setImageResource(R.drawable.volmn_100);
        } else if (index >= 5 && index < 10) {
            mOperationBg.setImageResource(R.drawable.volmn_60);
        } else if (index > 0 && index < 5) {
            mOperationBg.setImageResource(R.drawable.volmn_30);
        } else {
            mOperationBg.setImageResource(R.drawable.volmn_no);
        }
    }

    /**
     * �����ı�����
     *
     * @param percent
     */
    private void onBrightnessSlide(float percent) {
        if (mBrightness < 0) {
            mBrightness = activity.getWindow().getAttributes().screenBrightness;
            if (mBrightness <= 0.00f)
                mBrightness = 0.50f;
            if (mBrightness < 0.01f)
                mBrightness = 0.01f;
            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
        }
        WindowManager.LayoutParams lpa = activity.getWindow().getAttributes();
        lpa.screenBrightness = mBrightness + percent;
        if (lpa.screenBrightness > 1.0f) {
            lpa.screenBrightness = 1.0f;
        } else if (lpa.screenBrightness < 0.01f) {
            lpa.screenBrightness = 0.01f;
        }
        //�������
        activity.getWindow().setAttributes(lpa);
        mOperationTv.setText((int) (lpa.screenBrightness * 100) + "%");
        if (lpa.screenBrightness * 100 >= 90) {
            mOperationBg.setImageResource(R.drawable.light_100);
        } else if (lpa.screenBrightness * 100 >= 80 && lpa.screenBrightness * 100 < 90) {
            mOperationBg.setImageResource(R.drawable.light_90);
        } else if (lpa.screenBrightness * 100 >= 70 && lpa.screenBrightness * 100 < 80) {
            mOperationBg.setImageResource(R.drawable.light_80);
        } else if (lpa.screenBrightness * 100 >= 60 && lpa.screenBrightness * 100 < 70) {
            mOperationBg.setImageResource(R.drawable.light_70);
        } else if (lpa.screenBrightness * 100 >= 50 && lpa.screenBrightness * 100 < 60) {
            mOperationBg.setImageResource(R.drawable.light_60);
        } else if (lpa.screenBrightness * 100 >= 40 && lpa.screenBrightness * 100 < 50) {
            mOperationBg.setImageResource(R.drawable.light_50);
        } else if (lpa.screenBrightness * 100 >= 30 && lpa.screenBrightness * 100 < 40) {
            mOperationBg.setImageResource(R.drawable.light_40);
        } else if (lpa.screenBrightness * 100 >= 20 && lpa.screenBrightness * 100 < 20) {
            mOperationBg.setImageResource(R.drawable.light_30);
        } else if (lpa.screenBrightness * 100 >= 10 && lpa.screenBrightness * 100 < 20) {
            mOperationBg.setImageResource(R.drawable.light_20);
        }
    }

    /**
     * ������Ƶ�ļ���
     *
     * @param name
     */
    public void setVideoName(String name) {
        videoname = name;
        if (mFileName != null) {
            mFileName.setText(name);
        }
    }

    /**
     * ���ػ���ʾ
     */
    private void toggleMediaControlsVisiblity() {
        if (isShowing()) {
            hide();
        } else {
            show();
        }
    }

    /**
     * ����/��ͣ
     */
    private void playOrPause() {
        if (videoView != null)
            if (videoView.isPlaying()) {
                videoView.pause();
            } else {
                videoView.start();
            }
    }
}
