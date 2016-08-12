package com.silion.zhbj.pager;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

import com.silion.zhbj.R;

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

        TextView tv = new TextView(mActivity);
        tv.setText("设置");
        tv.setTextSize(30);
        tv.setTextColor(mActivity.getResources().getColor(R.color.red));
        tv.setGravity(Gravity.CENTER);

        flContent.addView(tv);
    }
}
