package com.astimac.rxandroidexamples.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by alex on 5/27/15.
 */
public class RxAndroidExampleApplication extends Application {

    public static RefWatcher getRefWatcher(@NonNull Context context) {
        RxAndroidExampleApplication application = (RxAndroidExampleApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }
}
