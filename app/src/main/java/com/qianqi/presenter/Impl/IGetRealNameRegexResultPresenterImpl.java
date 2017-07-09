package com.qianqi.presenter.Impl;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.RealNameRegex.RealNameRegexResult;
import com.qianqi.model.IGetRealNameRegexResultModel;
import com.qianqi.model.IMPL.IGetRealNameRegexResultModelImpl;
import com.qianqi.presenter.IGetRealNameRegexResultPresenter;
import com.qianqi.view.GetRealNameRegexResult;

/**
 * Created by p on 2017/3/16.
 */

public class IGetRealNameRegexResultPresenterImpl implements IGetRealNameRegexResultPresenter {
    private GetRealNameRegexResult view;
    private IGetRealNameRegexResultModel model;

    public IGetRealNameRegexResultPresenterImpl(GetRealNameRegexResult view) {
        this.view = view;
        this.model = new IGetRealNameRegexResultModelImpl();
    }

    @Override
    public void getRealNameRegexResultPresenter(String realname, String idcard) {
        model.getRealNameRegexResult(realname, idcard, new AsyncCallBack() {
            @Override
            public void OnSucess(Object object) {
                RealNameRegexResult result = (RealNameRegexResult) object;
                view.getRealNameRegexSuccess(result);

            }

            @Override
            public void OnError(String Msg) {

            }
        });
    }
}
