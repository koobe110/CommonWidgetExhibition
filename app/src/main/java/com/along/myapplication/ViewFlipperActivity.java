package com.along.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private int[] resId = {R.drawable.meinv1, R.drawable.meinv2, R.drawable.meinv3};
    private float startX;
    private float endX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        setTitle("ViewFlipper");
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        for (int i = 0; i < resId.length; i++) {
            viewFlipper.addView(getImageView(resId[i]));
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setInAnimation(this,android.R.anim.fade_in);
        viewFlipper.setOutAnimation(this,android.R.anim.fade_out);
        viewFlipper.startFlipping();
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startX = event.getX();
                break;
            }

            case MotionEvent.ACTION_UP: {
                endX = event.getX();
                if (endX - startX > 100) {
                    viewFlipper.showPrevious();
                }
                if (endX - startX < -100) {
                    viewFlipper.showNext();
                }
                break;
            }
        }
        return super.onTouchEvent(event);
    }

    private ImageView getImageView(int resId) {
        ImageView image = new ImageView(this);
        image.setBackgroundResource(resId);
        return image;
    }

}
