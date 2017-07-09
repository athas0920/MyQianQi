package com.qianqi.presenter.Impl;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.QueryTrainInfoByStation.TrainInfoByStationResultList;
import com.qianqi.model.IGetTrainInfoByStationModel;
import com.qianqi.model.IMPL.IGetTrainInfoByStationModelImpl;
import com.qianqi.presenter.IGetTrainInfoByStationPresenter;
import com.qianqi.view.GetTrainInfoByStation;

import java.util.List;

/**
 * Created by p on 2017/3/15.
 */

public class IGetTrainInfoByStationPresenterImpl implements IGetTrainInfoByStationPresenter {
    private GetTrainInfoByStation view;
    private IGetTrainInfoByStationModel model;

    public IGetTrainInfoByStationPresenterImpl(GetTrainInfoByStation view) {
        this.view = view;
        this.model = new IGetTrainInfoByStationModelImpl();
    }

    @Override
    public void getTrainInfoByStation(String start, String end,String date) {
            model.getTrainInfo(start, end, date,new AsyncCallBack() {
                @Override
                public void OnSucess(Object object) {
                    List<TrainInfoByStationResultList> lists = (List<TrainInfoByStationResultList>) object;
                    view.getTrainInfoByStationSuccess(lists);
                }

                @Override
                public void OnError(String Msg) {
                    view.getTrainInfoByStationFail(Msg);
                }
            });
    }
}
