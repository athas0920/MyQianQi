package com.qianqi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qianqi.R;
import com.qianqi.bean.SportListBean.SportRankBean;
import com.qianqi.retrofit2.AllotRetrofitClient;
import com.qianqi.retrofit2.AllotRetrofitService;
import com.qianqi.utils.AnimationTools;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 14041578 on 2017/4/29.
 */

public class StepAdapter extends BaseAdapter {
    List<SportRankBean.RankListBean> data = new ArrayList<SportRankBean.RankListBean>();
    Context context;
    boolean flag = true;

    public StepAdapter(Context context, List<SportRankBean.RankListBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int arg0) {
        return data.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final SportRankBean.RankListBean bean = data.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_step,
                    parent, false);
            holder = new ViewHolder();
            holder.zan_img = (ImageView) convertView.findViewById(R.id.zan_img);
            holder.headicon = (ImageView) convertView.findViewById(R.id.headicon);
            holder.rank = (TextView) convertView.findViewById(R.id.rank);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.zan_num = (TextView) convertView.findViewById(R.id.zan_num);
            holder.stepnum = (TextView) convertView.findViewById(R.id.stepnum);
            holder.lytzan = (LinearLayout) convertView.findViewById(R.id.lytzan);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.rank.setText(bean.getRank() + "");
        holder.name.setText(bean.getName() + "");
        holder.stepnum.setText(bean.getNum() + "步");

        // 取出bean中当记录状态是否为true，是的话则给img设置focus点赞图片
        if (bean.getIsGood() == 1) {
            holder.zan_img.setImageResource(R.drawable.market_icon_liked);
        } else {
            holder.zan_img.setImageResource(R.drawable.market_icon_dislike);
        }

        // 设置赞的数量
        holder.zan_num.setText(bean.getGoods() + "");

        if (bean.getIsGood() == 1) {
            flag = true;
        } else {
            flag = false;
        }

        holder.lytzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取上次是否已经被点击

                // 判断当前flag是点赞还是取消赞,是的话就给bean值减1，否则就加1
                if (flag) {
                    flag = false;
                    holder.zan_num.setText((Integer.valueOf(holder.zan_num.getText().toString()) - 1) + "");
                    holder.zan_img.setImageResource(R.drawable.market_icon_dislike);
                    AllotRetrofitClient.getInstance()
                            .create(AllotRetrofitService.class)
                            .goodsSport(1, bean.getId())
                            .subscribeOn(Schedulers.io())//在IO线程进行网络请求
                            .observeOn(AndroidSchedulers.mainThread())//主线程去处理请求结果
                            .subscribe(new Observer<SportRankBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(SportRankBean mSportRankBean) {


                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });


                } else {
                    flag = true;
                    holder.zan_num.setText((bean.getGoods() + 1) + "");
                    holder.zan_img.setImageResource(R.drawable.market_icon_liked);
                    AllotRetrofitClient.getInstance()
                            .create(AllotRetrofitService.class)
                            .goodsSport(1, bean.getId())
                            .subscribeOn(Schedulers.io())//在IO线程进行网络请求
                            .observeOn(AndroidSchedulers.mainThread())//主线程去处理请求结果
                            .subscribe(new Observer<SportRankBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(SportRankBean mSportRankBean) {


                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                }
                // 反向存储记录，实现取消点赞功能
                //bean.setIsGood(0);
                AnimationTools.scale(holder.zan_img);

            }
        });
        return convertView;
    }

    private class ViewHolder {
        private ImageView zan_img, headicon;
        private TextView zan_num, rank, name, stepnum;
        private LinearLayout lytzan;
    }

}

