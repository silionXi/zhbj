package com.silion.zhbj;

import android.app.Activity;
import android.view.View;

/**
 * Created by silion on 2016/8/10.
 */
public class SettingPager extends BasePager {
    public SettingPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initView() {
        setSlidingMenuEnable(false);
        tvTitle.setText("设置");
        tvContent.setText("设置");
    }
}
