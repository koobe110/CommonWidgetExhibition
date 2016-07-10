package com.along.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 */
public class MyAdapter extends BaseAdapter implements AbsListView.OnScrollListener {
    private int convertViewCount;
    private List<ItemBean> list;
    private LayoutInflater mLayoutInflater;
    private ListViewImageLoader mListViewImageLoader;
    private int startItem, endItem;
    public static String[] URLS;
    private boolean firstFlag;

    public MyAdapter(Context context, List<ItemBean> list, ListView listView) {
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
        mListViewImageLoader = new ListViewImageLoader(listView);
        URLS = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            URLS[i] = list.get(i).imageViewItem;
        }
        //注册对应的事件
        listView.setOnScrollListener(this);
        firstFlag = true;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
//        System.out.println("getview:"+position+" "+convertView);
        if (convertView == null) {
            convertViewCount++;
            convertView = mLayoutInflater.inflate(R.layout.item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageViewItem = (ImageView) convertView.findViewById(R.id.imageViewItem);
            viewHolder.textViewItemName = (TextView) convertView.findViewById(R.id.textViewItemName);
            viewHolder.textViewItemContent = (TextView) convertView.findViewById(R.id.textViewItemContent);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemBean bean = list.get(position);
        viewHolder.imageViewItem.setImageResource(R.mipmap.ic_launcher);
        String url = bean.imageViewItem;
        viewHolder.imageViewItem.setTag(url);
//        new ListViewImageLoader().showImageByThread(viewHolder.imageViewItem,url);
//        mListViewImageLoader.showImageByThread(viewHolder.imageViewItem,url);
        mListViewImageLoader.showImageByAsyncTsk(viewHolder.imageViewItem, url);
//        new ListViewImageLoader().showImageByAsyncTsk(viewHolder.imageViewItem,url);
        viewHolder.textViewItemName.setText(bean.textViewItemName);
        viewHolder.textViewItemContent.setText(bean.textViewItemContent);
        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            //加载图片
            mListViewImageLoader.loadImages(startItem, endItem);
        } else {
            //停止加载
            mListViewImageLoader.cancelAllTasks();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        startItem = firstVisibleItem;
        endItem = startItem + visibleItemCount;
        //跳过最开始还没加载出item的阶段
        if (firstFlag && visibleItemCount > 0) {
            mListViewImageLoader.loadImages(startItem, endItem);
            firstFlag = false;
        }
    }

    static class ViewHolder {
        public ImageView imageViewItem;
        public TextView textViewItemName;
        public TextView textViewItemContent;
    }

}

