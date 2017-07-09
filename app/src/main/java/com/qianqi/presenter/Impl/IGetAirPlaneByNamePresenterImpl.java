package com.qianqi.presenter.Impl;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.AirPlaneBeanByName.AirPlaneInfoResult;
import com.qianqi.model.IGetAirPlaneInfoByNameModel;
import com.qianqi.model.IMPL.IGetAirPlaneInfoByNameModelImpl;
import com.qianqi.presenter.IGetAirPlaneInfoByNamePresenter;
import com.qianqi.view.GetAirPlaneInfoByName;

/**
 * Created by p on 2017/3/16.
 */

public class IGetAirPlaneByNamePresenterImpl implements IGetAirPlaneInfoByNamePresenter {
    private GetAirPlaneInfoByName view;
    private IGetAirPlaneInfoByNameModel model;

    public IGetAirPlaneByNamePresenterImpl(GetAirPlaneInfoByName view) {
        this.view = view;
        this.model = new IGetAirPlaneInfoByNameModelImpl();
    }

    @Override
    public void getAirPlaneInfoByNamePresenter(String name, String date) {
        model.getAirPlaneInfo(name, date, new AsyncCallBack() {
            @Override
            public void OnSucess(Object object) {
                AirPlaneInfoResult result = (AirPlaneInfoResult) object;
                view.getAirPlaneInfoByNameSuccess(result);
            }

            @Override
            public void OnError(String Msg) {
                view.getAirPlaneInfoByNameFail(Msg);
            }
        });
    }
}
