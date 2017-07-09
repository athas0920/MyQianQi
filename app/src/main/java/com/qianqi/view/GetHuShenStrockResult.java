package com.qianqi.view;

import com.qianqi.bean.ShanghaiAndShenzhenStrock.HuShenStrockResult;

import java.util.List;

/**
 * Created by p on 2017/3/17.
 */

public interface GetHuShenStrockResult  {
    public void getHuShenStrockResultSuccess(List<HuShenStrockResult> result);
    public void getHuShenStrockResultFail(String msg);
}
