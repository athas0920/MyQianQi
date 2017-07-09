package com.qianqi;

import android.app.Application;

/**
 * Created by p on 2017/3/14.
 */

public class QqAppApplication extends Application {
    private static QqAppApplication instance;

    public static QqAppApplication getInstance() {
        if (instance == null) {
            instance = new QqAppApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
