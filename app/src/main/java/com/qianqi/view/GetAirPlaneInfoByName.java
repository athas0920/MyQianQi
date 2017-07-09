package com.qianqi.view;

import com.qianqi.bean.AirPlaneBeanByName.AirPlaneInfoResult;

/**
 * Created by p on 2017/3/16.
 */

public interface GetAirPlaneInfoByName {
    public void getAirPlaneInfoByNameSuccess(AirPlaneInfoResult result);
    public void getAirPlaneInfoByNameFail(String error_Msg);
}
