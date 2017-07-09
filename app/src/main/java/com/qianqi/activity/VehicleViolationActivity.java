package com.qianqi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qianqi.bean.VehicleViolationBean.VehicleViolationResult;
import com.qianqi.presenter.IGetVehicleViolationInfoPresenter;
import com.qianqi.presenter.Impl.IGetVehicleViolationInfoPresenterImpl;
import com.qianqi.view.GetVehicleViolationInfo;
import com.qianqi.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by p on 2017/3/15.
 */

public class VehicleViolationActivity extends AppCompatActivity implements GetVehicleViolationInfo {
    @InjectView(R.id.carNo)
    EditText carNo;
    @InjectView(R.id.frameNo)
    EditText frameNo;
    @InjectView(R.id.enginNo)
    EditText enginNo;
    @InjectView(R.id.vehicleviolationsearch)
    Button search;
    IGetVehicleViolationInfoPresenter presenter = new IGetVehicleViolationInfoPresenterImpl(this);
    String RealNeedEnginNo;
    List<VehicleViolationResult> results;
    String reason;
    String Error_Msg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_violation_activity);
        ButterKnife.inject(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CarNo =carNo.getText().toString().trim();
                String FrameNo = frameNo.getText().toString().trim();
                if (FrameNo.length()!= 6){
                    Toast.makeText(VehicleViolationActivity.this, "输入车架号位数错误", Toast.LENGTH_SHORT).show();
                }
                if (CarNo.startsWith("宁A")){
                    String EnginNo = enginNo.getText().toString().trim();
                    RealNeedEnginNo = EnginNo.substring(3,EnginNo.length());
                }else if (CarNo.startsWith("蒙K")){
                    RealNeedEnginNo = enginNo.getText().toString().trim();
                }
                Log.i("RealNeedEnginNo", "RealNeedEnginNo: "+RealNeedEnginNo);
                presenter.IGetVehicleViolationInfoPresenter(FrameNo,RealNeedEnginNo,CarNo);
            }
        });
    }

    @Override
    public void getVehicleViolationInfoSucess(List<VehicleViolationResult> results) {
                this.results = results;
        Log.i("ViewResults", "getVehicleViolationInfoSucess: "+results.toString());
    }

    @Override
    public void getVehicleViolationInfoError(String Msg) {
            this.Error_Msg = Msg;
        Log.i("getVehicleVioFaile", "getVehicleViolationInfoError: "+Msg);
    }

    @Override
    public void NoVehicleViolationBehavior(String reason) {
        this.reason = reason;
        Log.i("noVehicleVioBehavior", "NoVehicleViolationBehavior: "+reason);
    }
}
