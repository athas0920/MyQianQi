package com.qianqi.view;

import com.qianqi.bean.WeatherBean.Result;

/**
 * Created by p on 2017/3/14.
 */

public interface ShowWeatherInfo {
    public  void showWeatherInfo(Result result);
    public void showErrorInfo(String error_Msg);
}
