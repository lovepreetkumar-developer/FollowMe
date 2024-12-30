package com.techeytech.followme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseActivity;
import com.techeytech.followme.base.BaseCallback;
import com.techeytech.followme.beans.ApiErrorBean;
import com.techeytech.followme.beans.response_beans.LoginBean;
import com.techeytech.followme.databinding.ActivityLoginBinding;
import com.techeytech.followme.utils.AppController;
import com.techeytech.followme.utils.Constants;
import com.techeytech.followme.utils.FieldsValidator;
import com.techeytech.followme.utils.PrefUtils;
import com.techeytech.followme.utils.ResponseHandler;

import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    private FieldsValidator mFieldsValidator;
    private String mFireBaseToken;
    private Call<LoginBean> mCallLogin;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initView();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    @Override
    protected void onDestroy() {
        if (mCallLogin != null) mCallLogin.cancel();
        super.onDestroy();
    }

    private void initView() {
        mFieldsValidator = new FieldsValidator();
        setHighLightedText(binding.textRegister, getString(R.string.don_t_have_an_account_register), 23, 31);
        setBaseCallback(baseCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getToken();
    }

    private BaseCallback baseCallback = view -> {
        switch (view.getId()) {
            case R.id.text_forgot_pass:
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                goNextAnimation();
                break;
            case R.id.button_login:
                //if (validate()) loginApi();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                goNextAnimation();
                break;
        }
    };

    public void setHighLightedText(TextView tv, String text, int start, int end) {
        Spannable wordToSpan = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View textView) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                goNextAnimation();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.white));
            }
        };
        wordToSpan.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(wordToSpan);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private boolean validate() {
        return mFieldsValidator.hasText(binding.editTextEmail) &&
                mFieldsValidator.isEmailAddress(binding.editTextEmail, true) &&
                mFieldsValidator.hasText(binding.editTextPassword);
    }

    private void getToken() {

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                try {
                    mFireBaseToken = Objects.requireNonNull(task.getResult()).getToken();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                showErrorToast(getString(R.string.not_valid_token));
            }
        });
    }

    private void loginApi() {
        showBaseProgress();
        HashMap<String, String> map = new HashMap<>();
        map.put("email", binding.editTextEmail.getText().toString());
        map.put("password", binding.editTextPassword.getText().toString());
        map.put("device_type", Constants.DEVICETYPE);
        if (mFireBaseToken != null) {
            map.put("device_token", mFireBaseToken);
        } else {
            hideBaseProgress();
            getToken();
            return;
        }

        mCallLogin = AppController.getInstance().getApis().login(map);
        mCallLogin.enqueue(new ResponseHandler<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {

                if (loginBean != null) {
                    if (loginBean.getStatus() == Constants.SUCCESS_CODE) {
                        if (PrefUtils.getInstance().setUser(loginBean.getData())) {
                            goToHomeScreen();
                        }
                    }
                }
            }

            @Override
            public void apiError(ApiErrorBean t) {
                hideBaseProgress();
                if (t != null) showErrorToast(t.getMessage());
            }


            @Override
            public void onComplete(Call<LoginBean> call, Throwable t) {
                hideBaseProgress();
                onCallComplete(call, t);
            }

            @Override
            public void statusCode(int t) {
                super.statusCode(t);
                handleCodes(t);
            }
        });
    }
}
