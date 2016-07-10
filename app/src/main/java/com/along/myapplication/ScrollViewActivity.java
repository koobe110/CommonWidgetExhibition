package com.along.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ScrollViewActivity extends AppCompatActivity implements View.OnTouchListener {
    private ScrollView scrollView;
    private TextView textViewScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_viewctivity);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        textViewScrollView = (TextView)findViewById(R.id.textViewScrollView);
        scrollView.setOnTouchListener(this);
        setTitle("新闻APP读取到底部后，继续读取的效果（ScrollView）");

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                if (scrollView.getChildAt(0).getMeasuredHeight() <= scrollView.getHeight() + scrollView.getScrollY()) {
                    //手指移动到底部
                    Toast.makeText(ScrollViewActivity.this, "已加载到底部", Toast.LENGTH_SHORT).show();
                    textViewScrollView.append("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n");
                }
                break;
            }
        }
        return false;
    }
}

