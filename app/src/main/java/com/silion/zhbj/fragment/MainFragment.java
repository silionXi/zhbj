package com.silion.zhbj.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.silion.zhbj.BasePager;
import com.silion.zhbj.GovaffairsPager;
import com.silion.zhbj.HomePager;
import com.silion.zhbj.NewsCenterPager;
import com.silion.zhbj.R;
import com.silion.zhbj.SettingPager;
import com.silion.zhbj.SmartservicePager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silion on 2016/8/4.
 */
public class MainFragment extends BaseFragment {
    private ViewPager vpContent;
    private ViewPagerAdapter mAdapter;
    private List<BasePager> mData = new ArrayList<>();
    private RadioGroup rgTab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        vpContent = (ViewPager) view.findViewById(R.id.vpContent);
        addPagers();
        mAdapter = new ViewPagerAdapter();
        vpContent.setAdapter(mAdapter);
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mData.get(position).initView();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mData.get(0).initView();

        rgTab = (RadioGroup) view.findViewById(R.id.rgTab);
        rgTab.check(R.id.rbHome);
        rgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbHome:
                        vpContent.setCurrentItem(0, false);
                        break;
                    case R.id.rbNewsCenter:
                        vpContent.setCurrentItem(1, false);
                        break;
                    case R.id.rbSmartService:
                        vpContent.setCurrentItem(2, false);
                        break;
                    case R.id.rbGovAffairs:
                        vpContent.setCurrentItem(3, false);
                        break;
                    case R.id.rbSetting:
                        vpContent.setCurrentItem(4, false);
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }

    private void addPagers() {
        mData.add(new HomePager(mActivity));
        mData.add(new NewsCenterPager(mActivity));
        mData.add(new SmartservicePager(mActivity));
        mData.add(new GovaffairsPager(mActivity));
        mData.add(new SettingPager(mActivity));
    }

    protected class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mData.get(position).mView;
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
