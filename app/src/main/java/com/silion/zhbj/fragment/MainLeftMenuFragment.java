package com.silion.zhbj.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.silion.zhbj.R;
import com.silion.zhbj.activity.MainActivity;
import com.silion.zhbj.domain.NewsData.NewsMenuData;
import com.silion.zhbj.pager.NewsCenterPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silion on 2016/8/4.
 */
public class MainLeftMenuFragment extends BaseFragment {
    private List<NewsMenuData> mNewsMenuDatas = new ArrayList<>();
    private ListView mListView;
    private ListAdapter mListAdapter;
    private int mSelectItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_leftmenu, null);
        mListView = (ListView) view.findViewById(R.id.listview);
        mListAdapter = new ListAdapter();
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectItem = position;
                mListAdapter.notifyDataSetChanged();
                toggleSlidingMenu();
                MainFragment fragment = (MainFragment) ((MainActivity) mActivity).getFragment(MainFragment.class.getSimpleName());
                NewsCenterPager pager = (NewsCenterPager) fragment.getPager(1);
                pager.setMenuDetailPager(position);
            }
        });
        return view;
    }

    public void setNewsMenuData(List<NewsMenuData> data) {
        mNewsMenuDatas.clear();
        mNewsMenuDatas.addAll(data);
        mListAdapter.notifyDataSetChanged();
    }

    public void toggleSlidingMenu() {
        SlidingMenu slidingMenu = ((MainActivity) mActivity).getSlidingMenu();
        slidingMenu.toggle();
    }

    class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mNewsMenuDatas.size();
        }

        @Override
        public NewsMenuData getItem(int position) {
            return mNewsMenuDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity, R.layout.listitem_mainleftmenu, null);
            NewsMenuData newsMenuData = getItem(position);
            TextView tvMenu = (TextView) view.findViewById(R.id.tvMenu);
            tvMenu.setText(newsMenuData.title);
            if (mSelectItem == position) {
                tvMenu.setEnabled(true);
            } else {
                tvMenu.setEnabled(false);
            }
            return view;
        }
    }
}
