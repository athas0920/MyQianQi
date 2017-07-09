package com.qianqi.model;

import com.qianqi.AsyncCallBack;

/**
 * Created by p on 2017/3/21.
 */

public interface IGetEveryHourWeatherModel {
    public void getWeatherbyHour(String startTime, String endTime, AsyncCallBack asyncCallBack);
}
