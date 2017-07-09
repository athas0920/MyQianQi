package com.qianqi.view;

import com.qianqi.bean.Express.ExpressResult;

/**
 * Created by p on 2017/3/16.
 */

public interface GetExpressInfo {
    public void getExpressInfoSuccess(ExpressResult result);
    public void getExpressInfoFail(String error_Msg);
}
