package com.qianqi.presenter.Impl;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.ShanghaiAndShenzhenStrock.HuShenStrockResult;
import com.qianqi.model.IGetHuShenStrockModel;
import com.qianqi.model.IMPL.IGetHuShenStrockModelImpl;
import com.qianqi.presenter.IGetHuShenStrockPresenter;
import com.qianqi.view.GetHuShenStrockResult;

import java.util.List;

/**
 * Created by p on 2017/3/17.
 */

public class IGetHuShenStrockPresenterImpl implements IGetHuShenStrockPresenter {
    private GetHuShenStrockResult view;
    private IGetHuShenStrockModel model;

    public IGetHuShenStrockPresenterImpl(GetHuShenStrockResult view) {
        this.view = view;
        this.model = new IGetHuShenStrockModelImpl();
    }

    @Override
    public void getHuShenStrockPresenter(String gid) {
        model.getHuShenStrock(gid, new AsyncCallBack() {
            @Override
            public void OnSucess(Object object) {
               List<HuShenStrockResult> results = (List<HuShenStrockResult>) object;
                view.getHuShenStrockResultSuccess(results);
            }

            @Override
            public void OnError(String Msg) {
                view.getHuShenStrockResultFail(Msg);
            }
        });
    }
}
