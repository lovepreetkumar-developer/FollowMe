package com.techeytech.followme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseActivity;
import com.techeytech.followme.base.BaseCallback;
import com.techeytech.followme.databinding.ActivityChangeMapStyleBinding;

public class ChangeMapStyleActivity extends BaseActivity<ActivityChangeMapStyleBinding> {

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
        return R.layout.activity_change_map_style;
    }

    private void initView() {

        binding.toolbar.textTitle.setText(getString(R.string.change_map_style));
        binding.toolbar.imageBack.setVisibility(View.VISIBLE);

        setBaseCallback(baseCallback);
    }

    private BaseCallback baseCallback = view -> {

        switch (view.getId()){
            case R.id.image_back:
                goBack();
                break;
            case R.id.rl_simple:

                break;
            case R.id.imageddsf:
                showToast("dslkfdskflj");
                break;
        }
    };
}
