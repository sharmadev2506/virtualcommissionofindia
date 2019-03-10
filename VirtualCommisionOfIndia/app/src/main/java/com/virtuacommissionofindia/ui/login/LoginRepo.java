package com.virtuacommissionofindia.ui.login;

import android.text.Editable;

import com.virtuacommissionofindia.R;
import com.virtuacommissionofindia.base.RichMediatorLiveData;
import com.virtuacommissionofindia.model.BaseResponseBean;
import com.virtuacommissionofindia.model.FailureResponse;
import com.virtuacommissionofindia.util.Constants;
import com.virtuacommissionofindia.util.ResourceUtils;

public class LoginRepo {


    public void verifyAdhaar(String adhaarNumber, RichMediatorLiveData<BaseResponseBean> mLiveData) {
        if(validate(adhaarNumber))
        {
            String son1 = "{ \"aadhaar-id\": \""+adhaarNumber+"\", \"channel\":\"SMS\", \"location\": { \"type\": \"gps\", \"latitude\": \"73.2\", \"longitude\": \"22.3\", \"altitude\": \"0\" } }";
            String[] inputArr = new String[] {
                    adhaarNumber,
                    son1,
            };


        }
        else
        {
            FailureResponse failureResponse=new FailureResponse();
            failureResponse.setErrorCode(Constants.INVALID_ADHAAR_NUMBER);
            failureResponse.setErrorMessage(ResourceUtils.getInstance().getString(R.string.invalid_adhaar).toString());
            mLiveData.setFailure(failureResponse);
        }

    }

    private boolean validate(String adhaarNumber) {
        if(adhaarNumber.length()<12) {
            return false;
        }
        return true;
    }
}
