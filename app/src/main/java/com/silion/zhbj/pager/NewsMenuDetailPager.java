package com.silion.zhbj.pager;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silion.zhbj.R;
import com.silion.zhbj.activity.MainActivity;
import com.silion.zhbj.domain.NewsData.NewsTabData;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silion on 2016/8/12.
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager implements ViewPager.OnPageChangeListener {
    private ArrayList<NewsTabData> mNewsTabDatas;
    private ViewPager vpNewsMenuDetail;
    private NewsTabPagerAdapter mPagerAdapter;
    private TabPageIndicator mIndicator;
    private List<TapDetailPager> mTapDetailPagers;

    public NewsMenuDetailPager(Activity activity, ArrayList<NewsTabData> datas) {
        super(activity);
        mNewsTabDatas = datas;
    }

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.viewpager_news_menudetail, null);

        vpNewsMenuDetail = (ViewPager) view.findViewById(R.id.vpNewsMenuDetail);
        mPagerAdapter = new NewsTabPagerAdapter();
        vpNewsMenuDetail.setAdapter(mPagerAdapter);
//
        mIndicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        mIndicator.setViewPager(vpNewsMenuDetail);
        vpNewsMenuDetail.addOnPageChangeListener(this);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        mTapDetailPagers = new ArrayList<>();
        for (NewsTabData newsTabData : mNewsTabDatas) {
            mTapDetailPagers.add(new TapDetailPager(mActivity, newsTabData));
        }
        mPagerAdapter.notifyDataSetChanged();
        mIndicator.notifyDataSetChanged();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            ((MainActivity) mActivity).setSlidingMenuEnable(true);
        } else {
            ((MainActivity) mActivity).setSlidingMenuEnable(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class NewsTabPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mNewsTabDatas == null) {
                return 0;
            }
            return mNewsTabDatas.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mNewsTabDatas.get(position).title;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TapDetailPager tapDetailPager = mTapDetailPagers.get(position);
            container.addView(tapDetailPager.mView);
            tapDetailPager.initData();
            return tapDetailPager.mView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
