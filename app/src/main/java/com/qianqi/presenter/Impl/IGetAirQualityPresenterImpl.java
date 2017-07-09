package com.qianqi.presenter.Impl;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.AirQualityBean.AirQualityResult;
import com.qianqi.model.IGetAirQualityModel;
import com.qianqi.model.IMPL.IGetAirQualityModelImpl;
import com.qianqi.presenter.IGetAirQualityPresenter;
import com.qianqi.view.GetAirQuality;

/**
 * Created by p on 2017/3/16.
 */

public class IGetAirQualityPresenterImpl implements IGetAirQualityPresenter {
    private  GetAirQuality view;
    private IGetAirQualityModel  model;

    public IGetAirQualityPresenterImpl(GetAirQuality view) {
        this.view = view;
        this.model = new IGetAirQualityModelImpl();
    }

    @Override
    public void getAirQualityPresenter() {
        model.getAirQuality(new AsyncCallBack() {
            @Override
            public void OnSucess(Object object) {
                AirQualityResult result  = (AirQualityResult) object;
                view.getAirQualitySuccess(result);
            }

            @Override
            public void OnError(String Msg) {
                view.getAirQualityFail(Msg);
            }
        });
    }
}
