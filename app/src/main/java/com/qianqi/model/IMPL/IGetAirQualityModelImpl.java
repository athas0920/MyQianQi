package com.qianqi.model.IMPL;

import android.util.Log;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.AirQualityBean.AirQualityBean;
import com.qianqi.model.IGetAirQualityModel;
import com.qianqi.netservice.AirQualityApiService;
import com.qianqi.utils.GlobalContants;

/**
 * Created by p on 2017/3/16.
 */

public class IGetAirQualityModelImpl implements IGetAirQualityModel {
    @Override
    public void getAirQuality(final AsyncCallBack callback) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(GlobalContants.getAirQualityBaseURL)
//                .build();
//        AirQualityApiService service = retrofit.create(AirQualityApiService.class);
//        Call<AirQualityBean> call = service.getAllAirQuality();
//        call.enqueue(new Callback<AirQualityBean>() {
//            @Override
//            public void onResponse(Response<AirQualityBean> response, Retrofit retrofit) {
//                AirQualityBean airQualityBean = response.body();
//                Log.i("airQualityBean", "onResponse: "+airQualityBean);
//                if (airQualityBean.error_code == 0){
//                    callback.OnSucess(airQualityBean.result);
//                }else {
//                    callback.OnError(airQualityBean.reason);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.i("getairqualityfail", "onFailure: "+t.getMessage());
//            }
//        });
    }


}
