package com.techeytech.followme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseActivity;
import com.techeytech.followme.databinding.ActivitySplashBinding;


public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        new Handler().postDelayed(() -> {
            /*if (PrefUtils.getInstance().getUser() != null) {
                goToHomeScreen();
            } else {*/
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            goNextAnimation();
            // }
        }, 2000);
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }
}