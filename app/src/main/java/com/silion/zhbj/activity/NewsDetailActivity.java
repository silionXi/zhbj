package com.silion.zhbj.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.silion.zhbj.R;

/**
 * Created by silion on 2016/8/19.
 */
public class NewsDetailActivity extends Activity {

    private WebView webView;
    private WebSettings mWebSettings;
    private ImageButton ibBack;
    private ImageButton ibTextSize;
    private ImageButton ibShare;

    private String[] mTextSizeItem = new String[]{"超大号字体", "大号字体", "正常字体", "小号字体", "超小号字体"};
    private int[] mTextSize = new int[]{200, 150, 100, 75, 50};

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ibBack:
                    finish();
                    break;
                case R.id.ibTextSize:
                    showChooseDialog();
                    break;
                case R.id.ibShare:
                    showShare();
                    break;
                default:
                    break;
            }
        }
    };
    private int mChooseItem;
    private int mCurrentItem = 2;

    private void showChooseDialog() {
        AlertDialog.Builder builer = new AlertDialog.Builder(this);
        builer.setTitle("字体设置")
                .setSingleChoiceItems(mTextSizeItem, mCurrentItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mChooseItem = which;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCurrentItem = mChooseItem;

                        mWebSettings.setTextZoom(mTextSize[mChooseItem]);
                    }
                }).show();
    }

    private TextView tvTitle;
    private ProgressBar progressBar;

    private void showShare() {
        //@TODO ShareSDK
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
        String url = getIntent().getStringExtra("url");
        webView = (WebView) findViewById(R.id.webView);
        mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true); // 表示支持js
        mWebSettings.setBuiltInZoomControls(true);// 显示放大缩小按钮
        mWebSettings.setUseWideViewPort(true);// 支持双击缩放
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                android.util.Log.d("silion", "网页加载开始");
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                android.util.Log.d("silion", "网页加载结束");
                progressBar.setVisibility(View.GONE);
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                android.util.Log.d("silion", "所有跳转的链接都会在此方法中回调:url = " + url);
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                android.util.Log.d("silion", "加载进度：" + newProgress);
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                tvTitle.setText(title);
                super.onReceivedTitle(view, title);
            }
        });
        webView.loadUrl(url);

        ibBack = (ImageButton) findViewById(R.id.ibBack);
        ibTextSize = (ImageButton) findViewById(R.id.ibTextSize);
        ibShare = (ImageButton) findViewById(R.id.ibShare);
        ibBack.setOnClickListener(mClickListener);
        ibTextSize.setOnClickListener(mClickListener);
        ibShare.setOnClickListener(mClickListener);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }
}
