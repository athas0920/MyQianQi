package com.qianqi.presenter.Impl;

import com.qianqi.AsyncCallBack;
import com.qianqi.model.IGetSportListModel;
import com.qianqi.model.IMPL.IGetSportListModelImpl;
import com.qianqi.presenter.IGetSportListPresenter;
import com.qianqi.view.GetSportList;

/**
 * Created by p on 2017/3/16.
 */

public class IGetSportListPresenterImpl implements IGetSportListPresenter {
    private GetSportList view;
    private IGetSportListModel model;

    public IGetSportListPresenterImpl(GetSportList view) {
        this.view = view;
        this.model = new IGetSportListModelImpl();
    }

    @Override
    public void getSportListPresenter() {
        model.getSportList(new AsyncCallBack() {
            @Override
            public void OnSucess(Object object) {
                //   AirQualityResult result = (AirQualityResult) object;
                // view.getAirQualitySuccess(result);
            }

            @Override
            public void OnError(String Msg) {
                view.getSportListFail(Msg);
            }
        });
    }
}
