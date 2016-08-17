package com.silion.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by silion on 2016/8/10.
 */
public class MainContentViewPager extends ViewPager {
    public MainContentViewPager(Context context) {
        super(context);
    }

    public MainContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
