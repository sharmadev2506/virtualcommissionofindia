package com.virtuacommissionofindia.ui.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.mukesh.OtpView;
import com.virtuacommissionofindia.R;
import com.virtuacommissionofindia.base.BaseActivity;
import com.virtuacommissionofindia.model.BaseResponseBean;
import com.virtuacommissionofindia.model.FailureResponse;
import com.virtuacommissionofindia.ui.dashboard.DashboardActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_adhaar_number)
    AppCompatEditText etAdhaarNumber;
    @BindView(R.id.tv_verify)
    AppCompatTextView tvVerify;
    @BindView(R.id.otp_view)
    OtpView otpView;
    @BindView(R.id.llm_otp)
    LinearLayout llmOtp;
    @BindView(R.id.btn_get_Otp)
    AppCompatButton btnGetOtp;
    @BindView(R.id.btn_verify_Otp)
    AppCompatButton btnVerifyOtp;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.setGenericListeners(getErrorObserver(), getFailureResponseObserver());
        loginViewModel.getAdhaarLiveData().observe(this, new Observer<BaseResponseBean>() {
            @Override
            public void onChanged(@Nullable BaseResponseBean data) {

            }
        });
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_login;
    }


    @Override
    protected void onFailure(FailureResponse failureResponse) {
        showToastLong(failureResponse.getErrorMessage());
        super.onFailure(failureResponse);
    }


    @OnClick({R.id.btn_get_Otp, R.id.btn_verify_Otp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_Otp:
                // loginViewModel.verifyAdhaarAndGetOtp(etAdhaarNumber.getText().toString());
                llmOtp.setVisibility(View.VISIBLE);
                etAdhaarNumber.setEnabled(false);
                btnGetOtp.setEnabled(false);
                break;
            case R.id.btn_verify_Otp:
                Intent intent=new Intent(this, DashboardActivity.class);
                startActivity(intent);
                finishAffinity();
                break;
        }
    }
}


