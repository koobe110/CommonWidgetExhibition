package com.along.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerActivity extends AppCompatActivity {
    private Handler mainHandler;
    private Button handlerButton;
    private TextView handlerTextView;
    private static final String TAG = "HandlerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        handlerButton = (Button) findViewById(R.id.handlerButton);
        handlerTextView = (TextView) findViewById(R.id.handlerTextView);
        setTitle("Handler");
        handlerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRunnable mrunnable = new mRunnable();
                Thread mThread = new Thread(mrunnable);
                mThread.start();
            }
        });
        mainHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Toast.makeText(HandlerActivity.this, "Handler收到了来自子线程的消息", Toast.LENGTH_SHORT).show();
                handlerTextView.setText("文本内容已经被改变");
                super.handleMessage(msg);
            }
        };
    }
    class mRunnable implements Runnable{

        @Override
        public void run() {
            Log.d(TAG, "run: ");
            mainHandler.sendEmptyMessage(0);
        }
    }
}
