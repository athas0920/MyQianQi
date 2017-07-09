package com.qianqi.model.IMPL;

import android.util.Log;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.ShanghaiAndShenzhenStrock.HuShenStrockBean;
import com.qianqi.model.IGetHuShenStrockModel;
import com.qianqi.netservice.HuShenStrockApiService;
import com.qianqi.utils.GlobalContants;



/**
 * Created by p on 2017/3/17.
 */

public class IGetHuShenStrockModelImpl implements IGetHuShenStrockModel {
    @Override
    public void getHuShenStrock(String pid, final AsyncCallBack asyncCallBack) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(GlobalContants.getStrockIndexBaseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        HuShenStrockApiService service = retrofit.create(HuShenStrockApiService.class);
//        Call<HuShenStrockBean> call= service.getHuShenStrockBean(pid);
//        call.enqueue(new Callback<HuShenStrockBean>() {
//            @Override
//            public void onResponse(Response<HuShenStrockBean> response, Retrofit retrofit) {
//                HuShenStrockBean huShenStrockBean = response.body();
//                Log.i("hushenstrockbeanbody", "onResponse: "+huShenStrockBean);
//                if (huShenStrockBean.error_code == 0){
//                    asyncCallBack.OnSucess(huShenStrockBean.result);
//                }else {
//                    asyncCallBack.OnError(huShenStrockBean.reason);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.i("hushenstrockfail", "onFailure: "+t.getMessage());
//            }
//        });
    }
}
