package com.along.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    private static final String TAG = "MyService";
    public MyService() {
    }

    public class mBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new mBinder();
    }

    @Override
    public void onCreate() {
        Toast.makeText(MyService.this, "onCreate", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(MyService.this, "onStart", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(MyService.this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    public void run(){
        Toast.makeText(MyService.this, "在Service中运行了run函数", Toast.LENGTH_SHORT).show();
    }
}
