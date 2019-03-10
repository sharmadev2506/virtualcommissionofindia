package com.virtuacommissionofindia;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.virtuacommissionofindia.data.DataManager;
import com.virtuacommissionofindia.util.ResourceUtils;


public class App extends MultiDexApplication {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ResourceUtils.init(this);
        DataManager dataManager = DataManager.init(context);
        dataManager.initApiManager();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


}
