package com.qianqi.presenter.Impl;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.EveryHourWeather.EveryHourResult;
import com.qianqi.model.IGetEveryHourWeatherModel;
import com.qianqi.model.IMPL.IGetEveryHourWeatherModelImpl;
import com.qianqi.presenter.IGetEveryHourWeatherPresenter;
import com.qianqi.view.GetEveryHourWeatherInfo;

/**
 * Created by p on 2017/3/21.
 */

public class IGetEveryHourWeatherPresenterImpl implements IGetEveryHourWeatherPresenter {


    private GetEveryHourWeatherInfo view;
    private IGetEveryHourWeatherModel model;
    public IGetEveryHourWeatherPresenterImpl(GetEveryHourWeatherInfo view) {
        this.view = view;
        this.model = new IGetEveryHourWeatherModelImpl();
    }
    @Override
    public void getEveryHourWeatherPresenter(String startTime, String endTime) {
       model.getWeatherbyHour(startTime, endTime, new AsyncCallBack() {
           @Override
           public void OnSucess(Object object) {
               EveryHourResult result = (EveryHourResult) object;
               view.getWeatherInfoSuccess(result);
           }

           @Override
           public void OnError(String Msg) {
                view.getWeatherInfoFail(Msg);
           }
       });
    }
}
