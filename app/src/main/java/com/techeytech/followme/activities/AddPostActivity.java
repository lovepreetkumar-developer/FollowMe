package com.techeytech.followme.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
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
import com.techeytech.followme.databinding.ActivityAddPostBinding;
import com.techeytech.followme.utils.CircleImageTransform;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddPostActivity extends BaseActivity<ActivityAddPostBinding> {

    private Options mOptions;
    private static final int CODE_PERMISSION = 101;

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
        return R.layout.activity_add_post;
    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        goBack();
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                if (requestCode == CODE_PERMISSION) {
                    ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
                    if (returnValue != null && returnValue.size() > 0) {
                        binding.rlMedia.setVisibility(View.VISIBLE);
                        Picasso.get().load(new File(returnValue.get(0)))
                                .into(binding.imageMedia);
                        //updatePicture(returnValue.get(0));
                    }
                }
            }
        }
    }

    private void initView() {

        mOptions = Options.init()
                .setRequestCode(CODE_PERMISSION)
                .setCount(1)
                .setFrontfacing(false)
                .setImageQuality(ImageQuality.LOW)
                .setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT);
        setBaseCallback(baseCallback);

        Picasso.get().load(R.drawable.image_profile_circle)
                .transform(new CircleImageTransform())
                .into(binding.imageProfile);

    }

    private BaseCallback baseCallback = view -> {

        switch (view.getId()) {
            case R.id.image_close:
            case R.id.text_post:
                goBack();
                break;
            case R.id.image_gallery:
                getPermissions();
                break;
            case R.id.image_discard:
                binding.rlMedia.setVisibility(View.GONE);
                break;
        }
    };

    private void getPermissions() {
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Permissions.check(this, permissions, R.string.enable_media_permission, null, new PermissionHandler() {
            @Override
            public void onGranted() {
                Pix.start(AddPostActivity.this, mOptions);
            }
        });
    }
}
