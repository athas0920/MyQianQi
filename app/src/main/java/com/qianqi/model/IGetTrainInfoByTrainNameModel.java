package com.qianqi.model;

import com.qianqi.AsyncCallBack;

/**
 * Created by p on 2017/3/15.
 */

public interface IGetTrainInfoByTrainNameModel {
    public void getTrainInfoByTrainName(String name, AsyncCallBack callBack);
}
