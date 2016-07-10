package com.along.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private ListView mListView;
    private static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mListView = (ListView) findViewById(R.id.listView);
        new NewsAsyncTsk().execute(URL);
    }
    /***
     *网络的异步访问
     */
    class NewsAsyncTsk extends AsyncTask<String, Void, List<ItemBean>> {

        @Override
        protected List<ItemBean> doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<ItemBean> itemBeen) {
            super.onPostExecute(itemBeen);
            MyAdapter myAdapter = new MyAdapter(ListViewActivity.this,itemBeen,mListView);
            mListView.setAdapter(myAdapter);
        }
    }

    private List<ItemBean> getJsonData(String url) {
        List<ItemBean> itemBeanList = new ArrayList<>();
        try {
            String jsonString = readStream(new URL(url).openStream());
            JSONObject jsonObject;
            ItemBean itemBeam;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    itemBeam = new ItemBean(jsonObject.getString("picSmall"),jsonObject.getString("name"),jsonObject.getString("description"));
                    itemBeanList.add(itemBeam);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemBeanList;
    }

    private String readStream(InputStream is) {
        InputStreamReader isr;
        String result = "";
        try {
            String line = "";
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
