package com.silion.zhbj.pager;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

import com.silion.zhbj.R;

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

        TextView tv = new TextView(mActivity);
        tv.setText("智慧服务");
        tv.setTextSize(30);
        tv.setTextColor(mActivity.getResources().getColor(R.color.red));
        tv.setGravity(Gravity.CENTER);

        flContent.addView(tv);
    }
}
