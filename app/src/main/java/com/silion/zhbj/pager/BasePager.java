package com.silion.zhbj.pager;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.silion.zhbj.R;
import com.silion.zhbj.activity.MainActivity;

/**
 * Created by silion on 2016/8/10.
 */
public abstract class BasePager {
    public Activity mActivity;
    public View mView;
    public final ImageButton ibMenu;
    public final TextView tvTitle;
    public final FrameLayout flContent;
    public final ImageButton ibViewType;

    public BasePager(Activity activity) {
        mActivity = activity;
        mView = View.inflate(activity, R.layout.viewpager_main_base, null);
        tvTitle = (TextView) mView.findViewById(R.id.tvTitle);
        flContent = (FrameLayout) mView.findViewById(R.id.flContent);
        ibMenu = (ImageButton) mView.findViewById(R.id.ibMenu);
        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSlidingMenu();
            }
        });
        ibViewType = (ImageButton) mView.findViewById(R.id.ibViewType);
    }

    public void toggleSlidingMenu() {
        SlidingMenu slidingMenu = ((MainActivity) mActivity).getSlidingMenu();
        slidingMenu.toggle();
    }

    public abstract void initView();

    public void setSlidingMenuEnable(boolean enable) {
        SlidingMenu slidingMenu = ((MainActivity) mActivity).getSlidingMenu();
        if (enable) {
            ibMenu.setVisibility(View.VISIBLE);
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
            ibMenu.setVisibility(View.INVISIBLE);
        }
    }
}
