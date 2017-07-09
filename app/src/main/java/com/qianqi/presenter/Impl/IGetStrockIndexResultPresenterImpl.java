package com.qianqi.presenter.Impl;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.StrockIndex.StrockIndexResult;
import com.qianqi.model.IGetStrockIndexModel;
import com.qianqi.model.IMPL.IGetStrockIndexModelImpl;
import com.qianqi.presenter.IGetStrockInfoPresenter;
import com.qianqi.view.GetStrockIndex;

/**
 * Created by p on 2017/3/17.
 */

public class IGetStrockIndexResultPresenterImpl implements IGetStrockInfoPresenter {
    private GetStrockIndex view;
    private IGetStrockIndexModel model;

    public IGetStrockIndexResultPresenterImpl(GetStrockIndex view) {
        this.view = view;
        this.model = new IGetStrockIndexModelImpl();
    }

    @Override
    public void getStrockInfo(int type) {
        model.getStrockIndex(type, new AsyncCallBack() {
            @Override
            public void OnSucess(Object object) {
                StrockIndexResult result= (StrockIndexResult) object;
                view.getStrockIndexSuccess(result);
            }

            @Override
            public void OnError(String Msg) {
                view.getStrockIndexFial(Msg);
            }
        });
    }
}
