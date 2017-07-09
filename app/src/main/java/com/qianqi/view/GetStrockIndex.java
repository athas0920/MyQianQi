package com.qianqi.view;

import com.qianqi.bean.StrockIndex.StrockIndexResult;

/**
 * Created by p on 2017/3/17.
 */

public interface GetStrockIndex {
    public void getStrockIndexSuccess(StrockIndexResult result);
    public void getStrockIndexFial(String Msg);
}
