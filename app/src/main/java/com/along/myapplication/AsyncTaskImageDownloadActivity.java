package com.along.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class AsyncTaskImageDownloadActivity extends AppCompatActivity {

    private String url = "http://img.25pp.com/uploadfile/app/icon/20160111/1452486896326370.jpg";
    private ImageView imageViewAsyncTaskImage;
    private ProgressBar progressBarAsyncTaskImage;
    private AsyncTaskImage mTask = new AsyncTaskImage();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_image_download);
        imageViewAsyncTaskImage = (ImageView) findViewById(R.id.imageViewAsyncTaskImage);
        progressBarAsyncTaskImage = (ProgressBar) findViewById(R.id.progressBarAsyncTaskImage);
        mTask.execute(url);
        setTitle("AsyncTask");
    }

    @Override
    protected void onPause() {
        if (mTask!=null&& mTask.getStatus()==AsyncTask.Status.RUNNING) {
            mTask.cancel(true);
        }
        super.onPause();
    }

    class AsyncTaskImage extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarAsyncTaskImage.setVisibility(View.VISIBLE);
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageViewAsyncTaskImage.setImageBitmap(bitmap);
            progressBarAsyncTaskImage.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBarAsyncTaskImage.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            Bitmap bitmap = null;
            URLConnection urlConnection;
            InputStream inputStream;
            try {
                urlConnection = new URL(url).openConnection();
                inputStream = urlConnection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                inputStream.close();
                bufferedInputStream.close();
                for (int i = 0; i < 100; i++) {
                    if (isCancelled()){
                        break;
                    }
                    Thread.sleep(10);
                    publishProgress(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}
