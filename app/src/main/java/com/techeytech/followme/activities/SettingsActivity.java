package com.techeytech.followme.activities;

import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseActivity;
import com.techeytech.followme.base.BaseCallback;
import com.techeytech.followme.base.SimpleRecyclerViewAdapter;
import com.techeytech.followme.beans.SettingMenuBean;
import com.techeytech.followme.databinding.ActivitySettingsBinding;
import com.techeytech.followme.databinding.ItemViewSettingMenuBinding;
import com.techeytech.followme.utils.AppData;
import com.techeytech.followme.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends BaseActivity<ActivitySettingsBinding> {

    private SimpleRecyclerViewAdapter<SettingMenuBean, ItemViewSettingMenuBinding> mSettingMenuAdapter;

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
        return R.layout.activity_settings;
    }

    private void initView() {
        binding.toolbar.textTitle.setText(getString(R.string.settings));
        binding.toolbar.imageBack.setVisibility(View.VISIBLE);

        List<SettingMenuBean> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            SettingMenuBean bean = new SettingMenuBean(AppData.names[i], AppData.icons[i], i, false);
            list.add(bean);
        }
        mSettingMenuAdapter = new SimpleRecyclerViewAdapter<>(R.layout.item_view_setting_menu, BR.bean, (v, settingBean) -> {

            changeBackColor(settingBean);

        });
        binding.rvSettingMenus.setLayoutManager(new LinearLayoutManager(this));
        binding.rvSettingMenus.setAdapter(mSettingMenuAdapter);
        mSettingMenuAdapter.setList(list);


        setBaseCallback(baseCallback);
    }

    private void changeBackColor(SettingMenuBean settingBean) {
        for (int i = 0; i < mSettingMenuAdapter.getList().size(); i++) {
            mSettingMenuAdapter.getList().get(i).setSelected(false);
        }
        mSettingMenuAdapter.getList().get(settingBean.getIndex()).setSelected(true);
        mSettingMenuAdapter.notifyDataSetChanged();

        switch (settingBean.getIndex()) {
            case 0:
                startActivity(new Intent(SettingsActivity.this, GroupsActivity.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case 1:
                break;
            case 2:
                startActivity(new Intent(SettingsActivity.this, ChangeMapStyleActivity.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case 3:
                break;
            case 4:
                startActivity(new Intent(SettingsActivity.this, PrivacyActivity.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case 5:
                logoutUser();
                break;
        }
    }

    private BaseCallback baseCallback = view -> {

        if (view.getId() == R.id.image_back) {
            goBack();
        }
    };
}
