package com.along.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonServiceVolume;
    private Button buttonServiceStart;
    private Button buttonServiceStop;
    private Button buttonServiceBind;
    private Button buttonServiceUnbind;
    private Button buttonServiceRun;
    private TextView textViewService;
    private Intent intentService;
    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        setTitle("Service");
        buttonServiceVolume = (Button) findViewById(R.id.buttonServiceVolume);
        buttonServiceStart = (Button) findViewById(R.id.buttonServiceStart);
        buttonServiceStop = (Button) findViewById(R.id.buttonServiceStop);
        buttonServiceBind = (Button) findViewById(R.id.buttonServiceBind);
        buttonServiceUnbind = (Button) findViewById(R.id.buttonServiceUnbind);
        buttonServiceRun = (Button) findViewById(R.id.buttonServiceRun);
        textViewService = (TextView) findViewById(R.id.textViewService);
        buttonServiceVolume.setOnClickListener(this);
        buttonServiceStart.setOnClickListener(this);
        buttonServiceStop.setOnClickListener(this);
        buttonServiceBind.setOnClickListener(this);
        buttonServiceUnbind.setOnClickListener(this);
        buttonServiceRun.setOnClickListener(this);
        intentService = new Intent(this, MyService.class);

    }

    public ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((MyService.mBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonServiceVolume: {
                AudioManager mAudioManager = (AudioManager) this.getSystemService(AUDIO_SERVICE);
                mAudioManager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_RAISE, 5);
                break;
            }
            case R.id.buttonServiceStart: {
                startService(intentService);
                break;
            }
            case R.id.buttonServiceStop: {
                stopService(intentService);
                break;
            }
            case R.id.buttonServiceBind: {
                bindService(intentService, mServiceConnection, BIND_AUTO_CREATE);
                break;

            }
            case R.id.buttonServiceRun: {
                myService.run();
                break;

            }
            case R.id.buttonServiceUnbind: {
                unbindService(mServiceConnection);
                break;

            }
        }
    }
}
