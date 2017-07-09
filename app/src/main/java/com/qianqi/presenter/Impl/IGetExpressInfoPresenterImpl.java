package com.qianqi.presenter.Impl;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.Express.ExpressResult;
import com.qianqi.model.IGetExpressInfoModel;
import com.qianqi.model.IMPL.IGetExpressInfoModelImpl;
import com.qianqi.presenter.IGetExpresssInfoPresenter;
import com.qianqi.view.GetExpressInfo;

/**
 * Created by p on 2017/3/16.
 */

public class IGetExpressInfoPresenterImpl implements IGetExpresssInfoPresenter {
    private GetExpressInfo view;
    private IGetExpressInfoModel model;

    public IGetExpressInfoPresenterImpl(GetExpressInfo view) {
        this.view = view;
        this.model = new IGetExpressInfoModelImpl();
    }

    @Override
    public void getExpressInfoPresenter(String com, String no) {
        model.getExpressInfo(com, no, new AsyncCallBack() {
            @Override
            public void OnSucess(Object object) {
                ExpressResult result = (ExpressResult) object;
                view.getExpressInfoSuccess(result);
            }

            @Override
            public void OnError(String Msg) {
                view.getExpressInfoFail(Msg);
            }
        });
    }
}
