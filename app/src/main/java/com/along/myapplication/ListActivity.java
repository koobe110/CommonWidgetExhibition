package com.along.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;
    private ListView listView;
    private ArrayAdapter<String> arr_adapter;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> datalist;
    private Button button2to3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        button = (Button) findViewById(R.id.button4);
        editText = (EditText) findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("回传的数据", editText.getText().toString());
                setResult(2, intent);
                finish();
            }
        });
        listView = (ListView) findViewById(R.id.listView);
        datalist = new ArrayList<Map<String, Object>>();
        simpleAdapter = new SimpleAdapter(this, getDatalist(), R.layout.item, new String[]{"dish", "dishDescription"}, new int[]{R.id.dish, R.id.dishDescription});
//        String[] arr_data = {"宝贝1", "宝贝2", "宝贝3"};
//        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_data);
//        listView.setAdapter(arr_adapter);      //给ArrayAdapter的
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = listView.getItemAtPosition(position) + "";
                Toast.makeText(ListActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_FLING:
                        Toast.makeText(ListActivity.this, "我在惯性滑动", Toast.LENGTH_SHORT).show();
                        break;
                    case SCROLL_STATE_IDLE:
                        Toast.makeText(ListActivity.this, "我不滑了", Toast.LENGTH_SHORT).show();
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("dish", R.drawable.huoguo);
                        map.put("dishDescription", "再吃火锅");
                        simpleAdapter.notifyDataSetChanged();
                        datalist.add(map);
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        Toast.makeText(ListActivity.this, "我正在滑动", Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        button2to3 = (Button) findViewById(R.id.button2to3);
        button2to3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2to3 = new Intent(ListActivity.this, MainActivity.class);
                startActivityForResult(intent2to3, 2);
            }
        });
    }

    private List<Map<String, Object>> getDatalist() {
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("dish", R.drawable.guobaorou);
            map.put("dishDescription", (i + 1) + "块锅包肉好吃");
            datalist.add(map);
        }
        return datalist;
    }
}
