package com.qianqi.retrofit2;

import com.qianqi.bean.SportListBean.SportListBean;
import com.qianqi.bean.SportListBean.SportRankBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 14041578 on 2017/3/28.
 */

public interface AllotRetrofitService {
    @POST("comm/sportsList")
    Observable<SportListBean> getSportsList(@Query("page") int page,
                                            @Query("rows") int rows,
                                            @Query("nowDate") String nowDate,
                                            @Query("userId") int userId);


    @POST("comm/sportsRank")
    Observable<SportRankBean> getSportsRank(@Query("page") int page,
                                            @Query("rows") int rows,
                                            @Query("nowDate") String nowDate,
                                            @Query("userId") int userId);

    //运动点赞
    @POST("comm/goodSport")
    Observable<SportRankBean> goodsSport(@Query("userId") int userId,
                                         @Query("walkId") int walkId
    );

    //运动点赞列表
    @POST("comm/sportsGoodsList")
    Observable<SportRankBean> setSportsGoodsList(@Query("userId") int userId,
                                                 @Query("walkId") int walkId,
                                                 @Query("rows") int rows
    );
}

