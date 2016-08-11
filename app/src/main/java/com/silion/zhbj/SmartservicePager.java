package com.silion.zhbj;

import android.app.Activity;
import android.view.View;

/**
 * Created by silion on 2016/8/10.
 */
public class SmartservicePager extends BasePager {
    public SmartservicePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initView() {
        setSlidingMenuEnable(true);
        tvTitle.setText("生活");
        tvContent.setText("智慧服务");
    }
}
