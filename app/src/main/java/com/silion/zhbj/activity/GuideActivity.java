package com.silion.zhbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.silion.zhbj.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silion on 2016/8/3.
 */
public class GuideActivity extends Activity {
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private List<View> mViewList;
    private int[] mGuideImg = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private Button btStart;
    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int transfer = (int) (position * mDistance + positionOffset * mDistance);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewSelected.getLayoutParams();
            params.leftMargin = transfer;
            viewSelected.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {
            if (position == mViewList.size() - 1) {
                btStart.setVisibility(View.VISIBLE);
            } else {
                btStart.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private LinearLayout llPageBullet;
    private View viewSelected;
    private int mDistance;
    private SharedPreferences mPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mPre = getSharedPreferences("settings", MODE_PRIVATE);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewList = new ArrayList<>();
        mViewPagerAdapter = new ViewPagerAdapter();
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        btStart = (Button) findViewById(R.id.btStart);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPre.edit().putBoolean("is_guided", true).apply();
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
        llPageBullet = (LinearLayout) findViewById(R.id.llPageBullet);
        viewSelected = findViewById(R.id.viewSelected);
        initViews();
    }

    private void initViews() {
        mViewList.clear();
        List<View> viewList = new ArrayList<>();
        for (int i = 0; i < mGuideImg.length; i++) {
            View view = new View(this);
            view.setBackgroundResource(mGuideImg[i]);
            viewList.add(view);
        }

        mViewList.addAll(viewList);
        mViewPagerAdapter.notifyDataSetChanged();

        for (int i = 0; i < mGuideImg.length; i++) {
            View bullet = new View(this);
            bullet.setBackgroundResource(R.drawable.guide_page_bullet_gray_shape);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
            if (i > 0) {
                params.leftMargin = 50;
            }
            bullet.setLayoutParams(params);
            llPageBullet.addView(bullet);
        }
        llPageBullet.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llPageBullet.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mDistance = llPageBullet.getChildAt(1).getLeft() - llPageBullet.getChildAt(0).getLeft();
            }
        });
    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mViewList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
