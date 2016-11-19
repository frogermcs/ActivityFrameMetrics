package com.frogermcs.activityframemetrics.sample;

import android.app.Application;

import com.frogermcs.activityframemetrics.ActivityFrameMetrics;

/**
 * Created by froger_mcs on 18/11/2016.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityFrameMetrics.Builder().build());
    }
}
