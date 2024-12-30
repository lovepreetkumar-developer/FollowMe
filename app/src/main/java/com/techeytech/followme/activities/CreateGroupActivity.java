package com.techeytech.followme.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fxn.pix.Options;
import com.fxn.pix.Pix;
import com.fxn.utility.ImageQuality;
import com.squareup.picasso.Picasso;
import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseActivity;
import com.techeytech.followme.base.BaseCallback;
import com.techeytech.followme.base.SimpleRecyclerViewAdapter;
import com.techeytech.followme.beans.SettingMenuBean;
import com.techeytech.followme.databinding.ActivityCreateGroupBinding;
import com.techeytech.followme.databinding.ItemViewAddedParticipantsBinding;
import com.techeytech.followme.utils.AppData;
import com.techeytech.followme.utils.CircleImageTransform;
import com.techeytech.followme.utils.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateGroupActivity extends BaseActivity<ActivityCreateGroupBinding> {

    private static final int CODE_MEDIA_PERMISSION = 101;
    private Options mOptions;
    private SimpleRecyclerViewAdapter<SettingMenuBean, ItemViewAddedParticipantsBinding> mAddedParticipantsAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        initView();
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        if (requestCode == CODE_MEDIA_PERMISSION) {
            Pix.start(CreateGroupActivity.this, mOptions);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_create_group;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null) {

            ArrayList<String> returnValue;

            if (requestCode == 100) {
                returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);

                assert returnValue != null;

                if (returnValue.get(0) != null) {
                    Picasso.get().load(new File(returnValue.get(0)))
                            .centerCrop()
                            .resize(200,200)
                            .transform(new CircleImageTransform())
                            .into(binding.imageProfile);
                }
                File file = new File(returnValue.get(0));
                Bitmap bitmap = new BitmapDrawable(getResources(), file.getAbsolutePath()).getBitmap();

                //binding.imageProfile.setImageBitmap(bitmap);
                Log.d("dfdfd", "dfd");
            }
        }
    }

    private void initView() {

        mOptions = Options.init()
                .setRequestCode(100)
                .setCount(1)
                .setFrontfacing(false)
                .setImageQuality(ImageQuality.LOW)
                .setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT);

        binding.toolbar.textTitle.setText(getString(R.string.new_group));
        binding.toolbar.imageBack.setVisibility(View.VISIBLE);

        List<SettingMenuBean> listAddedParticipants = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            SettingMenuBean bean = new SettingMenuBean(AppData.names[i], AppData.icons[i], i, false);
            listAddedParticipants.add(bean);
        }

        mAddedParticipantsAdapter = new SimpleRecyclerViewAdapter<>(R.layout.item_view_added_participants, BR.bean, (v, participantBean) -> {

            mAddedParticipantsAdapter.getList().remove(participantBean.getIndex());
            mAddedParticipantsAdapter.notifyDataSetChanged();

        });

        binding.rvAddedParticipants.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvAddedParticipants.setAdapter(mAddedParticipantsAdapter);
        mAddedParticipantsAdapter.setList(listAddedParticipants);

        setBaseCallback(baseCallback);
    }

    private BaseCallback baseCallback = view -> {

        switch (view.getId()) {
            case R.id.image_back:
                goBack();
                break;
            case R.id.image_profile:
                requestAppPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        R.string.enable_media_permission, CODE_MEDIA_PERMISSION);
                break;
            case R.id.button_create_group:
                startActivity(new Intent(CreateGroupActivity.this, HomeActivity.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
        }
    };
}
