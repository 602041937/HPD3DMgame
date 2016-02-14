package com.hpdxay.hpd3dmgame;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MyWebChromeClient extends WebChromeClient {

    private WebSupport mSupport;

    public MyWebChromeClient(WebSupport support) {
        mSupport = support;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (mSupport != null) {
            mSupport.updateTitle(title);
        }
    }

    /**
     * WebView加载网页的时候，会有一个进度的提示，这个提示，通过这个方法可以收到
     * @param view
     * @param newProgress 百分比的数值
     */
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (mSupport != null) {
            mSupport.updateProgress(newProgress);
        }
    }
}
