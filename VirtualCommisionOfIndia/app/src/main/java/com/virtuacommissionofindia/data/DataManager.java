package com.virtuacommissionofindia.data;

import android.content.Context;

import com.virtuacommissionofindia.App;
import com.virtuacommissionofindia.data.api.ApiManager;
import com.virtuacommissionofindia.data.preferences.PreferenceManager;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;

public class DataManager {

    private static DataManager instance;
    private ApiManager apiManager;
    private PreferenceManager mPrefManager;


    private DataManager(Context context) {
        //Initializing SharedPreference object
        mPrefManager = PreferenceManager.getInstance(context);


    }

    /**
     * Returns the single instance of {@link DataManager} if
     * {@link #init(Context)} is called first
     *
     * @return instance
     */
    public static DataManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Call init() before getInstance()");
        }
        return instance;
    }

    /**
     * Method used to create an instance of {@link DataManager}
     *
     * @param context of the application passed from the {@link App}
     * @return instance if it is null
     */
    public synchronized static DataManager init(Context context) {
        if (instance == null) {
            instance = new DataManager(context);
        }
        return instance;
    }

    /**
     * Method to initialize {@link ApiManager} class
     */
    public void initApiManager() {
        apiManager = ApiManager.getInstance();
    }



}
