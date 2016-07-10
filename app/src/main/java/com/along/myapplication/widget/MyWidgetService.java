package com.along.myapplication.widget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.along.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyWidgetService extends Service {
    private Timer mTimer;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public MyWidgetService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateViews();
            }
        },0,1000);
    }

    private void updateViews() {
        String time = mSimpleDateFormat.format(new Date());
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.my_widget);
        remoteViews.setTextViewText(R.id.appwidget_text,time);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        ComponentName componentName = new ComponentName(getApplicationContext(),MyWidget.class);
        appWidgetManager.updateAppWidget(componentName,remoteViews);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
