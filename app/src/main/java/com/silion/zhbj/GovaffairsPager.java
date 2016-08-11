package com.silion.zhbj;

import android.app.Activity;
import android.view.View;

/**
 * Created by silion on 2016/8/10.
 */
public class GovaffairsPager extends BasePager {
    public GovaffairsPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initView() {
        setSlidingMenuEnable(true);
        tvTitle.setText("政务办理");
        tvContent.setText("政务");
    }
}
