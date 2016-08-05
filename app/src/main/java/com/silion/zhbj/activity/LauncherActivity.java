package com.silion.zhbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.silion.zhbj.R;
import com.silion.zhbj.utils.AnimationUtils;

/**
 * Created by silion on 2016/8/1.
 */
public class LauncherActivity extends Activity {

    private RelativeLayout rlRoot;
    private SharedPreferences mPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        rlRoot = (RelativeLayout) findViewById(R.id.rlRoot);
        mPre = getSharedPreferences("settings", MODE_PRIVATE);
        startAnimation(1000);
    }

    private void startAnimation(long duration) {
        RotateAnimation rotate = AnimationUtils.getRotateAnimationByCenter(duration);
        ScaleAnimation scale = AnimationUtils.getAmplificationScaleAnimation(duration);
        AlphaAnimation alpha = AnimationUtils.getShowAlphaAnimation(duration);

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(rotate);
        animationSet.addAnimation(scale);
        animationSet.addAnimation(alpha);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                nextPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        rlRoot.startAnimation(animationSet);
    }

    private void nextPage() {
        if (mPre.getBoolean("is_guided", false)) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, GuideActivity.class));
        }
        finish();
    }
}
