package com.qianqi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.qianqi.bean.AirQualityBean.AirQualityResult;
import com.qianqi.bean.AirQualityBean.Kdatalistey;
import com.qianqi.presenter.IGetAirQualityPresenter;
import com.qianqi.presenter.Impl.IGetAirQualityPresenterImpl;
import com.qianqi.R;
import com.qianqi.view.GetAirQuality;

import java.util.List;

/**
 * Created by p on 2017/3/16.
 */

public class AirQualityActivity extends AppCompatActivity implements GetAirQuality {
    IGetAirQualityPresenter presenter = new IGetAirQualityPresenterImpl(this);
    AirQualityResult result ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_home_fragment);
        presenter.getAirQualityPresenter();
    }

    @Override
    public void getAirQualitySuccess(AirQualityResult result) {
        this.result = result;
        Log.i("beginTime", "beginTime: "+System.currentTimeMillis());
        List<Kdatalistey> kdatalisteys = result.kdatalistey;
        for (Kdatalistey kdatalistey: kdatalisteys
             ) {
            if (kdatalistey.areaid.equals("101080705")){
                Log.i("AirQualityaqi", "getAirQualityApi: "+kdatalistey.aqi);
                Log.i("AirQualityCO", "getAirQualityCO: "+kdatalistey.co);
            };
        }
    }

    @Override
    public void getAirQualityFail(String error_Msg) {
        Log.i("getAirQualityFail", "getAirQualityFail: "+error_Msg);
    }
}
