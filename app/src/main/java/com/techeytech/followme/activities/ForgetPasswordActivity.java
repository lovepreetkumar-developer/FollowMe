package com.techeytech.followme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseActivity;
import com.techeytech.followme.databinding.ActivityForgetPasswordBinding;


public class ForgetPasswordActivity extends BaseActivity<ActivityForgetPasswordBinding> {

    //private Call<SuccessBean> callForgotPassword;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        initView();
    }

    private void initView() {
        binding.toolbar.imageBack.setVisibility(View.VISIBLE);
        setBaseCallback(view -> {
            switch (view.getId()) {
                case R.id.image_back:
                case R.id.button_submit:
                    goBack();
                    break;
                // if (binding.editTextEmail.getText().length() > 0) forgetPasswordApi();
                //else binding.editTextEmail.setText(R.string.please_fill);
            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void onDestroy() {
        //if (callForgotPassword != null) callForgotPassword.cancel();
        super.onDestroy();
    }

    /*private void forgetPasswordApi() {

        showBaseProgress();
        callForgotPassword = AppController.getInstance().getApis().forgotPassword(binding.etEmail.getText().toString());
        callForgotPassword.enqueue(new ResponseHandler<SuccessBean>() {
            @Override
            public void onSuccess(SuccessBean model) {

                if (model != null) {
                    if (model.getStatus() == Constants.SUCCESS_CODE) {
                        showSuccessToast(model.getMessage());
                        onBackPressed();
                    }
                }
            }

            @Override
            public void apiError(ApiError t) {
                hideBaseProgress();
                if (t != null) showErrorToast(t.getMessage());
            }

            @Override
            public void onComplete(Call<SuccessBean> call, Throwable t) {
                hideBaseProgress();
                onCallComplete(call, t);
            }

            @Override
            public void statusCode(int t) {
                super.statusCode(t);
                handleCodes(t);
            }
        });
    }*/
}