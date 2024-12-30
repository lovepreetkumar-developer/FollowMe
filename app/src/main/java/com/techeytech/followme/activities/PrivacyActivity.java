package com.techeytech.followme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseActivity;
import com.techeytech.followme.base.BaseCallback;
import com.techeytech.followme.databinding.ActivityChangeMapStyleBinding;
import com.techeytech.followme.databinding.ActivityPrivacyBinding;

public class PrivacyActivity extends BaseActivity<ActivityPrivacyBinding> {

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        initView();
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_privacy;
    }

    private void initView() {

        binding.toolbar.textTitle.setText(getString(R.string.privacy));
        binding.toolbar.imageBack.setVisibility(View.VISIBLE);

        setBaseCallback(baseCallback);
    }

    private BaseCallback baseCallback = view -> {

        if (view.getId() == R.id.image_back) {
            goBack();
        }
    };
}
