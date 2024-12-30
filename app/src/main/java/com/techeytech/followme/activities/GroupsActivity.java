package com.techeytech.followme.activities;

import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseActivity;
import com.techeytech.followme.base.BaseCallback;
import com.techeytech.followme.base.BaseCustomDialog;
import com.techeytech.followme.base.SimpleRecyclerViewAdapter;
import com.techeytech.followme.beans.SettingMenuBean;
import com.techeytech.followme.databinding.ActivityGroupsBinding;
import com.techeytech.followme.databinding.DialogActiviateGroupBinding;
import com.techeytech.followme.databinding.ItemViewSettingMenuBinding;
import com.techeytech.followme.utils.AppData;
import com.techeytech.followme.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupsActivity extends BaseActivity<ActivityGroupsBinding> {

    private SimpleRecyclerViewAdapter<SettingMenuBean, ItemViewSettingMenuBinding> mGroupsAdapter;
    private BaseCustomDialog<DialogActiviateGroupBinding> mDialogActivateGroup;
    private List<SettingMenuBean> list;

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
        return R.layout.activity_groups;
    }

    @Override
    public void onResume() {
        super.onResume();
        //getGroups();

        new Handler().postDelayed(() -> {
            binding.shimmerViewContainer.stopShimmerAnimation();
            binding.shimmerViewContainer.setVisibility(View.GONE);
            mGroupsAdapter.setList(list);
        }, 2000);
    }


    @Override
    public void onPause() {
        binding.shimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    private void initView() {
        binding.toolbar.textTitle.setText(getString(R.string.groups));
        binding.toolbar.imageBack.setVisibility(View.VISIBLE);
        binding.toolbar.imageAddGroup.setVisibility(View.VISIBLE);

        list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            SettingMenuBean bean = new SettingMenuBean(AppData.names[i], AppData.icons[i], i, false);
            list.add(bean);
        }
        mGroupsAdapter = new SimpleRecyclerViewAdapter<>(R.layout.item_view_group, BR.bean, (v, groupBean) -> {

            changeBackColor(groupBean);

        });
        binding.rvGroups.setLayoutManager(new LinearLayoutManager(this));
        binding.rvGroups.setAdapter(mGroupsAdapter);

        binding.shimmerViewContainer.startShimmerAnimation();
        setBaseCallback(baseCallback);
    }

    private void changeBackColor(SettingMenuBean settingBean) {
        for (int i = 0; i < mGroupsAdapter.getList().size(); i++) {
            mGroupsAdapter.getList().get(i).setSelected(false);
        }
        mGroupsAdapter.getList().get(settingBean.getIndex()).setSelected(true);
        mGroupsAdapter.notifyDataSetChanged();

        showActivateGroupDialog(settingBean);
    }

    private void showActivateGroupDialog(SettingMenuBean settingBean) {

        mDialogActivateGroup = new BaseCustomDialog<>(this, R.layout.dialog_activiate_group, new BaseCustomDialog.DialogListener() {
            @Override
            public void onViewClick(View view) {
                switch (view.getId()) {
                    case R.id.image_close:
                        mDialogActivateGroup.dismiss();
                        break;
                    case R.id.btn_yes:
                        mDialogActivateGroup.dismiss();
                        break;
                    case R.id.btn_no:
                        mDialogActivateGroup.dismiss();
                        break;
                }
            }
        });
        Objects.requireNonNull(mDialogActivateGroup.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        mDialogActivateGroup.show();
    }

    private BaseCallback baseCallback = view -> {

        switch (view.getId()) {
            case R.id.image_back:
                goBack();
                break;
            case R.id.image_add_group:
                startActivity(new Intent(GroupsActivity.this, AddParticipantsActivity.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
        }
    };
}