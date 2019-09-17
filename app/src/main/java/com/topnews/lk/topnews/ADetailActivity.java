package com.topnews.lk.topnews;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ADetailActivity extends AppCompatActivity {

    Unbinder unbinder;
    @BindView(R.id.wb_news)
    WebView webNews;
    private String loadUrl,title;
    private WebViewClient webViewClient;
    @BindView(R.id.tv_bar_title)
    TextView tv_bar_title;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.pb_load)
    ProgressBar pb_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adetail);
        unbinder = ButterKnife.bind(this);
        loadUrl = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        initView();
        setWebViewClient();
    }

    private void setWebViewClient() {
        webViewClient = new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pb_load.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pb_load.setVisibility(View.GONE);
            }
        };
        webNews.setWebViewClient(webViewClient);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView(){
        webNews.getSettings().setJavaScriptEnabled(true);
        webNews.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webNews.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webNews.canGoBack();
        webNews.canGoForward();
        webNews.loadUrl(loadUrl);
        tv_bar_title.setText(title);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webNews.destroy();
        unbinder.unbind();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && webNews.canGoBack()){
            webNews.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
