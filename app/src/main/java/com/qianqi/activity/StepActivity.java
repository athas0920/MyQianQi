package com.qianqi.activity;

/**
 * Created by 14041578 on 2017/4/14.
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qianqi.R;
import com.qianqi.RxBus.Events;
import com.qianqi.RxBus.RxBus;
import com.qianqi.adapter.StepAdapter;
import com.qianqi.bean.AirQualityBean.AirQualityResult;
import com.qianqi.bean.SportListBean.SportListBean;
import com.qianqi.bean.SportListBean.SportRankBean;
import com.qianqi.presenter.IGetSportListPresenter;
import com.qianqi.presenter.Impl.IGetSportListPresenterImpl;
import com.qianqi.retrofit2.AllotRetrofitClient;
import com.qianqi.retrofit2.AllotRetrofitService;
import com.qianqi.step.config.Constant;
import com.qianqi.step.service.StepService;
import com.qianqi.step.utils.SharedPreferencesUtils;
import com.qianqi.step.utils.StepCountModeDispatcher;
import com.qianqi.utils.CommonUtils;
import com.qianqi.view.GetSportList;
import com.qianqi.view.StepArcView;
import com.suning.snrf.fragment.FinalHttp;
import com.suning.snrf.fragment.http.AjaxCallBack;
import com.suning.snrf.fragment.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class StepActivity extends AppCompatActivity implements GetSportList, Handler.Callback, View.OnClickListener {


    IGetSportListPresenter presenter = new IGetSportListPresenterImpl(this);
    Object result;
    private TextView tv_data;
    private StepArcView cc;
    private TextView tv_set,rankTxt;
    private TextView tv_isSupport;
    private Handler delayHandler;
    private SharedPreferencesUtils sp;
    private boolean isBind = false;
    private Messenger mGetReplyMessenger = new Messenger(new Handler(this));
    private Messenger messenger;
    /**
     * 用于查询应用服务（application Service）的状态的一种interface，
     * 更详细的信息可以参考Service 和 context.bindService()中的描述，
     * 和许多来自系统的回调方式一样，ServiceConnection的方法都是进程的主线程中调用的。
     */
    ServiceConnection conn = new ServiceConnection() {
        /**
         * 在建立起于Service的连接时会调用该方法，目前Android是通过IBind机制实现与服务的连接。
         * @param name 实际所连接到的Service组件名称
         * @param service 服务的通信信道的IBind，可以通过Service访问对应服务
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                messenger = new Messenger(service);
                Message msg = Message.obtain(null, Constant.MSG_FROM_CLIENT);
                msg.replyTo = mGetReplyMessenger;
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        /**
         * 当与Service之间的连接丢失的时候会调用该方法，
         * 这种情况经常发生在Service所在的进程崩溃或者被Kill的时候调用，
         * 此方法不会移除与Service的连接，当服务重新启动的时候仍然会调用 onServiceConnected()。
         * @param name 丢失连接的组件名称
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private ListView listview;
    private int num;
    private FinalHttp finalHttp = new FinalHttp();
    private List<SportRankBean.RankListBean> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        assignViews();
        addListener();
        //presenter.getSportListPresenter();
        step();
        initStep();
    }

    private void assignViews() {
        rankTxt = (TextView)findViewById(R.id.rank);
        tv_data = (TextView) findViewById(R.id.tv_data);
        cc = (StepArcView) findViewById(R.id.cc);
        listview = (ListView) findViewById(R.id.listview);
    }

    private void initStep() {
        sp = new SharedPreferencesUtils(StepActivity.this);
        final String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "20000");

        AllotRetrofitClient.getInstance()
                .create(AllotRetrofitService.class)
                .getSportsList(1, 10, "", 1)
                .subscribeOn(Schedulers.io())//在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())//主线程去处理请求结果
                .subscribe(new Observer<SportListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SportListBean mSportListBean) {
                        for (int i = 0; i < mSportListBean.getSportsList().size(); i++) {
                            if (mSportListBean.getSportsList().get(i).getId() == 358) {
                                int num = mSportListBean.getSportsList().get(i).getNum();
                                int rank = mSportListBean.getSportsList().get(i).getRank();
                                cc.setCurrentCount(Integer.parseInt(planWalk_QTY), num);
                                rankTxt.setText("当前排名："+String.valueOf(rank));
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void getSportListSuccess(AirQualityResult result) {

    }

    @Override
    public void getSportListFail(String error_Msg) {

    }


    private void step() {
        AllotRetrofitClient.getInstance()
                .create(AllotRetrofitService.class)
                .getSportsRank(1, 10, "", 1)
                .subscribeOn(Schedulers.io())//在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())//主线程去处理请求结果
                .subscribe(new Observer<SportRankBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (StepActivity.this.isDestroyed()) {
                            d.dispose();
                        }
                    }

                    @Override
                    public void onNext(SportRankBean mSportRankBean) {


                        list = new ArrayList<SportRankBean.RankListBean>();
                        for (SportRankBean.RankListBean mRankListBean : mSportRankBean.getRankList()) {
                            SportRankBean.RankListBean bean = new SportRankBean.RankListBean();
                            bean.setGoods(mRankListBean.getGoods());
                            bean.setIcon(mRankListBean.getIcon());
                            bean.setIsGood(mRankListBean.getIsGood());
                            bean.setRank(mRankListBean.getRank());
                            bean.setNum(mRankListBean.getNum());
                            bean.setName(mRankListBean.getName());
                            bean.setId(mRankListBean.getId());
                            list.add(bean);
                        }
                        listview.setAdapter(new StepAdapter(StepActivity.this, list));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void initData() {
        sp = new SharedPreferencesUtils(this);
        String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "20000");
        cc.setCurrentCount(Integer.parseInt(planWalk_QTY), num);
        if (StepCountModeDispatcher.isSupportStepCountSensor(this)) {
            tv_isSupport.setText("计步中...");
            delayHandler = new Handler(this);
            setupService();
        } else {
            tv_isSupport.setText("该设备不支持计步");
        }
    }

    private void addListener() {
       // tv_set.setOnClickListener(this);
        //tv_data.setOnClickListener(this);
    }

    /**
     * 从service服务中拿到步数
     *
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case Constant.MSG_FROM_SERVER:
                String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
                cc.setCurrentCount(Integer.parseInt(planWalk_QTY), msg.getData().getInt("step"));
                RxBus.getInstance().send(Events.TAP, msg.getData().getInt("step"));
                break;
        }
        return false;
    }

    /**
     * 开启计步服务
     */
    private void setupService() {
        Intent intent = new Intent(this, StepService.class);
        isBind = bindService(intent, conn, Context.BIND_AUTO_CREATE);
        startService(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBind) {
            this.unbindService(conn);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}

