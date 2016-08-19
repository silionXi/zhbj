package com.silion.zhbj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.silion.zhbj.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by silion on 2016/8/18.
 */
public class RefreshListView extends ListView implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private View mHeaderView;
    private int mHeaderViewHeight;
    private int mStartY;
    private RefresState mCurrentState;
    private ImageView ivArrow;
    private ProgressBar pbRefresh;
    private TextView tvTitle;
    private TextView tvDate;
    private OnRefreshListener mRefreshListener;
    private RotateAnimation mUpAnim;
    private RotateAnimation mDownAnim;
    private View mFooterView;
    private int mFooterViewHeight;
    private boolean mIsLoadingMore;
    private OnLoadMoreListener mLoadMoreListener;
    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
            if (getLastVisiblePosition() == getCount() - 1 && !mIsLoadingMore) {
                mFooterView.setPadding(0, 0, 0, 0);
                setSelection(getCount() - 1);
                mIsLoadingMore = true;

                if (mLoadMoreListener != null) {
                    mLoadMoreListener.onLoadMore();
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mItemClickListener.onItemClick(parent, view, position - getHeaderViewsCount(), id);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return mItemLongClickListener.onItemLongClick(parent, view, position - getHeaderViewsCount(), id);
    }

    enum RefresState {REFRES_STATE_PULL, REFRES_STATE_RELEASE, REFRES_STATE_ING}

    public RefreshListView(Context context) {
        super(context);
        initHeaderView();
        initFooterView();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();
        initFooterView();
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeaderView();
        initFooterView();
    }

    private void initHeaderView() {
        mHeaderView = View.inflate(getContext(), R.layout.listheader_refresh, null);
        this.addHeaderView(mHeaderView);

        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        pbRefresh = (ProgressBar) findViewById(R.id.pbRefresh);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDate = (TextView) findViewById(R.id.tvDate);

        mHeaderView.measure(0, 0);
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
        initArrowAnim();
    }

    private void initFooterView() {
        mFooterView = View.inflate(getContext(), R.layout.listfooter_loadmore, null);
        this.addFooterView(mFooterView);
        mFooterView.measure(0, 0);
        mFooterViewHeight = mFooterView.getMeasuredHeight();
        mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);
        this.setOnScrollListener(this);
    }

    private void initArrowAnim() {
        mUpAnim = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mUpAnim.setDuration(200);
        mUpAnim.setFillAfter(true);

        mDownAnim = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mDownAnim.setDuration(200);
        mDownAnim.setFillAfter(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mCurrentState == RefresState.REFRES_STATE_ING) {
                    break;
                }
                if (mStartY == -1) {
                    mStartY = (int) ev.getRawY();
                }
                int endY = (int) ev.getRawY();
                int dy = endY - mStartY;
                if (dy > 0 && getFirstVisiblePosition() == 0) {
                    int padding = dy - mHeaderViewHeight;
                    if (padding > 0 && mCurrentState != RefresState.REFRES_STATE_RELEASE) {
                        mCurrentState = RefresState.REFRES_STATE_RELEASE;
                        refreshState();
                    } else if (padding < 0 && mCurrentState != RefresState.REFRES_STATE_PULL) {
                        mCurrentState = RefresState.REFRES_STATE_PULL;
                        refreshState();
                    }
                    mHeaderView.setPadding(0, padding, 0, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                mStartY = -1;
                if (mCurrentState == RefresState.REFRES_STATE_RELEASE) {
                    mCurrentState = RefresState.REFRES_STATE_ING;
                    mHeaderView.setPadding(0, 0, 0, 0);
                    refreshState();
                } else if (mCurrentState == RefresState.REFRES_STATE_PULL) {
                    mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void refreshState() {
        switch (mCurrentState) {
            case REFRES_STATE_PULL:
                ivArrow.setVisibility(VISIBLE);
                pbRefresh.setVisibility(INVISIBLE);
                tvTitle.setText("下拉刷新");
                ivArrow.startAnimation(mDownAnim);
                break;
            case REFRES_STATE_RELEASE:
                ivArrow.setVisibility(VISIBLE);
                pbRefresh.setVisibility(INVISIBLE);
                tvTitle.setText("松开刷新");
                ivArrow.startAnimation(mUpAnim);
                break;
            case REFRES_STATE_ING:
                ivArrow.clearAnimation();
                ivArrow.setVisibility(INVISIBLE);
                pbRefresh.setVisibility(VISIBLE);
                tvTitle.setText("正在刷新");
                if (mRefreshListener != null) {
                    mRefreshListener.onRefresh();
                }
                break;
            default:
                break;
        }
    }

    public void refreshCompelete(boolean success) {
        mCurrentState = RefresState.REFRES_STATE_PULL;
        ivArrow.setVisibility(VISIBLE);
        pbRefresh.setVisibility(INVISIBLE);
        tvTitle.setText("下拉刷新");
        mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);

        if (success) {
            tvDate.setText(getCurrentDate());
        }
    }

    public void loadMoreCompelete() {
        mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);// 隐藏脚布局
        mIsLoadingMore = false;
    }

    private String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mRefreshListener = listener;
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mLoadMoreListener = listener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
        super.setOnItemClickListener(this);
    }

    @Override
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mItemLongClickListener = listener;
        super.setOnItemLongClickListener(this);
    }
}
