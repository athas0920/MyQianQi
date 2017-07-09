package com.qianqi.activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qianqi.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 14041578 on 2017/4/14.
 */

public class SearchActivity extends AppCompatActivity {
    @InjectView(R.id.webview)
    WebView webView;
    @InjectView(R.id.news_webpage_back)
    LinearLayout back;
    @InjectView(R.id.titletxt)
    TextView titletxt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);

        titletxt.setText("前旗搜索");

        String str = getIntent().getStringExtra("searchtxt");
        WebSettings webSettings = webView.getSettings();//获得WebView的设置
        webSettings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);//适配
        webSettings.setJavaScriptEnabled(true);  //支持js
        //添加下面2行代码来忽略SSL验证
        WebViewClient mWebviewclient = new WebViewClient() {
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        };
        webView.setWebViewClient(mWebviewclient);
        webView.loadUrl("https://m.baidu.com/ssid=7c01b2a6cfdfb5db0909/s?word=" + str + "&ts=0150396&t_kt=0&ie=utf-8&rsv_iqid=2785745695&rsv_t=7f633x%252Bumf1ChXsRC6bJtXmGppDqTTwUKJs9gd97%252FEEJbjKgICFA&sa=ib&rsv_pq=2785745695&rsv_sug4=4668&ss=101&inputT=2210");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // traynum
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                && event.getAction() == KeyEvent.ACTION_UP) {
            finish();
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            return false;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
