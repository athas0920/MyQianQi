package com.qianqi.model.IMPL;

import android.util.Log;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.RealNameRegex.RealNameRegexBean;
import com.qianqi.model.IGetRealNameRegexResultModel;
import com.qianqi.netservice.GetRealNameResultApiService;
import com.qianqi.utils.GlobalContants;

//import retrofit.Call;
//import retrofit.Callback;
//import retrofit.GsonConverterFactory;
//import retrofit.Response;
//import retrofit.Retrofit;

/**
 * Created by p on 2017/3/16.
 */

public class IGetRealNameRegexResultModelImpl implements IGetRealNameRegexResultModel {
    @Override
    public void getRealNameRegexResult(String realname, String idcard, final AsyncCallBack asyncCallBack) {
//        Retrofit retrofit= new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(GlobalContants.realNameRegexBaseURL)
//                .build();
//        GetRealNameResultApiService service = retrofit.create(GetRealNameResultApiService.class);
//        Call<RealNameRegexBean> call = service.getRealNameRegexResult(realname,idcard);
//        call.enqueue(new Callback<RealNameRegexBean>() {
//            @Override
//            public void onResponse(Response<RealNameRegexBean> response, Retrofit retrofit) {
//                RealNameRegexBean realNameRegexBean = response.body();
//                Log.i("realnameregexbody", "onResponse: "+realNameRegexBean);
//                if (realNameRegexBean.error_code == 0 ){
//                    asyncCallBack.OnSucess(realNameRegexBean.result);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.i("realnameregexfail", "onFailure: "+t.getMessage());
//            }
//        });
    }
}
