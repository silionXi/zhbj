package com.silion.zhbj.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.silion.zhbj.R;
import com.silion.zhbj.fragment.MainFragment;
import com.silion.zhbj.fragment.MainLeftMenuFragment;

/**
 * Created by silion on 2016/8/3.
 */
public class MainActivity extends Activity {

    private FragmentManager mFragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("智慧北京");
        setContentView(R.layout.activity_main);
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setBehindOffset(1000);
        menu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.slidingmenu_main);

        initFragment();
    }

    private void initFragment() {
        mFragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new MainFragment(), MainFragment.class.getSimpleName());
        fragmentTransaction.add(R.id.menuContainer, new MainLeftMenuFragment(), MainLeftMenuFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }
}
