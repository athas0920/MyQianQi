package com.qianqi.view;

import com.qianqi.bean.Train.TrainInfoResult;

/**
 * Created by p on 2017/3/15.
 */

public interface GetTrainInfoByTrainName {
   public void getTrainInfoByTrainNameSuccess(TrainInfoResult results);
   public void getTrainInfoByTrainNameFaile(String error_Msg);
}
