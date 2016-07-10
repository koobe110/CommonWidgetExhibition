package com.along.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {
    private TextView mTvServiceInfo;

    private HandlerThread mCheckMsgThread;
    private Handler mCheckMsgHandler;
    private boolean isUpdateInfo;

    private static final int MSG_UPDATE_INFO = 0x110;
    private static final String TAG = "HandlerActivity";

    //与UI线程管理的handler
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "主线程" + Thread.currentThread().getId());
            Bundle bundle2 = msg.getData();
            String result = bundle2.getString("大盘指数");
            mTvServiceInfo.setText(Html.fromHtml(result).toString());
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        //创建后台线程
        initBackThread();

        mTvServiceInfo = (TextView) findViewById(R.id.handlerTextView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始查询
        isUpdateInfo = true;
        mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止查询
        isUpdateInfo = false;
        mCheckMsgHandler.removeMessages(MSG_UPDATE_INFO);

    }

    private void initBackThread() {
        mCheckMsgThread = new HandlerThread("check-message-coming");
        mCheckMsgThread.start();
        mCheckMsgHandler = new Handler(mCheckMsgThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                checkForUpdate();
                if (isUpdateInfo) {
                    mCheckMsgHandler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 1000);
                }
            }
        };


    }

    /**
     * 模拟从服务器解析数据
     */
    private void checkForUpdate() {
        try {
            //模拟耗时
            Thread.sleep(1000);
            //耗时后把获得的数据放到msg里
            Log.d(TAG, "耗时线程" + Thread.currentThread().getId());
            Message msg =  mHandler.obtainMessage();
            Bundle bundle1 = new Bundle();
            String result = "实时更新中，当前大盘指数：<font color='red'>%d</font>";
            result = String.format(result, (int) (Math.random() * 3000 + 1000));
            bundle1.putString("大盘指数",Html.fromHtml(result).toString());
            msg.setData(bundle1);
            mHandler.sendMessage(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mCheckMsgThread.quit();
    }
}