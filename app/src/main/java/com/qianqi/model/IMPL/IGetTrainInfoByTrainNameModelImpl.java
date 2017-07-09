package com.qianqi.model.IMPL;

import android.util.Log;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.Train.TrainInfoBeanByName;
import com.qianqi.model.IGetTrainInfoByTrainNameModel;
import com.qianqi.netservice.TrainSearchApiService;
import com.qianqi.utils.GlobalContants;

//import retrofit.Call;
//import retrofit.Callback;
//import retrofit.GsonConverterFactory;
//import retrofit.Response;
//import retrofit.Retrofit;

/**
 * Created by p on 2017/3/15.
 */

public class IGetTrainInfoByTrainNameModelImpl implements IGetTrainInfoByTrainNameModel {

    @Override
    public void getTrainInfoByTrainName(String name, final AsyncCallBack callBack) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(GlobalContants.getTrainInfoByTrainNameBaseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        TrainSearchApiService service = retrofit.create(TrainSearchApiService.class);
//        Call<TrainInfoBeanByName> call = service.getTrainInfoResultByTrainName(name);
//        call.enqueue(new Callback<TrainInfoBeanByName>() {
//            @Override
//            public void onResponse(Response<TrainInfoBeanByName> response, Retrofit retrofit) {
//                TrainInfoBeanByName train = response.body();
//                if(train.error_code == 0){
//                    callBack.OnSucess(train.result);
//                    Log.i("TrainInfoResult", "onResponse: "+train.result);
//                }else {
//                    callBack.OnError(train.reason);
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.i("trainonFailure", "onFailure: "+t.getMessage());
//            }
//        });
    }
}
