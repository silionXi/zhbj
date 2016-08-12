package com.silion.zhbj.pager;

import android.app.Activity;
import android.view.View;

/**
 * Created by silion on 2016/8/12.
 */
public abstract class BaseMenuDetailPager {

    public Activity mActivity;

    public View mRootView;// 根布局对象

    public BaseMenuDetailPager(Activity activity) {
        mActivity = activity;
        mRootView = initViews();
    }

    /**
     * 初始化界面
     */
    public abstract View initViews();

    /**
     * 初始化数据
     */
    public void initData() {

    }

}
