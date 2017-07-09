package com.qianqi.model.IMPL;

import android.util.Log;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.AirPlaneBeanByName.AirPlaneInfoBeanByName;
import com.qianqi.model.IGetAirPlaneInfoByNameModel;
import com.qianqi.netservice.GetAirPlaneInfoByNameApiService;
import com.qianqi.utils.GlobalContants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by p on 2017/3/16.
 */

public class IGetAirPlaneInfoByNameModelImpl implements IGetAirPlaneInfoByNameModel {
    @Override
    public void getAirPlaneInfo(String name, String date, final AsyncCallBack asyncCallBack) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(GlobalContants.getAirPlaneInfoByNameBaseURL)
//                .build();
//        GetAirPlaneInfoByNameApiService service = retrofit.create(GetAirPlaneInfoByNameApiService.class);
//        Call<AirPlaneInfoBeanByName> call = service.getAirPlaneInfoBeanByName(name,date);
//        call.enqueue(new Callback<AirPlaneInfoBeanByName>() {
//            @Override
//            public void onResponse(Response<AirPlaneInfoBeanByName> response, Retrofit retrofit) {
//                AirPlaneInfoBeanByName airPlaneInfoBeanByName = response.body();
//                Log.i("airplaneinfobeanbyname", "onResponse: "+airPlaneInfoBeanByName);
//                Log.i("airplanequeryerrorcode", "onResponse: "+airPlaneInfoBeanByName.error_code);
//                if (airPlaneInfoBeanByName.error_code == 0){
//                    asyncCallBack.OnSucess(airPlaneInfoBeanByName.result);
//                }else {
//                    asyncCallBack.OnError(airPlaneInfoBeanByName.reason);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.i("getairplaneinfofail", "onFailure: "+t.getMessage());
//            }
//        });

    }
}
