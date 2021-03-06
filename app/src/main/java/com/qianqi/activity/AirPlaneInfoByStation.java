package com.qianqi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qianqi.R;
import com.qianqi.bean.AirPlaneBeanByStation.AirPlaneInfoByStationResult;
import com.qianqi.presenter.IGetAirPlaneInfoByStationPresenter;
import com.qianqi.presenter.Impl.IGetAirPlaneInfoByStationPresenterImpl;
import com.qianqi.view.GetAirPlaneInfoByStation;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by p on 2017/3/16.
 */

public class AirPlaneInfoByStation extends AppCompatActivity implements GetAirPlaneInfoByStation{
    @InjectView(R.id.airplanebystation_start)
    EditText start;
    @InjectView(R.id.airplanebystation_end)
    EditText end;
    @InjectView(R.id.airplanebystation_query)
    Button query;
    IGetAirPlaneInfoByStationPresenter presenter = new IGetAirPlaneInfoByStationPresenterImpl(this);
    List<AirPlaneInfoByStationResult> results =new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airplanebystationactivity);
        ButterKnife.inject(this);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getAirPlaneInfoByStation(start.getText().toString().trim(),end.getText().toString().trim(),"");
            }
        });

    }

    @Override
    public void getAirPlaneInfoByStationSuccess(List<AirPlaneInfoByStationResult> results) {
        this.results = results;
        Log.i("ArrCity", "getAirPlaneInfoByStationArrCity: "+results.get(0).ArrCity);
    }

    @Override
    public void getAirPlaneInfoByStationFail(String error_Msg) {
        Log.i("airinfobystationfail", "getAirPlaneInfoByStationFail: "+error_Msg);
    }
}
