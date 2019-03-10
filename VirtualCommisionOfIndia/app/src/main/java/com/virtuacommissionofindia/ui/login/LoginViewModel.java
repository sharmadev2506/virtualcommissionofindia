package com.virtuacommissionofindia.ui.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import com.virtuacommissionofindia.base.RichMediatorLiveData;
import com.virtuacommissionofindia.model.BaseResponseBean;
import com.virtuacommissionofindia.model.FailureResponse;

public class LoginViewModel extends ViewModel {

    private Observer<Throwable> mErrorObserver;

    private Observer<FailureResponse> mFailureObserver;

    private RichMediatorLiveData<BaseResponseBean> mLiveData;

    private LoginRepo repo = new LoginRepo();

    //saving error & failure observers instance
    public void setGenericListeners(Observer<Throwable> errorObserver, Observer<FailureResponse> failureResponseObserver) {
        this.mErrorObserver = errorObserver;
        this.mFailureObserver = failureResponseObserver;
        initLiveData();
    }


    /**
     * Method is used to initialize live data objects
     */
    private void initLiveData() {
        if (mLiveData == null) {
            mLiveData = new RichMediatorLiveData<BaseResponseBean>() {
                @Override
                protected Observer<FailureResponse> getFailureObserver() {
                    return mFailureObserver;
                }

                @Override
                protected Observer<Throwable> getErrorObserver() {
                    return mErrorObserver;
                }
            };
        }
    }


    public RichMediatorLiveData<BaseResponseBean> getAdhaarLiveData() {
        return mLiveData;
    }


    public void verifyAdhaarAndGetOtp(String adhaarNumber) {
        repo.verifyAdhaar(adhaarNumber, mLiveData);
    }
}

