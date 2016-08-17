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
import com.silion.zhbj.domain.NewsData.NewsTabData;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

/**
 * Created by silion on 2016/8/12.
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager {
    private ArrayList<NewsTabData> mNewsTabDatas;
    private ViewPager vpNewsMenuDetail;
    private NewsTabPagerAdapter mPagerAdapter;
    private TabPageIndicator mIndicator;

    public NewsMenuDetailPager(Activity activity, ArrayList<NewsTabData> datas) {
        super(activity);
        mNewsTabDatas = datas;
    }

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.viewpager_news_menudetail, null);

        vpNewsMenuDetail = (ViewPager) view.findViewById(R.id.vpNewsMenuDetail);
//        mPagerAdapter = new NewsTabPagerAdapter();
//        vpNewsMenuDetail.setAdapter(mPagerAdapter);
//
        mIndicator = (TabPageIndicator) view.findViewById(R.id.indicator);
//        mIndicator.setViewPager(vpNewsMenuDetail);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
//        mPagerAdapter.notifyDataSetChanged();
//        mIndicator.notifyDataSetChanged();
        mPagerAdapter = new NewsTabPagerAdapter();
        vpNewsMenuDetail.setAdapter(mPagerAdapter);

        mIndicator.setViewPager(vpNewsMenuDetail);
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
            String title = mNewsTabDatas.get(position).title;
            TextView text = new TextView(mActivity);
            text.setText(title);
            text.setTextColor(Color.RED);
            text.setTextSize(25);
            text.setGravity(Gravity.CENTER);
            container.addView(text);
            return text;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
