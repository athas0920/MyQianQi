package com.qianqi.model;

import com.qianqi.AsyncCallBack;

/**
 * Created by p on 2017/3/16.
 */

public interface IGetAirPlaneInfoByNameModel {
    public void getAirPlaneInfo(String name, String date, AsyncCallBack asyncCallBack);
}
