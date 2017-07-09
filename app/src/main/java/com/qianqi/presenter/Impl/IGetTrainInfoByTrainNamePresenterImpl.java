package com.qianqi.presenter.Impl;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.Train.TrainInfoResult;
import com.qianqi.model.IGetTrainInfoByTrainNameModel;
import com.qianqi.model.IMPL.IGetTrainInfoByTrainNameModelImpl;
import com.qianqi.presenter.IGetTrainInfoByTrainNamePresenter;
import com.qianqi.view.GetTrainInfoByTrainName;

/**
 * Created by p on 2017/3/15.
 */

public class IGetTrainInfoByTrainNamePresenterImpl implements IGetTrainInfoByTrainNamePresenter {
    private GetTrainInfoByTrainName view;
    private IGetTrainInfoByTrainNameModel model;
    public IGetTrainInfoByTrainNamePresenterImpl(GetTrainInfoByTrainName view) {
        this.view = view;
        this.model = new IGetTrainInfoByTrainNameModelImpl();
    }

    @Override
    public void IGetTrainInfoByTrainNamePresenter(String name) {
        model.getTrainInfoByTrainName(name, new AsyncCallBack() {
            @Override
            public void OnSucess(Object object) {
                TrainInfoResult results= (TrainInfoResult) object;
                view.getTrainInfoByTrainNameSuccess(results);
            }

            @Override
            public void OnError(String Msg) {
                view.getTrainInfoByTrainNameFaile(Msg);
            }
        });
    }
}
