package com.qianqi.presenter.Impl;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.WeatherBean.Result;
import com.qianqi.model.IGetWeatherInfoModel;
import com.qianqi.model.IMPL.IGetWeatherInfoModelImpl;
import com.qianqi.presenter.IShowWeatherInfoPresenter;
import com.qianqi.view.ShowWeatherInfo;

/**
 * Created by p on 2017/3/14.
 */

public class IShowWeatherInfoPresenterImpl implements IShowWeatherInfoPresenter {
     private ShowWeatherInfo view;
     private IGetWeatherInfoModel model;

    public IShowWeatherInfoPresenterImpl(ShowWeatherInfo view) {
        this.view = view;
        this.model = new IGetWeatherInfoModelImpl();
    }

    @Override
    public void showWeatherInfoPresenter() {
        model.getWeatherInfo( new AsyncCallBack() {
            @Override
            public void OnSucess(Object object) {
                Result result = (Result) object;
                view.showWeatherInfo(result);
            }

            @Override
            public void OnError(String Msg) {
                view.showErrorInfo(Msg);
            }
        });
    }
}
