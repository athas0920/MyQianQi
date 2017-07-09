package com.qianqi.model;

import com.qianqi.AsyncCallBack;

/**
 * Created by p on 2017/3/15.
 */

public interface IGetTrainInfoByStationModel {
    public  void getTrainInfo(String start, String end,String date, AsyncCallBack callBack);
}
