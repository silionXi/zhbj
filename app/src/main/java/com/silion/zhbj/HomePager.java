package com.silion.zhbj;

import android.app.Activity;
import android.view.View;

/**
 * Created by silion on 2016/8/10.
 */
public class HomePager extends BasePager {
    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initView() {
        setSlidingMenuEnable(false);
        tvTitle.setText("智慧北京");
        tvContent.setText("首页");
    }
}
