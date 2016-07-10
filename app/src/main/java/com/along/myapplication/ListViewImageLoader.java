package com.along.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/6/2.
 */
public class ListViewImageLoader {
    private ImageView mImageView;
    private String mUrl;
    private LruCache<String, Bitmap> mLruCache;
    static int asyncTaskCount;
    private ListView mListView;
    private Set<ItemAsyncTask> mItemAsyncTaskSet;

    public ListViewImageLoader(ListView listview) {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 2;
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        mListView = listview;
        mItemAsyncTaskSet = new HashSet<>();
    }

    public void addBitmapToCache(String url, Bitmap bitmap) {
        if (getBitmapFromCache(url) == null) {
            mLruCache.put(url, bitmap);
        }
    }

    public Bitmap getBitmapFromCache(String url) {
        return mLruCache.get(url);
    }

//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (mImageView.getTag().equals(mUrlInAsyncTask)) {
//                mImageView.setImageBitmap((Bitmap) msg.obj);
//            }
//        }
//    };

    public void showImageByAsyncTsk(ImageView imageView, String url) {
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {
//            new ItemAsyncTask(url).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    //    public void showImageByThread(ImageView imageView, final String url) {
//        mImageView = imageView;
//        mUrlInAsyncTask = url;
////      从内存中取出Bitmap的缓存
//        Bitmap bitmap =getBitmapFromCache(url);
//        if (bitmap == null) {
//            new Thread() {
//                @Override
//                public void run() {
//                    super.run();
//                    Bitmap bitmap = getBitmapFromURL(url);
//                    Message message = Message.obtain();
//                    message.obj = bitmap;
//                    mHandler.sendMessage(message);
//                }
//            }.start();
//        }else {
//            imageView.setImageBitmap(bitmap);
//        }
//
//    }

    /**
     * 用来加载从start到end的所有图片
     * @param start
     * @param end
     */
    public void loadImages(int start, int end) {
        for (int i = start; i < end; i++) {
            String url = MyAdapter.URLS[i];
            Bitmap bitmap = getBitmapFromCache(url);
            if (bitmap == null) {
                ItemAsyncTask itemAsyncTask = new ItemAsyncTask(url);
                itemAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
                mItemAsyncTaskSet.add(itemAsyncTask);
            } else {
                ImageView imageView = (ImageView) mListView.findViewWithTag(url);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public void cancelAllTasks(){
        if (mItemAsyncTaskSet != null) {
            for (ItemAsyncTask task:mItemAsyncTaskSet){
                task.cancel(true);
            }
        }
    }

    public Bitmap getBitmapFromURL(String urlString) {
        Bitmap bitmap;
        InputStream inputStream = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(inputStream);
            connection.disconnect();
            if (bitmap != null) {
                addBitmapToCache(urlString, bitmap);
            }
            Thread.sleep(500);
            return bitmap;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public class ItemAsyncTask extends AsyncTask<String, Void, Bitmap> {
        ImageView mImageView;
        String mUrlInAsyncTask;

        public ItemAsyncTask(String urlInAsyncTask) {
//            mImageView = imageView;
            mUrlInAsyncTask = urlInAsyncTask;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            asyncTaskCount++;
            Log.d("1", "asyncTaskCount:" + asyncTaskCount);
            mUrlInAsyncTask = params[0];
            Bitmap bitmap = getBitmapFromURL(params[0]);
            if (bitmap != null) {
                addBitmapToCache(params[0], bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = (ImageView) mListView.findViewWithTag(mUrlInAsyncTask);
            if (imageView!=null&&bitmap!=null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
