package com.techeytech.followme.activities;

import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arvind.android.permissions.PermissionHandler;
import com.arvind.android.permissions.Permissions;
import com.fxn.pix.Options;
import com.fxn.pix.Pix;
import com.fxn.utility.ImageQuality;
import com.squareup.picasso.Picasso;
import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseActivity;
import com.techeytech.followme.base.BaseCallback;
import com.techeytech.followme.base.SimpleRecyclerViewAdapter;
import com.techeytech.followme.beans.InterestBean;
import com.techeytech.followme.databinding.ActivityEditProfileBinding;
import com.techeytech.followme.databinding.ItemViewInterestBinding;
import com.techeytech.followme.utils.CircleImageTransform;
import com.techeytech.followme.utils.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends BaseActivity<ActivityEditProfileBinding> {

    private SimpleRecyclerViewAdapter<InterestBean, ItemViewInterestBinding> mOptionsAdapter;
    private Options mOptions;
    private static final int CODE_PERMISSION = 101;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initView();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_edit_profile;
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                if (requestCode == CODE_PERMISSION) {
                    ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
                    if (returnValue != null && returnValue.size() > 0) {
                        Picasso.get().load(new File(returnValue.get(0)))
                                .transform(new CircleImageTransform())
                                .into(binding.imageProfile);
                        //updatePicture(returnValue.get(0));
                    }
                }
            }
        }
    }

    private void initView() {
        binding.toolbar.imageBack.setVisibility(View.VISIBLE);
        binding.toolbar.textTitle.setText(getString(R.string.edit_profile));
        setBaseCallback(baseCallback);

        List<InterestBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            InterestBean bean = new InterestBean(Constants.allInterest[i], i, false);
            list.add(bean);
        }
        mOptionsAdapter = new SimpleRecyclerViewAdapter<>(R.layout.item_view_interest, BR.bean, (v, settingBean) -> {


        });
        binding.recyclerInterest.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerInterest.setAdapter(mOptionsAdapter);
        mOptionsAdapter.setList(list);

        mOptions = Options.init()
                .setRequestCode(CODE_PERMISSION)
                .setCount(1)
                .setFrontfacing(false)
                .setImageQuality(ImageQuality.LOW)
                .setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT);
    }

    private BaseCallback baseCallback = view -> {
        switch (view.getId()) {
            case R.id.text_forgot_pass:
                startActivity(new Intent(EditProfileActivity.this, ForgetPasswordActivity.class));
                goNextAnimation();
                break;
            case R.id.button_login:
                startActivity(new Intent(EditProfileActivity.this, HomeActivity.class));
                goNextAnimation();
                break;
            case R.id.image_back:
                goBack();
                break;
            case R.id.relative_profile_pic:
                getPermissions();
                break;
        }
    };

    private void getPermissions() {
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Permissions.check(this, permissions, R.string.enable_media_permission, null, new PermissionHandler() {
            @Override
            public void onGranted() {
                Pix.start(EditProfileActivity.this, mOptions);
            }
        });
    }
}