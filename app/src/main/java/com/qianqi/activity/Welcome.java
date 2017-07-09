package com.qianqi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.qianqi.R;
import com.qianqi.bean.EveryHourWeather.EveryHourResult;
import com.qianqi.bean.StrockIndex.StrockIndexResult;
import com.qianqi.bean.WeatherBean.Result;
import com.qianqi.presenter.IGetEveryHourWeatherPresenter;
import com.qianqi.presenter.IGetStrockInfoPresenter;
import com.qianqi.presenter.IShowWeatherInfoPresenter;
import com.qianqi.presenter.Impl.IGetEveryHourWeatherPresenterImpl;
import com.qianqi.presenter.Impl.IGetStrockIndexResultPresenterImpl;
import com.qianqi.presenter.Impl.IShowWeatherInfoPresenterImpl;
import com.qianqi.utils.TimeFormatUtils;
import com.qianqi.view.GetEveryHourWeatherInfo;
import com.qianqi.view.GetStrockIndex;
import com.qianqi.view.ShowWeatherInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by p on 2017/3/24.
 */

public class Welcome extends AppCompatActivity implements GetStrockIndex, ShowWeatherInfo, GetEveryHourWeatherInfo {
    IGetStrockInfoPresenter presenter = new IGetStrockIndexResultPresenterImpl(this);
    int typeSHANGHAI = 0;
    int typeSHENZHEN = 1;
    List<StrockIndexResult> results = new ArrayList<>();
    Result result;
    EveryHourResult everyHourResult;
    IShowWeatherInfoPresenter presenter_weather = new IShowWeatherInfoPresenterImpl(this);
    IGetEveryHourWeatherPresenter presenter_byHour = new IGetEveryHourWeatherPresenterImpl(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomeactivity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWeatherInfo();
        getEconomicData();
    }

    private void getWeatherInfo() {
        //get weather info
        Date date = new Date();
        String CurrentTime = TimeFormatUtils.formatDate(date);
        Log.i("CurrentTime", "CurrentTime: " + CurrentTime);
        String currentTime = CurrentTime.substring(0, 4) + CurrentTime.substring(5, 7) + CurrentTime.substring(8);
        Log.i("currenttime", "currenttime: " + currentTime);
        presenter_byHour.getEveryHourWeatherPresenter(currentTime + "00", currentTime + "24");
        presenter_weather.showWeatherInfoPresenter();
    }

    private void getEconomicData() {
        //get economic data
        presenter.getStrockInfo(typeSHENZHEN);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                presenter.getStrockInfo(typeSHANGHAI);
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.currentThread().sleep(5000);
//                    startActivity(new Intent(Welcome.this, FrameActivity.class));
                    Intent intent = new Intent(Welcome.this, FrameActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("strockIndex", (Serializable) results);
                    b.putSerializable("weather", result);
                    b.putSerializable("everyHourweather", everyHourResult);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void getStrockIndexSuccess(StrockIndexResult result) {
        if (result != null) {
            results.add(result);
            Log.i("results.size", "getStrockIndexSuccess: " + results.size());
        }
    }

    @Override
    public void getStrockIndexFial(String Msg) {

    }

    @Override
    public void showWeatherInfo(Result result) {
        if (result != null) {
            this.result = result;
        }

    }

    @Override
    public void showErrorInfo(String error_Msg) {

    }

    @Override
    public void getWeatherInfoSuccess(EveryHourResult result) {
        if (result != null) {
            this.everyHourResult = result;
        }
    }

    @Override
    public void getWeatherInfoFail(String Msg) {

    }
}
