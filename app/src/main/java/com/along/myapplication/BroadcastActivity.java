package com.along.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BroadcastActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "BroadcastActivity";
    private Button sentNormalMessage;
    private Button sentOrderdMessage;
    private Button buttonCreadReceiver1;
    private Button buttonCreadReceiver2;
    private TextView textViewBroadcast;
    private BC1 Receiver1;
    private BC2 Receiver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        setTitle("Broadcast");
        sentNormalMessage = (Button) findViewById(R.id.buttonBroadcast);
        sentOrderdMessage = (Button) findViewById(R.id.buttonBroadcast2);
        buttonCreadReceiver1 = (Button) findViewById(R.id.buttonCreadReceiver1);
        buttonCreadReceiver2 = (Button) findViewById(R.id.buttonCreadReceiver2);
        textViewBroadcast = (TextView) findViewById(R.id.textViewBroadcast);
        sentNormalMessage.setOnClickListener(this);
        sentOrderdMessage.setOnClickListener(this);
        buttonCreadReceiver1.setOnClickListener(this);
        buttonCreadReceiver2.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonCreadReceiver1:{
                Receiver1 = new BC1();
                IntentFilter intentFilter= new IntentFilter("MyAction");
                registerReceiver(Receiver1, intentFilter);
                textViewBroadcast.append("动态产生了广播接收者1\n");
                break;
            }
            case R.id.buttonCreadReceiver2:{
                Receiver2 = new BC2();
                IntentFilter intentFilter= new IntentFilter("MyAction");
                registerReceiver(Receiver2, intentFilter);
                textViewBroadcast.append("动态产生了广播接收者2\n");
                break;
            }
            case R.id.buttonBroadcast:{
                Intent intent = new Intent("MyAction");
                sendBroadcast(intent);
                break;

            }
            case R.id.buttonBroadcast2:{
                Intent intent = new Intent("MyAction");
                sendOrderedBroadcast(intent,null);
                break;

            }
        }
    }
    public  class BC1 extends BroadcastReceiver{
        public BC1(){

        }
        @Override
        public void onReceive(Context context, Intent intent) {
            textViewBroadcast.append("动态接受者1，收到广播\n");
            abortBroadcast();
        }
    }
    public  class BC2 extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            textViewBroadcast.append("动态接受者2，收到广播\n");
            abortBroadcast();
        }
    }
}
