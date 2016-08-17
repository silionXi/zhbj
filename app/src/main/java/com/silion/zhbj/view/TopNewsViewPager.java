package com.silion.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by silion on 2016/8/17.
 */
public class TopNewsViewPager extends ViewPager {

    private int mStartX;
    private int mStartY;

    public TopNewsViewPager(Context context) {
        super(context);
    }

    public TopNewsViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 事件分发, 请求父控件及祖宗控件是否拦截事件
     * 1. 右划, 而且是第一个页面, 需要父控件拦截
     * 2. 左划, 而且是最后一个页面, 需要父控件拦截
     * 3. 上下滑动, 需要父控件拦截
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = (int) ev.getRawX();
                mStartY = (int) ev.getRawY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getRawX();
                int endY = (int) ev.getRawY();
                if (Math.abs(endY - mStartY) > Math.abs(endX - mStartX)) {
                    //上下滑动，拦截
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    if (endX > mStartX && getCurrentItem() == 0) {
                        // 右划且是第一个
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else if (endX < mStartX && getCurrentItem() == getAdapter().getCount() - 1) {
                        //左划且是最后一个
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
