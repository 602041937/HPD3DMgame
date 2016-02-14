package com.hpdxay.hpd3dmgame;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.hpdxay.hpd3dmgame.utils.MyLog;

public class ChapterContentActivity extends AppCompatActivity implements WebSupport {

    private WebView mWebView;
    private Button mButton;
    private String id;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_content);
        setTitle("文章详情");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mButton = (Button) findViewById(R.id.id_chapter_content_bt_comment);
        mWebView = (WebView) findViewById(R.id.id_chapter_content_web);
        mProgressBar = (ProgressBar) findViewById(R.id.id_chapter_content_progress);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("id");
        String typeid = bundle.getString("typeid");
        String uri = bundle.getString("uri");
        String path = "http://www.3dmgame.com/sitemap/api.php?id=" + id + "&typeid=" + typeid;
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
        mWebView.loadUrl(uri);

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

    public void enterComment(View view) {
        if (id != null) {
            Intent intent = new Intent(ChapterContentActivity.this, ChapterCommentActivity.class);
            intent.putExtra("id", id);
            MyLog.d("mini", id);
            startActivity(intent);
        }
    }

    @Override
    public void updateProgress(int progress) {
        mProgressBar.setProgress(progress);
    }

    @Override
    public void updateTitle(String title) {

    }
}
