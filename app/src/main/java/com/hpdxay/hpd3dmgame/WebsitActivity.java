package com.hpdxay.hpd3dmgame;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebsitActivity extends AppCompatActivity implements WebSupport {

    private WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_websit);

        setTitle("官方网站");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        String path = intent.getStringExtra("path");

        mWebView = (WebView) findViewById(R.id.id_websit_web);
        mProgressBar = (ProgressBar) findViewById(R.id.id_websit_progress);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient(this));

        // WebView 通用的配置
        // 设置初始的缩放比例，以100为限
        mWebView.setInitialScale(50);
        // WebSettings 是 WebView的通用配置集合；
        WebSettings settings = mWebView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDefaultFontSize(30);
        // 允许手势和控件进行缩放
        settings.setSupportZoom(true);
        mWebView.loadUrl(path);
    }

    @Override
    public void updateProgress(int progress) {
        mProgressBar.setProgress(progress);
    }

    @Override
    public void updateTitle(String title) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                if (mWebView != null) {
                    mWebView.destroy();
                    mWebView = null;
                }

                ;
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
    }
}
