package com.qianqi.view;

import com.qianqi.bean.QueryTrainInfoByStation.TrainInfoByStationResultList;

import java.util.List;

/**
 * Created by p on 2017/3/15.
 */

public interface GetTrainInfoByStation {
    public void getTrainInfoByStationSuccess(List<TrainInfoByStationResultList> lists);
    public void getTrainInfoByStationFail(String Msg);
}
