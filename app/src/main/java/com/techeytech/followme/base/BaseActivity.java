package com.techeytech.followme.base;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.gson.JsonSyntaxException;
import com.techeytech.followme.R;
import com.techeytech.followme.activities.HomeActivity;
import com.techeytech.followme.activities.LoginActivity;
import com.techeytech.followme.beans.response_beans.LoginBean;
import com.techeytech.followme.utils.Constants;
import com.techeytech.followme.utils.MyToast;
import com.techeytech.followme.utils.PrefUtils;
import com.techeytech.followme.utils.RuntimePermissionsActivity;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;


/**
 * Created by macminiandroid on 31/07/18.
 */

public abstract class BaseActivity<V extends ViewDataBinding> extends RuntimePermissionsActivity {

    SharedPreferences sharedPreferences;
    public static final long TIMEOUT = 30000;
    SharedPreferences.Editor editor;
    Context context;
    Handler handler;
    Runnable run;
    Boolean back = false;
    protected ProgressDialog progressDialog;
    public boolean check_session = true;
    private static final String TAG = BaseActivity.class.getName();
    static Context mContext;
    protected V binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getContentView());
        this.context = this;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        onViewReady(savedInstanceState, getIntent());

        //startCounter();
    }

    public void showToast(String message) {
        Toast.makeText(context, message + "", Toast.LENGTH_SHORT).show();
    }

    public void showBaseProgress() {
        progressDialog.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    protected void goBack() {
        onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    protected void goNextAnimation() {
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    @Override
    protected void onDestroy() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();

        super.onDestroy();
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
    }

    public void hideBaseProgress() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public void startTimer() {

    }

    protected <T> void onCallComplete(Call<T> call, Throwable t) {
        hideBaseProgress();
        if (!call.isCanceled() && t != null) {
            showErrorToast(resolveNetworkError(t));
        }
    }

    protected String resolveNetworkError(Throwable cause) {
        if (cause instanceof UnknownHostException)
            return context.getString(R.string.no_internet);
        else if (cause instanceof SocketTimeoutException)
            return context.getString(R.string.server_error);
        else if (cause instanceof ConnectException)
            return context.getString(R.string.no_internet);
        else if (cause instanceof JsonSyntaxException)
            return context.getString(R.string.parser_error);
        return context.getString(R.string.wrong);
    }

    protected void handleCodes(int s) {
        switch (s) {
            case 401:
                logoutUser();
                break;

        }
    }

    protected void logoutUser() {
        //if (PrefUtils.getInstance().getUser() != null) {
        //    if (PrefUtils.getInstance().clear()) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        ActivityCompat.finishAffinity(BaseActivity.this);
        //    }
        // }
    }


    protected void goToHomeScreen() {
        if (PrefUtils.getInstance().getUser() != null) {

            LoginBean.DataBean loginBean = PrefUtils.getInstance().getUser();

            Intent intent;

            if (loginBean != null) {
                intent = new Intent(context, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        }
    }

    public void DebugLog(String message) {
        if (Constants.SHOW_LOG)
            Log.d(context.getClass().getSimpleName(), "" + message);
    }

    @Override
    protected void onPause() {
        super.onPause();
        check_session = false;
    }

    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //PrefUtils.getInstance().setShowLockScreen(false);
        //countDownTimer.start();
    }

    protected abstract int getContentView();

    protected void setBaseCallback(BaseCallback baseCallback) {
        binding.setVariable(com.techeytech.followme.BR.callback, baseCallback);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        back = true;
        // Log.e("TAG", "Activity Minimized");
    }

    protected void showSuccessToast(String message) {
        MyToast.success(context, message, Toast.LENGTH_SHORT, true).show();
    }

    protected void showInfoToast(String message) {
        MyToast.info(context, message, Toast.LENGTH_SHORT, true).show();
    }

    protected void showWarnToast(String message) {
        MyToast.warning(context, message, Toast.LENGTH_SHORT, true).show();
    }

    protected void showErrorToast(Throwable t) {
        try {
            showErrorToast(t.getMessage());

        } catch (Exception e) {
        }
    }

    protected Map<String, String> getHeader() {
        Map<String, String> map = new HashMap<>();
        LoginBean.DataBean bean = PrefUtils.getInstance().getUser();
        if (bean != null) {
            map.put(Constants.KEY_USERID, bean.getId());
            map.put(Constants.KEY_SESSION, bean.getSessionkey());

        }
        return map;

    }

    protected void showErrorToast(Exception t) {
        try {
            showErrorToast(t.getMessage());
        } catch (Exception e) {
        }
    }


    protected void showErrorToast(String t) {
        MyToast.error(context, "" + t, Toast.LENGTH_LONG, true).show();
    }
}
