package com.silion.zhbj.pager;

import android.app.Activity;

import com.google.gson.Gson;
import com.silion.zhbj.activity.MainActivity;
import com.silion.zhbj.domain.NewsData;
import com.silion.zhbj.domain.NewsData.NewsTabData;
import com.silion.zhbj.fragment.MainLeftMenuFragment;
import com.silion.zhbj.global.GlobalContants;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silion on 2016/8/10.
 */
public class NewsCenterPager extends BasePager {
    private final static String DEFAULT_NEWSDATA = "{\"retcode\":200,\"data\":[{\"id\":10000,\"title\":\"新闻\",\"type\":1,\"children\":[{\"id\":10007,\"title\":\"北京\",\"type\":1,\"url\":\"/10007/list_1.json\"},{\"id\":10006,\"title\":\"中国\",\"type\":1,\"url\":\"/10006/list_1.json\"},{\"id\":10008,\"title\":\"国际\",\"type\":1,\"url\":\"/10008/list_1.json\"},{\"id\":10010,\"title\":\"体育\",\"type\":1,\"url\":\"/10010/list_1.json\"},{\"id\":10091,\"title\":\"生活\",\"type\":1,\"url\":\"/10091/list_1.json\"},{\"id\":10012,\"title\":\"旅游\",\"type\":1,\"url\":\"/10012/list_1.json\"},{\"id\":10095,\"title\":\"科技\",\"type\":1,\"url\":\"/10095/list_1.json\"},{\"id\":10009,\"title\":\"军事\",\"type\":1,\"url\":\"/10009/list_1.json\"},{\"id\":10093,\"title\":\"时尚\",\"type\":1,\"url\":\"/10093/list_1.json\"},{\"id\":10011,\"title\":\"财经\",\"type\":1,\"url\":\"/10011/list_1.json\"},{\"id\":10094,\"title\":\"育儿\",\"type\":1,\"url\":\"/10094/list_1.json\"},{\"id\":10105,\"title\":\"汽车\",\"type\":1,\"url\":\"/10105/list_1.json\"}]},{\"id\":10002,\"title\":\"专题\",\"type\":10,\"url\":\"/10006/list_1.json\",\"url1\":\"/10007/list1_1.json\"},{\"id\":10003,\"title\":\"组图\",\"type\":2,\"url\":\"/10008/list_1.json\"},{\"id\":10004,\"title\":\"互动\",\"type\":3,\"excurl\":\"\",\"dayurl\":\"\",\"weekurl\":\"\"}],\"extend\":[10007,10006,10008,10014,10012,10091,10009,10010,10095]}";
    private NewsData mNewsData;
    private List<BaseMenuDetailPager> mPagers;

    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initView() {
        setSlidingMenuEnable(true);
        getDataFramServer();
    }

    private void getDataFramServer() {
        final RequestParams params = new RequestParams(GlobalContants.CATEGORIES_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                parseData(s);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                try {
                    parseData(DEFAULT_NEWSDATA);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(CancelledException e) {
                parseData(DEFAULT_NEWSDATA);
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void parseData(String result) {
        Gson gson = new Gson();
        mNewsData = gson.fromJson(result, NewsData.class);
        android.util.Log.v("silion", "新闻数据：" + mNewsData);

        MainLeftMenuFragment fragment = (MainLeftMenuFragment) ((MainActivity) mActivity).getFragment(MainLeftMenuFragment.class.getSimpleName());
        fragment.setNewsMenuData(mNewsData.data);

        mPagers = new ArrayList<>();
        mPagers.add(new NewsMenuDetailPager(mActivity, mNewsData.data.get(0).children));
        mPagers.add(new TopicMenuDetailPager(mActivity));
        mPagers.add(new PhotoMenuDetaiPager(mActivity));
        mPagers.add(new InteractMenuDetailPager(mActivity));

        setMenuDetailPager(0);
    }

    public void setMenuDetailPager(int location) {
        BaseMenuDetailPager pager = mPagers.get(location);
        pager.initData();

        flContent.removeAllViews();
        flContent.addView(pager.mRootView);

        tvTitle.setText(mNewsData.data.get(location).title);
    }
}
