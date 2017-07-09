package com.qianqi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.qianqi.bean.WeatherBean.Result;
import com.qianqi.presenter.IShowWeatherInfoPresenter;
import com.qianqi.presenter.Impl.IShowWeatherInfoPresenterImpl;
import com.qianqi.view.ShowWeatherInfo;
import com.qianqi.R;

public class MainActivity extends AppCompatActivity implements ShowWeatherInfo {
    private Result result;
    IShowWeatherInfoPresenter presenter = new IShowWeatherInfoPresenterImpl(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.showWeatherInfoPresenter();
        setContentView(R.layout.blank_activity);

    }

    @Override
    public void showWeatherInfo(Result result) {
        this.result = result;
        Log.i("MainActivityweather", "showWeatherInfo: "+result.data.cw);
    }

    @Override
    public void showErrorInfo(String error_Msg) {

    }
}
