package com.qianqi.model.IMPL;

import android.util.Log;

import com.qianqi.AsyncCallBack;
import com.qianqi.model.IGetSportListModel;
import com.qianqi.netservice.SportListApiService;
import com.qianqi.utils.GlobalContants;

//import retrofit.Call;
//import retrofit.Callback;
//import retrofit.GsonConverterFactory;
//import retrofit.Response;
//import retrofit.Retrofit;

/**
 * Created by p on 2017/3/16.
 */

public class IGetSportListModelImpl implements IGetSportListModel {
    @Override
    public void getSportList(final AsyncCallBack callback) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(GlobalContants.getSportsList)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        SportListApiService service = retrofit.create(SportListApiService.class);
//        Call<Object> call = service.getSportList(1, 10, "", 1);
//        call.enqueue(new Callback<Object>() {
//            @Override
//            public void onResponse(Response<Object> response, Retrofit retrofit) {
//                Object o = response.body();
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.i("getairqualityfail", "onFailure: " + t.getMessage());
//            }
//        });
    }


//     new OkHttpClient.Builder()
//            .cache(cache)
//                    .addInterceptor(loggingInterceptor)
//                    .connectTimeout(mTimeOut, TimeUnit.SECONDS)
//                    .readTimeout(mTimeOut, TimeUnit.SECONDS)
//                    .writeTimeout(mTimeOut, TimeUnit.SECONDS)
//                    .build();
}
