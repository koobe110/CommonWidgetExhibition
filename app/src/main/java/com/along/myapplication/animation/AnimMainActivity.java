package com.along.myapplication.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.along.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class AnimMainActivity extends Activity implements OnClickListener {

    private ImageView image;
    private Button scale;
    private Button rotate;
    private Button translate;
    private Button mix;
    private Button alpha;
    private Button continue_btn;
    private Button continue_btn2;
    private Button flash;
    private Button move;
    private Button change;
    private Button layout;
    private Button frame;
    private ImageView propertyImageView;
    private int[] propertyImageViewsID = {R.id.animImageViewB, R.id.animImageViewC, R.id.animImageViewD, R.id.animImageViewE, R.id.animImageViewF};
    private boolean animFlag = false;
    private List<ImageView> propertyImageViewsList = new ArrayList<ImageView>();
    private ImageView propertyImageViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim);
        image = (ImageView) findViewById(R.id.imageMeinv);
        scale = (Button) findViewById(R.id.scale);
        rotate = (Button) findViewById(R.id.rotate);
        translate = (Button) findViewById(R.id.translate);
        alpha = (Button) findViewById(R.id.alpha);
        continue_btn = (Button) findViewById(R.id.continue_btn);
        continue_btn2 = (Button) findViewById(R.id.continue_btn2);
        flash = (Button) findViewById(R.id.flash);
        change = (Button) findViewById(R.id.change);
        layout = (Button) findViewById(R.id.layout);
        frame = (Button) findViewById(R.id.frame);
        propertyImageViewButton = (ImageView) findViewById(R.id.animImageViewA);
        for (int i = 0; i < propertyImageViewsID.length; i++) {
            propertyImageView = (ImageView) findViewById(propertyImageViewsID[i]);
            propertyImageView.setOnClickListener(this);
            propertyImageViewsList.add(propertyImageView);
        }
        propertyImageViewButton.setOnClickListener(this);
        scale.setOnClickListener(this);
        rotate.setOnClickListener(this);
        translate.setOnClickListener(this);
        alpha.setOnClickListener(this);
        continue_btn.setOnClickListener(this);
        continue_btn2.setOnClickListener(this);
        flash.setOnClickListener(this);
//		move.setOnClickListener(this);
        change.setOnClickListener(this);
        layout.setOnClickListener(this);
        frame.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        Animation loadAnimation;
        switch (view.getId()) {
            case R.id.scale: {
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
                image.startAnimation(loadAnimation);
                break;
            }

            case R.id.rotate: {
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
                image.startAnimation(loadAnimation);
                break;
            }

            case R.id.translate: {

                loadAnimation = AnimationUtils
                        .loadAnimation(this, R.anim.translate);
                image.startAnimation(loadAnimation);
                break;
            }

            case R.id.continue_btn: {
                loadAnimation = AnimationUtils
                        .loadAnimation(this, R.anim.translate);
                image.startAnimation(loadAnimation);
                final Animation loadAnimation2 = AnimationUtils.loadAnimation(this,
                        R.anim.rotate);
                loadAnimation.setAnimationListener(new AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation arg0) {
                        // TODO Auto-generated method stub
                        image.startAnimation(loadAnimation2);
                    }
                });
                break;
            }

            case R.id.continue_btn2: {
                loadAnimation = AnimationUtils.loadAnimation(this,
                        R.anim.continue_anim);
                image.startAnimation(loadAnimation);
                break;
            }

            case R.id.alpha: {
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                image.startAnimation(loadAnimation);
                break;
            }


            case R.id.flash: {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                alphaAnimation.setDuration(1000);
                alphaAnimation.setRepeatCount(10);
                //倒序重复REVERSE  正序重复RESTART
                alphaAnimation.setRepeatMode(Animation.REVERSE);
//			alphaAnimation.setRepeatMode(Animation.RESTART);
                image.startAnimation(alphaAnimation);
                break;
            }

            case R.id.change: {
                Intent intent = new Intent(AnimMainActivity.this, AnimMainActivity2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                break;
            }

            case R.id.layout: {
                Intent intent = new Intent(AnimMainActivity.this, AnimListActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.frame: {
                image.setImageResource(R.drawable.meinv3);
                break;

            }
            case R.id.animImageViewA: {
                if (!animFlag) {
                    openPropertyAnim();
                    animFlag = true;
                } else {
                    closePropertyAnim();
                    animFlag = false;
                }
                break;
            }

        }
    }

    private void openPropertyAnim() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        image.clearAnimation();
        image.setVisibility(View.GONE);
        image.invalidate();
        for (int i = 0; i <propertyImageViewsList.size() ; i++) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(propertyImageViewsList.get(i), "translationX", 0, width*(i+1)/(propertyImageViewsList.size()+1));
            objectAnimator.setDuration(200);
            objectAnimator.setStartDelay(400 * i);
            objectAnimator.setInterpolator(new BounceInterpolator());
            objectAnimator.start();
        }
    }

    private void closePropertyAnim() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        for (int i = 0; i < propertyImageViewsList.size(); i++) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(propertyImageViewsList.get(i), "translationX",width*(i+1)/(propertyImageViewsList.size()+1),0);
            objectAnimator.setDuration(200);
            objectAnimator.setStartDelay(400 * i);
            objectAnimator.setInterpolator(new BounceInterpolator());
            if(i == propertyImageViewsList.size()-1){
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    image.setVisibility(View.VISIBLE);
                    super.onAnimationEnd(animation);
                }
            });
            }
            objectAnimator.start();
        }
    }

}
