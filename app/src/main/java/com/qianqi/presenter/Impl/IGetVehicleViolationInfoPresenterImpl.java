package com.qianqi.presenter.Impl;

import com.qianqi.AsyncCallBack;
import com.qianqi.bean.VehicleViolationBean.VehicleViolationResult;
import com.qianqi.model.IGetVehicleViolationInfoModel;
import com.qianqi.model.IMPL.IGetVehicleViolationInfoModelImpl;
import com.qianqi.presenter.IGetVehicleViolationInfoPresenter;
import com.qianqi.utils.GlobalContants;
import com.qianqi.view.GetVehicleViolationInfo;

import java.util.List;

/**
 * Created by p on 2017/3/15.
 */

public class IGetVehicleViolationInfoPresenterImpl implements IGetVehicleViolationInfoPresenter {
    private GetVehicleViolationInfo view;
    private IGetVehicleViolationInfoModel model;

    public IGetVehicleViolationInfoPresenterImpl(GetVehicleViolationInfo view) {
        this.view = view;
        this.model = new IGetVehicleViolationInfoModelImpl();
    }

    @Override
    public void IGetVehicleViolationInfoPresenter(String frameNo, String engineNo, String carNo) {
        model.getVehicleViolationInfo( frameNo,engineNo,carNo,new AsyncCallBack() {
            @Override
            public void OnSucess(Object object) {
                    if (GlobalContants.NoVehicleViolationBehavior){
                        String reason = (String) object;
                        view.NoVehicleViolationBehavior(reason);
                    }else {
                        List<VehicleViolationResult> results = (List<VehicleViolationResult>) object;
                        view.getVehicleViolationInfoSucess(results);
                    }
            }

            @Override
            public void OnError(String Msg) {
                        view.getVehicleViolationInfoError(Msg);
            }
        });
    }


}
