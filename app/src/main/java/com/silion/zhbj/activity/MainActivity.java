package com.silion.zhbj.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.silion.zhbj.R;
import com.silion.zhbj.fragment.BaseFragment;
import com.silion.zhbj.fragment.MainFragment;
import com.silion.zhbj.fragment.MainLeftMenuFragment;

/**
 * Created by silion on 2016/8/3.
 */
public class MainActivity extends Activity {

    private FragmentManager mFragmentManager;
    private SlidingMenu mSlidingMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("智慧北京");
        setContentView(R.layout.activity_main);
        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        Display display = getWindowManager().getDefaultDisplay();
        mSlidingMenu.setBehindOffset(display.getWidth() * 2 / 3);
        mSlidingMenu.setFadeDegree(0.35f);
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        mSlidingMenu.setMenu(R.layout.slidingmenu_main);

        initFragment();
    }

    private void initFragment() {
        mFragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new MainFragment(), MainFragment.class.getSimpleName());
        fragmentTransaction.add(R.id.menuContainer, new MainLeftMenuFragment(), MainLeftMenuFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

    public SlidingMenu getSlidingMenu() {
        return mSlidingMenu;
    }

    public BaseFragment getFragment(String tag) {
        return (BaseFragment) mFragmentManager.findFragmentByTag(tag);
    }
}
