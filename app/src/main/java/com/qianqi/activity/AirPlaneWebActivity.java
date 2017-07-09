package com.qianqi.activity;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qianqi.R;
import com.qianqi.utils.GlobalContants;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by p on 2017/3/22.
 */

public class AirPlaneWebActivity extends AppCompatActivity {
    @InjectView(R.id.webview_fun)
    WebView webView;
    @InjectView(R.id.news_webpage_back)
    LinearLayout back;
    @InjectView(R.id.titletxt)
    TextView titletxt;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplaneweb);
        ButterKnife.inject(this);
        titletxt.setText("前旗机票");

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
        webView.loadUrl("http://m.ctrip.com/");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        GlobalContants.FirstClick = false;
    }
}
