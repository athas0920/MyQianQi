package com.qianqi.view;

import com.qianqi.bean.AirQualityBean.AirQualityResult;

/**
 * Created by p on 2017/3/16.
 */

public interface GetSportList {
    public void getSportListSuccess(AirQualityResult result);
    public void getSportListFail(String error_Msg);
}
