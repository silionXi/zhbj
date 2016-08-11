package com.silion.zhbj;

import android.app.Activity;
import android.view.View;

/**
 * Created by silion on 2016/8/10.
 */
public class NewsCenterPager extends BasePager {
    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initView() {
        setSlidingMenuEnable(true);
        tvTitle.setText("新闻");
        tvContent.setText("新闻中心");
    }
}
