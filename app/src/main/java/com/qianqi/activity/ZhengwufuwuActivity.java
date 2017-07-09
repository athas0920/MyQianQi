package com.qianqi.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qianqi.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by p on 2017/4/3.
 */

public class ZhengwufuwuActivity extends BaseActivity {
    @InjectView(R.id.zhinengwenda)
    ImageView zhinengwenda;
    @InjectView(R.id.changjianwenti)
    ImageView changjianwenti;
    @InjectView(R.id.banshizhinan)
    ImageView banshizhinan;
    @InjectView(R.id.banshijindu)
    ImageView banshijindu;
    @InjectView(R.id.xinjiandafu)
    ImageView xinjiandafu;
    @InjectView(R.id.wangzhantongji)
    ImageView wangzhantongji;
    @InjectView(R.id.zhaiquanqingdan)
    ImageView zhaiquanqingdan;
    @InjectView(R.id.quanliqingdan)
    ImageView quanliqingdan;
    @InjectView(R.id.banjiangonggao)
    ImageView banjiangonggao;
    @InjectView(R.id.zhongdianshixiang)
    ImageView zhongdianshixiang;
    @InjectView(R.id.wangzhanfabu)
    ImageView wangzhanfabu;
    @InjectView(R.id.zhuizhibumen)
    ImageView chuiguanbumen;
    @InjectView(R.id.xingzhengshenpi)
    ImageView xingzhengshenpi;
    @InjectView(R.id.gerenbanshi)
    ImageView gerenbanshi;
    @InjectView(R.id.farenbanshi)
    ImageView farenbanshi;
    @InjectView(R.id.woyaotousu)
    ImageView woyaotousu;

    @InjectView(R.id.news_webpage_back)
    LinearLayout back;
    @InjectView(R.id.titletxt)
    TextView titleTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhengwudating);
        ButterKnife.inject(this);
        initView();
        //initActionBar();
        setButtonListener();
    }

    private void initView() {
        titleTxt.setText("政务大厅");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setButtonListener() {
        zhinengwenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/intelligentQueAns.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
        changjianwenti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/showFAQsQueryListFrame.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
        banshizhinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/guideListIndex.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
        banshijindu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/initCaseProgress.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
        xinjiandafu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/replyMail.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
        wangzhantongji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/websiteStatistics.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
        zhaiquanqingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/departmentList.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
        quanliqingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/initPowerList.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
        banjiangonggao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/initYgzwShenpiIndex.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
        zhongdianshixiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://xxgk.etkqq.gov.cn/zfbm_59329/qzfjbsjj/zfbgs/"));
                startActivity(i);
            }
        });
        wangzhanfabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/webPublish.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
        chuiguanbumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/initPowerLists.action?districtId=420000&isVerticalDept=1"));
                startActivity(i);
            }
        });
        xingzhengshenpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/initXzspPageIndex.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
        gerenbanshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/grbsIndex.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
        farenbanshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/frbsIndex.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
        woyaotousu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://zwfw.ordos.gov.cn/tsInit.action?districtId=8a96998a48f7fb180148f9037cbc010c"));
                startActivity(i);
            }
        });
    }
}
