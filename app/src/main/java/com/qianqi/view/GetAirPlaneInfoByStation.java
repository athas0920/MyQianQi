package com.qianqi.view;

import com.qianqi.bean.AirPlaneBeanByStation.AirPlaneInfoByStationResult;

import java.util.List;

/**
 * Created by p on 2017/3/16.
 */

public interface GetAirPlaneInfoByStation {
    public void getAirPlaneInfoByStationSuccess(List<AirPlaneInfoByStationResult> results);
    public void getAirPlaneInfoByStationFail(String error_Msg);
}
