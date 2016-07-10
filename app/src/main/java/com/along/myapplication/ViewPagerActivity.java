package com.along.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends Activity {
    private List<View> viewList;
    private ViewPager mViewPager;
    private PagerTabStrip pagerTabStrip;
    private List<String> titleList;
    private TextView textViewViewPager2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_pager);
        mViewPager = (ViewPager) findViewById(R.id.ViewPager);
        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        viewList = new ArrayList<>();
        View view1 = View.inflate(this, R.layout.viewpager1, null);
        View view2 = View.inflate(this, R.layout.viewpager2, null);
        View view3 = View.inflate(this, R.layout.viewpager3, null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        titleList = new ArrayList<>();
        titleList.add("底部加载 退出保存");
        titleList.add("SeekBar");
        titleList.add("第三页");
        PagerAdapter viewPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }
        };

        mViewPager.setAdapter(viewPagerAdapter);
    }


}
