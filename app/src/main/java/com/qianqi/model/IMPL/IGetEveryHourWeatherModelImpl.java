package com.qianqi.model.IMPL;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.EveryHourWeather.EveryHourWeatherBean;
import com.qianqi.model.IGetEveryHourWeatherModel;
import com.qianqi.netservice.EveryHourWeatherInfoApiService;
import com.qianqi.utils.GlobalContants;



/**
 * Created by p on 2017/3/21.
 */

public class IGetEveryHourWeatherModelImpl implements IGetEveryHourWeatherModel {
    @Override
    public void getWeatherbyHour(String startTime, String endTime, final AsyncCallBack asyncCallBack) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(GlobalContants.getEveryHourWeatherBaseURL)
//                .build();
//        EveryHourWeatherInfoApiService service = retrofit.create(EveryHourWeatherInfoApiService.class);
//        Call<EveryHourWeatherBean> call = service.getEveryHourWeather(startTime,endTime);
//        call.enqueue(new Callback<EveryHourWeatherBean>() {
//            @Override
//            public void onResponse(Response<EveryHourWeatherBean> response, Retrofit retrofit) {
//                EveryHourWeatherBean everyHourWeatherBean = response.body();
//                if (everyHourWeatherBean.error_code == 0){
//                    asyncCallBack.OnSucess(everyHourWeatherBean.result);
//                }else {
//                    asyncCallBack.OnError(everyHourWeatherBean.reason);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        });
    }
}
