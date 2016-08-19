package com.silion.zhbj.pager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.silion.zhbj.R;
import com.silion.zhbj.domain.NewsData.NewsTabData;
import com.silion.zhbj.domain.TabData;
import com.silion.zhbj.global.GlobalContants;
import com.silion.zhbj.view.RefreshListView;
import com.viewpagerindicator.CirclePageIndicator;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silion on 2016/8/17.
 */
public class TapDetailPager {
    private final static String DEFAULT_NEWSTABDATA = "{\"data\":{\"countcommenturl\":\"http://zhbj.qianlong.com/client/content/countComment/\",\"more\":\"/10007/list_2.json\",\"news\":[{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35311,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/2078369924F9UO.jpg\",\"pubdate\":\"2014-10-1113:18\",\"title\":\"网上大讲堂第368期预告：义务环保人人有责\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35312,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1509585620ASS3.jpg\",\"pubdate\":\"2014-10-1111:20\",\"title\":\"马路改建为停车场车位年费高达3000元\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35313,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg\",\"pubdate\":\"2014-10-1110:34\",\"title\":\"北京两年内将迁出1200家工业污染企业\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35314,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1505891536Z82T.jpg\",\"pubdate\":\"2014-10-1110:08\",\"title\":\"大雾再锁京城机场航班全部延误\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35315,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1483727032VMXT.jpg\",\"pubdate\":\"2014-10-1110:03\",\"title\":\"APEC会议期间调休企业员工盼同步放假\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35316,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1481879990BEMG.jpg\",\"pubdate\":\"2014-10-1109:59\",\"title\":\"机械“龙马”巡演17日亮相奥园\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35310,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/14800329488K7F.jpg\",\"pubdate\":\"2014-10-1109:54\",\"title\":\"门头沟获批100套限价房\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35318,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/14791094274LT9.jpg\",\"pubdate\":\"2014-10-1109:52\",\"title\":\"APEC期间净空区放带灯风筝可拘留\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35314\",\"id\":35314,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1478185906G9WQ.jpg\",\"pubdate\":\"2014-10-1109:48\",\"title\":\"今起两自住房摇号\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35117\",\"id\":35117,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1477262385PASS.jpg\",\"pubdate\":\"2014-10-1109:45\",\"title\":\"故宫神武门广场拟夜间开放停车\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"}],\"title\":\"北京\",\"topic\":[{\"description\":\"11111111\",\"id\":10101,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg\",\"sort\":1,\"title\":\"北京\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/list_1.json\"},{\"description\":\"22222222\",\"id\":10100,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg\",\"sort\":2,\"title\":\"222222222222\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/list_1.json\"}],\"topnews\":[{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35301\",\"id\":35301,\"pubdate\":\"2014-04-0814:24\",\"title\":\"蜗居生活\",\"topimage\":\"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35125\",\"id\":35125,\"pubdate\":\"2014-04-0809:09\",\"title\":\"中秋赏月\",\"topimage\":\"http://10.0.2.2:8080/zhbj/10007/1452327318UU92.jpg\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35125\",\"id\":35126,\"pubdate\":\"2014-04-0809:09\",\"title\":\"天空翱翔\",\"topimage\":\"http://10.0.2.2:8080/zhbj/10007/1452327318UU93.jpg\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35125\",\"id\":35127,\"pubdate\":\"2014-04-0809:09\",\"title\":\"感官设计\",\"topimage\":\"http://10.0.2.2:8080/zhbj/10007/1452327318UU94.png\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"}]},\"retcode\":200}";
    private final static String DEFAULT_NEWSMORE = "{\"data\":{\"countcommenturl\":\"http://zhbj.qianlong.com/client/content/countComment/\",\"more\":\"\",\"news\":[{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35311,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/2078369924F9UO.jpg\",\"pubdate\":\"2014-10-1113:18\",\"title\":\"网上大讲堂第368期预告：义务环保人人有责2\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35312,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1509585620ASS3.jpg\",\"pubdate\":\"2014-10-1111:20\",\"title\":\"马路改建为停车场车位年费高达3000元2\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35313,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg\",\"pubdate\":\"2014-10-1110:34\",\"title\":\"北京两年内将迁出1200家工业污染企业2\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35314,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1505891536Z82T.jpg\",\"pubdate\":\"2014-10-1110:08\",\"title\":\"大雾再锁京城机场航班全部延误2\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35315,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1483727032VMXT.jpg\",\"pubdate\":\"2014-10-1110:03\",\"title\":\"APEC会议期间调休企业员工盼同步放假2\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35316,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1481879990BEMG.jpg\",\"pubdate\":\"2014-10-1109:59\",\"title\":\"机械“龙马”巡演17日亮相奥园2\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35310,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/14800329488K7F.jpg\",\"pubdate\":\"2014-10-1109:54\",\"title\":\"门头沟获批100套限价房2\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35319\",\"id\":35318,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/14791094274LT9.jpg\",\"pubdate\":\"2014-10-1109:52\",\"title\":\"APEC期间净空区放带灯风筝可拘留2\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35314\",\"id\":35314,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1478185906G9WQ.jpg\",\"pubdate\":\"2014-10-1109:48\",\"title\":\"今起两自住房摇号2\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35117\",\"id\":35117,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1477262385PASS.jpg\",\"pubdate\":\"2014-10-1109:45\",\"title\":\"故宫神武门广场拟夜间开放停车2\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"}],\"title\":\"北京\",\"topic\":[{\"description\":\"11111111\",\"id\":10101,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg\",\"sort\":1,\"title\":\"北京\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/list_1.json\"},{\"description\":\"22222222\",\"id\":10100,\"listimage\":\"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg\",\"sort\":2,\"title\":\"222222222222\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/list_1.json\"}],\"topnews\":[{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35301\",\"id\":35301,\"pubdate\":\"2014-04-0814:24\",\"title\":\"北京1\",\"topimage\":\"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"},{\"comment\":true,\"commentlist\":\"http://10.0.2.2:8080/zhbj/10007/comment_1.json\",\"commenturl\":\"http://zhbj.qianlong.com/client/user/newComment/35125\",\"id\":35125,\"pubdate\":\"2014-04-0809:09\",\"title\":\"北京2\",\"topimage\":\"http://10.0.2.2:8080/zhbj/10007/1452327318UU92.jpg\",\"type\":\"news\",\"url\":\"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html\"}]},\"retcode\":200}";
    private Activity mActivity;
    public View mView;
    private final NewsTabData mNewsTabData;
    private ViewPager vpTopNews;
    private TopNewsPagerAdapter mPagerAdapter;
    private TabData mTabData;
    private List<TabData.TopNewsData> mTopNewsDatas = new ArrayList<>();
    private List<TabData.TabNewsData> mTabNewsDatas = new ArrayList<>();
    private int[] mTopNewsDefault = new int[]{R.drawable.topnews_item_default_1, R.drawable.topnews_item_default_2, R.drawable.topnews_item_default_3, R.drawable.topnews_item_default_4};
    private CirclePageIndicator mIndicator;
    private TextView tvTitle;
    private RefreshListView mListView;
    private ListAdapter mListAdapter;
    private final View mHeaderView;
    private final String mUrl;
    private String mMoreUrl;
    private SharedPreferences mSharedPref;

    public TapDetailPager(Activity activity, NewsTabData newsTabData) {
        mActivity = activity;
        mNewsTabData = newsTabData;
        mUrl = GlobalContants.SERVER_URL + mNewsTabData.url;
        mView = View.inflate(mActivity, R.layout.viewpager_tapdetail, null);
        mHeaderView = View.inflate(mActivity, R.layout.listheader_news, null);
        initView();
    }

    private void initView() {
        vpTopNews = (ViewPager) mHeaderView.findViewById(R.id.vpTopNews);
        mPagerAdapter = new TopNewsPagerAdapter();
        vpTopNews.setAdapter(mPagerAdapter);
        mIndicator = (CirclePageIndicator) mHeaderView.findViewById(R.id.indicator);
        tvTitle = (TextView) mHeaderView.findViewById(R.id.tvTitle);
        mIndicator.setViewPager(vpTopNews);
        vpTopNews.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(mTopNewsDatas.get(position).title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mListView = (RefreshListView) mView.findViewById(R.id.listview);
        mListAdapter = new ListAdapter();
        mListView.setAdapter(mListAdapter);
        mListView.addHeaderView(mHeaderView);
        mListView.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromServer(mUrl, false);
            }
        });
        mListView.setOnLoadMoreListener(new RefreshListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mMoreUrl == null) {
                    Toast.makeText(mActivity, "已经是最后一页了...", Toast.LENGTH_SHORT).show();
                    mListView.loadMoreCompelete();
                } else {
                    getDataFromServer(mMoreUrl, true);
                }
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String newsId = mTabNewsDatas.get(position).id;
                String readedIds = mSharedPref.getString("readed_ids", "");
                if (!readedIds.contains(newsId)) {
                    readedIds = readedIds + newsId + ",";
                    mSharedPref.edit().putString("readed_ids", readedIds).apply();
                    TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
                    tvTitle.setTextColor(mActivity.getResources().getColor(R.color.gray));
                }
            }
        });
    }

    public void initData() {
        mSharedPref = mActivity.getSharedPreferences("news", Context.MODE_PRIVATE);
        getDataFromServer(mUrl, false);
    }

    private void getDataFromServer(String url, final boolean isMore) {
        final RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                if (isMore) {
                    mListView.loadMoreCompelete();
                } else {
                    mListView.refreshCompelete(true);
                }
                parseData(s, isMore);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                try {
                    SystemClock.sleep(1000);
                    if (isMore) {
                        parseData(DEFAULT_NEWSMORE, isMore);
                        mListView.loadMoreCompelete();
                    } else {
                        mListView.refreshCompelete(false);
                        parseData(DEFAULT_NEWSTABDATA, isMore);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(CancelledException e) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void parseData(String result, boolean isMore) {
        Gson gson = new Gson();
        mTabData = gson.fromJson(result, TabData.class);
        android.util.Log.v("silion", mTabData.toString());
        String more = mTabData.data.more;
        if (!TextUtils.isEmpty(more)) {
            mMoreUrl = GlobalContants.SERVER_URL + more;
        } else {
            mMoreUrl = null;
        }

        if (!isMore) {
            mTopNewsDatas.clear();
            mTabNewsDatas.clear();
        }
        mTopNewsDatas.addAll(mTabData.data.topnews);
        mTabNewsDatas.addAll(mTabData.data.news);
        mPagerAdapter.notifyDataSetChanged();
        mIndicator.notifyDataSetChanged();
        tvTitle.setText(mTopNewsDatas.get(0).title);

        mListAdapter.notifyDataSetChanged();
    }

    class TopNewsPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mTopNewsDatas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mActivity);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(mTopNewsDefault[position % 4]);
            TabData.TopNewsData topNewsData = mTopNewsDatas.get(position);
//            ImageOptions imageOptions = new ImageOptions.Builder()
//                    .setImageScaleType(ImageView.ScaleType.FIT_XY)
//                    .setFailureDrawableId(mTopNewsDefault[position % 4])
//                    .build();
//            x.image().bind(imageView, topNewsData.topimage, imageOptions);
//            x.image().bind(imageView, "assets://topnews_item_default.png", imageOptions);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mTabNewsDatas.size();
        }

        @Override
        public TabData.TabNewsData getItem(int position) {
            return mTabNewsDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = View.inflate(mActivity, R.layout.listitem_news, null);
                viewHolder.ivPic = (ImageView) view.findViewById(R.id.ivPic);
                viewHolder.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
                viewHolder.tvDate = (TextView) view.findViewById(R.id.tvDate);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            TabData.TabNewsData tabNewsData = getItem(position);
            viewHolder.tvTitle.setText(tabNewsData.title);
            viewHolder.tvDate.setText(tabNewsData.pubdate);
            String newsId = tabNewsData.id;
            String readedIds = mSharedPref.getString("readed_ids", "");
            if (readedIds.contains(newsId)) {
                viewHolder.tvTitle.setTextColor(mActivity.getResources().getColor(R.color.gray));
            }
            return view;
        }

        class ViewHolder {
            ImageView ivPic;
            TextView tvTitle;
            TextView tvDate;
        }
    }
}
