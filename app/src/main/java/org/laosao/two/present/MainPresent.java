package org.laosao.two.present;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.laosao.two.MyApplication;
import org.laosao.two.R;
import org.laosao.two.model.Config;
import org.laosao.two.model.OtherUtils;
import org.laosao.two.model.SDCard;
import org.laosao.two.present.base.BasePresent;
import org.laosao.two.view.AboutActivity;
import org.laosao.two.view.AudioActivity;
import org.laosao.two.view.CardActivity;
import org.laosao.two.view.CustomActivity;
import org.laosao.two.view.EmailActivity;
import org.laosao.two.view.FeedBackActivity;
import org.laosao.two.view.FileActivity;
import org.laosao.two.view.HistoryActivity;
import org.laosao.two.view.MainActivity;
import org.laosao.two.view.PictureActivity;
import org.laosao.two.view.SmsActivity;
import org.laosao.two.view.UrlActivity;
import org.laosao.two.view.WifiActivity;
import org.laosao.two.view.scan.ScanActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * @author 赵树豪
 * @version 1.0
 */
public class MainPresent extends BasePresent<MainActivity> {
    public MainPresent(Activity activity, MainActivity view) {
        super(activity, view);
    }

    @Override
    public void granted(String[] permission) {
        switch (permission[0]) {
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                mHandler.postDelayed(mPermissionRunnable, 500);
                break;

            case Manifest.permission.READ_PHONE_STATE:
                loadThirdSdk();
                break;
        }
    }

    @Override
    public void denied(String[] permission) {
        mView.showToast("权限被拒绝，程序退出，请您手动更改权限", Toast.LENGTH_LONG);
        mActivity.finish();
    }

    private void loadThirdSdk() {
        BmobConfig config = new BmobConfig.Builder(mActivity)
                .setConnectTimeout(10)
                .setApplicationId(Config.BMOB_SERVER_APP_ID)
                .setUploadBlockSize(100 * 1024)
                .build();
        Bmob.initialize(config);
//        Bmob.initialize(mActivity,Config.BMOB_SERVER_APP_ID);
        OtherUtils.autoUpdate(Config.UPDATE_AUTO, mActivity);
        SDCard.detectionSDcard();
        Log.d("MainPresent", " load Complete");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    @Override
    public void onStop() {
        super.onStop();
        mView.closeFam();
    }

    private void init() {
        mHandler = new DelayedHandler();
        mAnimRunnable = new Runnable() {
            @Override
            public void run() {
                mView.toOtherActivity(mTarget, null);
            }
        };
        mPermissionRunnable = new Runnable() {
            @Override
            public void run() {
                requestPermission(Manifest.permission.READ_PHONE_STATE);
            }
        };
        //虽然targetApi会区别判断安卓系统，但是整体逻辑为6.0时，仍需要单独判断
        if (MyApplication.VERSION_CODE == Build.VERSION_CODES.M) {
            requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {
            loadThirdSdk();
        }


    }


    @Override
    public void onDestory() {
        super.onDestory();
        mHandler.removeCallbacks(mAnimRunnable);
        mHandler.removeCallbacks(mPermissionRunnable);
        mHandler = null;
        mAnimRunnable = null;
        mPermissionRunnable = null;
        mTarget = null;
    }

    private DelayedHandler mHandler;
    private Runnable mAnimRunnable;
    private Runnable mPermissionRunnable;
    private Class mTarget = null;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabAbout:
                mTarget = AboutActivity.class;
                break;
            case R.id.fabFeedback:
                mTarget = FeedBackActivity.class;
                break;
            case R.id.fabScan:
                mTarget = ScanActivity.class;
                break;
            case R.id.fabHistory:
                mTarget = HistoryActivity.class;
                break;
            case R.id.rpPic:
                mTarget = PictureActivity.class;
                break;
            case R.id.rpFile:
                mTarget = FileActivity.class;
                break;
            case R.id.rpCustomContent:
                mTarget = CustomActivity.class;
                break;
            case R.id.rpCard:
                mTarget = CardActivity.class;
                break;
            case R.id.rpUrl:
                mTarget = UrlActivity.class;
                break;
            case R.id.rpSms:
                mTarget = SmsActivity.class;
                break;
            case R.id.rpEmail:
                mTarget = EmailActivity.class;
                break;
            case R.id.rpWifi:
                mTarget = WifiActivity.class;
                break;
            case R.id.rpAudio:
                mTarget = AudioActivity.class;
                break;
        }
        mHandler.postDelayed(mAnimRunnable, 375);
    }

    class DelayedHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

        }
    }

}
