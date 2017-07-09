package com.qianqi.presenter.Impl;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.AirPlaneBeanByStation.AirPlaneInfoByStationResult;
import com.qianqi.model.IGetAirPlaneInfoByStationModel;
import com.qianqi.model.IMPL.IGetAirPlaneInfoByStationModelImpl;
import com.qianqi.presenter.IGetAirPlaneInfoByStationPresenter;
import com.qianqi.view.GetAirPlaneInfoByStation;

import java.util.List;

/**
 * Created by p on 2017/3/16.
 */

public class IGetAirPlaneInfoByStationPresenterImpl implements IGetAirPlaneInfoByStationPresenter {
    private GetAirPlaneInfoByStation view;
    private IGetAirPlaneInfoByStationModel model;

    public IGetAirPlaneInfoByStationPresenterImpl(GetAirPlaneInfoByStation view) {
        this.view = view;
        this.model = new IGetAirPlaneInfoByStationModelImpl();
    }

    @Override
    public void getAirPlaneInfoByStation(String start, String end,String date) {
        model.getAirPlaneInfoByStation(start, end, date,new AsyncCallBack() {
            @Override
            public void OnSucess(Object object) {
                List<AirPlaneInfoByStationResult> results = (List<AirPlaneInfoByStationResult>) object;
                view.getAirPlaneInfoByStationSuccess(results);
            }

            @Override
            public void OnError(String Msg) {
                view.getAirPlaneInfoByStationFail(Msg);
            }
        });
    }
}
