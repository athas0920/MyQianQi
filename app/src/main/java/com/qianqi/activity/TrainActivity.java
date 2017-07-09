package com.qianqi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qianqi.bean.Train.TrainInfoResult;
import com.qianqi.presenter.IGetTrainInfoByTrainNamePresenter;
import com.qianqi.presenter.Impl.IGetTrainInfoByTrainNamePresenterImpl;
import com.qianqi.view.GetTrainInfoByTrainName;
import com.qianqi.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by p on 2017/3/15.
 */

public class TrainActivity extends AppCompatActivity implements GetTrainInfoByTrainName {
    @InjectView(R.id.train_input)
    EditText train_input;
    @InjectView(R.id.train_search)
    Button search;
    IGetTrainInfoByTrainNamePresenter presenter = new IGetTrainInfoByTrainNamePresenterImpl(this);
    TrainInfoResult result;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_activity);
        ButterKnife.inject(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.IGetTrainInfoByTrainNamePresenter(train_input.getText().toString().trim());
            }
        });
    }

    @Override
    public void getTrainInfoByTrainNameSuccess(TrainInfoResult result) {
        this.result = result;
        Log.i("name", "getTrainInfoByTrainName: "+result.station_list.get(0).station_name);
    }

    @Override
    public void getTrainInfoByTrainNameFaile(String error_Msg) {
        Log.i("TrainError_Msg", "getTrainInfoByTrainNameFail: "+error_Msg);

    }
}
