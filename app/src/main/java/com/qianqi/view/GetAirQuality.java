package com.qianqi.view;

import com.qianqi.bean.AirQualityBean.AirQualityResult;

/**
 * Created by p on 2017/3/16.
 */

public interface GetAirQuality {
    public void getAirQualitySuccess(AirQualityResult result);
    public void getAirQualityFail(String error_Msg);
}
