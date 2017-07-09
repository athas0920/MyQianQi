package com.qianqi.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qianqi.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by p on 2017/3/21.
 */

public class FrameActivity extends BaseActivity {
    @InjectView(R.id.city_fragment)
    View city_Fragment;
    @InjectView(R.id.my_home)
    View my_home;
    @InjectView(R.id.myhome_title)
    TextView myhome_title;
    @InjectView(R.id.city_title)
    TextView city_title;
    @InjectView(R.id.btn_city)
    ImageButton btn_city;
    @InjectView(R.id.btn_home)
    ImageButton btn_home;
    @InjectView(R.id.home_linearlayout)
    LinearLayout home_linearlayout;
    @InjectView(R.id.city_linearlayout)
    LinearLayout city_linearlayout;
    boolean isFirstLoad = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);
        initActionBar();
        setImageButtonListener();
        initViewsSetting();
    }

    private void initViewsSetting() {
        city_Fragment.setVisibility(View.VISIBLE);
        my_home.setVisibility(View.GONE);
        if (isFirstLoad) {
            btn_home.setBackgroundResource(R.drawable.per_icon);
            btn_city.setBackgroundResource(R.drawable.city_icon_pressed);
        }
    }

    private void setImageButtonListener() {
        home_linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_Fragment.setVisibility(View.GONE);
                my_home.setVisibility(View.VISIBLE);
                btn_city.setBackgroundResource(R.drawable.city_icon);
                city_title.setTextColor(Color.parseColor("#a6a6a6"));
                btn_home.setBackgroundResource(R.drawable.per_iconpressed);
                myhome_title.setTextColor(Color.parseColor("#0e9ae9"));
                isFirstLoad = false;
            }
        });
        city_linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_Fragment.setVisibility(View.VISIBLE);
                my_home.setVisibility(View.GONE);
                btn_city.setBackgroundResource(R.drawable.city_icon_pressed);
                city_title.setTextColor(Color.parseColor("#0e9ae9"));
                btn_home.setBackgroundResource(R.drawable.per_icon);
                myhome_title.setTextColor(Color.parseColor("#a6a6a6"));
                isFirstLoad = false;
            }
        });
    }
}
