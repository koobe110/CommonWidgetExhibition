package com.along.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.along.myapplication.RecyclerView.RecyclerViewActivity;
import com.along.myapplication.animation.AnimMainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private List<Map<String, Object>> datalist;
    private SimpleAdapter sim_Adapter;
    int[] icon_imageNum = new int[]{R.drawable.calendar, R.drawable.address_book, R.drawable.camera, R.drawable.clock,
            R.drawable.games_control, R.drawable.messenger, R.drawable.ringtone, R.drawable.settings, R.drawable.speech_balloon,
            R.drawable.weather, R.drawable.world, R.drawable.picasa, R.drawable.piano, R.drawable.backup, R.drawable.weather_widget, R.drawable.right};
    String[] icon_name = new String[]{"Handler", "Service", "Broadcast", "Content\nProvider",
            "File IO", "ListView\nViewHodler", "ScrollView", "ViewPager", "Fragment",
            "View\nFlipper", "WebView", "AsyncTask", "Animation", "RecyclerView", "WeatherWidget", "Summary"};
    String url = "http://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
        datalist = new ArrayList<Map<String, Object>>();
        sim_Adapter = new SimpleAdapter(this, getdata(), R.layout.gridview, new String[]{"icon_image", "icon_name"}, new int[]{R.id.imageView2, R.id.textView});
        gridView.setAdapter(sim_Adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intentMainToFirstActivity = new Intent(MainActivity.this, HandlerActivity.class);
                    startActivityForResult(intentMainToFirstActivity, 0);
                }
                if (position == 1) {
                    Intent intentMainToFirstActivity = new Intent(MainActivity.this, ServiceActivity.class);
                    startActivityForResult(intentMainToFirstActivity, 1);
                }
                if (position == 2) {
                    Intent intentMainToFirstActivity = new Intent(MainActivity.this, BroadcastActivity.class);
                    startActivityForResult(intentMainToFirstActivity, 2);
                }
                if (position == 3) {
                    Intent intentMainToFirstActivity = new Intent(MainActivity.this, ContentProviderActivity.class);
                    startActivityForResult(intentMainToFirstActivity, 3);
                }
                if (position == 4) {
                    Intent intentMainToFirstActivity = new Intent(MainActivity.this, FileActivity.class);
                    startActivityForResult(intentMainToFirstActivity, 4);
                }
                if (position == 5) {
                    Intent intentMainToFirstActivity = new Intent(MainActivity.this, ListViewActivity.class);
                    startActivityForResult(intentMainToFirstActivity, 5);
                }
                if (position == 6) {
                    Intent intentMainToFirstActivity = new Intent(MainActivity.this, ScrollViewActivity.class);
                    startActivityForResult(intentMainToFirstActivity, 6);
                }
                if (position == 7) {
                    Intent intent3toFragmentNavigation = new Intent(MainActivity.this, ViewPagerActivity.class);
                    startActivityForResult(intent3toFragmentNavigation, 8);
                }
                if (position == 8) {
                    Intent intent3toFragmentNavigation = new Intent(MainActivity.this, FragmentNavigationActivity.class);
                    startActivityForResult(intent3toFragmentNavigation, 8);
                }
                if (position == 9) {
                    Intent intent3toFragmentNavigation = new Intent(MainActivity.this, ViewFlipperActivity.class);
                    startActivityForResult(intent3toFragmentNavigation, 8);
                }
                if (position == 10) {
                    Intent intent3toWebView = new Intent(MainActivity.this, web.class);
                    startActivityForResult(intent3toWebView, 10);
                }
                if (position == 11) {
                    Intent intent3to4 = new Intent(MainActivity.this, AsyncTaskImageDownloadActivity.class);
                    startActivityForResult(intent3to4, 11);
                }
                if (position == 12) {
                    Intent intent3to4 = new Intent(MainActivity.this, AnimMainActivity.class);
                    startActivityForResult(intent3to4, 12);
                }
                if (position == 13) {
                    Intent intent3to4 = new Intent(MainActivity.this, RecyclerViewActivity.class);
                    startActivityForResult(intent3to4, 13);
                }
                if (position == 15) {
                    Intent intent3to4 = new Intent(MainActivity.this, ProgressbarActivity.class);
                    startActivityForResult(intent3to4, 14);
                }
            }
        });
    }

    public List<Map<String, Object>> getdata() {
        for (int i = 0; i < icon_imageNum.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("icon_image", icon_imageNum[i]);
            map.put("icon_name", icon_name[i]);
            datalist.add(map);
        }
        return datalist;
    }
}
