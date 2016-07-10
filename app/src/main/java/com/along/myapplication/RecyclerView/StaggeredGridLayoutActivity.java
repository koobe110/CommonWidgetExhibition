package com.along.myapplication.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.along.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class StaggeredGridLayoutActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredViewAdapter mStaggeredViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initDatas();
        initViews();
        mStaggeredViewAdapter = new StaggeredViewAdapter(this, mDatas);
        mRecyclerView.setAdapter(mStaggeredViewAdapter);
        //设置布局管理
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add: {
                mStaggeredViewAdapter.addItem();
                break;
            }
            case R.id.delete: {
                mStaggeredViewAdapter.deleteItem();
                break;

            }
            case R.id.listView: {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            }
            case R.id.gridView: {
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
                break;
            }
            case R.id.staggeredView: {
                Intent intent = new Intent(this, StaggeredGridLayoutActivity.class);
                startActivity(intent);
                break;
            }
        }
        return true;
    }
}

class StaggeredViewAdapter extends RecyclerView.Adapter<StaggeredViewAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private List<String> mDatas2;
    private List<Integer> mHeight;

    public StaggeredViewAdapter(Context context, List<String> datas) {
        this.context = context;
        mDatas2 = datas;
        mInflater = LayoutInflater.from(context);
        mHeight = new ArrayList<Integer>();
        for (int i = 0; i < mDatas2.size(); i++) {
            mHeight.add((int) (100 + Math.random() * 300));
        }
    }

    public void addItem() {
        mDatas2.add(1, "Insert One");
        notifyItemInserted(1);
    }

    public void deleteItem() {
        mDatas2.remove(1);
        notifyItemRemoved(1);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_view_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeight.get(position);
        holder.itemView.setLayoutParams(layoutParams);
        holder.mTextView.setText(mDatas2.get(position));
    }


    @Override
    public int getItemCount() {
        return mDatas2.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_recycle_view);
        }
    }

}



