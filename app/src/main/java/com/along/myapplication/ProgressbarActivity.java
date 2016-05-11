package com.along.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProgressbarActivity extends Activity implements View.OnClickListener{
    private ArrayAdapter arrayAdapter;
    private Spinner spinner;
    private ProgressBar progressBar;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button6;
    private TextView displayProgress;
    private ProgressDialog proDialog;
    private List<String> cityname = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);
        spinner = (Spinner) findViewById(R.id.spinner);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getData());
        spinner.setAdapter(arrayAdapter);
        button1 = (Button) findViewById(R.id.progress_plus);
        button2 = (Button) findViewById(R.id.progress_minus);
        button3 = (Button) findViewById(R.id.progress_reset);
        button6 = (Button) findViewById(R.id.button6);
        displayProgress = (TextView) findViewById(R.id.textView3);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button6.setOnClickListener(this);
        int first = progressBar.getProgress();
        int second = progressBar.getSecondaryProgress();
        displayProgress.setText("第一进度条："+ first/100 +"%   第二进度条：" + second/100 + "%");

    }
    private List<String> getData(){
        cityname.add("上海");
        cityname.add("北京");
        cityname.add("深圳");
        cityname.add("广州");
        return cityname;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.progress_plus:{
                progressBar.incrementProgressBy(1000);
                progressBar.incrementSecondaryProgressBy(1000);
                break;
            }
            case R.id.progress_minus:{
                progressBar.incrementProgressBy(-1000);
                progressBar.incrementSecondaryProgressBy(-1000);
                break;
            }
            case R.id.progress_reset:{
                progressBar.setProgress(2000);
                progressBar.setSecondaryProgress(5000);
                break;
            }
            case R.id.button6:{
                proDialog = new ProgressDialog(this);
                proDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                proDialog.setTitle("悬浮进度条");
                proDialog.setMax(10000);
                proDialog.incrementProgressBy(9000);
                proDialog.setMessage("我一定尽最大努力学习和工作，不断为部门产出，感谢您辛苦面试我，谢谢！");
                proDialog.setIcon(R.drawable.android_6_0_marshmallow);
                proDialog.setButton(DialogInterface.BUTTON_POSITIVE, "录用该实习生", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ProgressbarActivity.this, "谢谢面试官！",Toast.LENGTH_SHORT).show();
                    }
                });
                proDialog.show();
                break;
            }
        }
        int first = progressBar.getProgress();
        int second = progressBar.getSecondaryProgress();
        displayProgress.setText("第一进度条："+ first/100 +"%   第二进度条：" + second/100 + "%");
    }
}
