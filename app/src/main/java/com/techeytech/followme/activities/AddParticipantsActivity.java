package com.techeytech.followme.activities;

import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseActivity;
import com.techeytech.followme.base.BaseCallback;
import com.techeytech.followme.base.SimpleRecyclerViewAdapter;
import com.techeytech.followme.beans.SettingMenuBean;
import com.techeytech.followme.databinding.ActivityAddParticipantsBinding;
import com.techeytech.followme.databinding.ItemViewAddedParticipantsBinding;
import com.techeytech.followme.databinding.ItemViewGroupBinding;
import com.techeytech.followme.utils.AppData;

import java.util.ArrayList;
import java.util.List;

public class AddParticipantsActivity extends BaseActivity<ActivityAddParticipantsBinding> {

    private SimpleRecyclerViewAdapter<SettingMenuBean, ItemViewAddedParticipantsBinding> mAddedParticipantsAdapter;
    private SimpleRecyclerViewAdapter<SettingMenuBean, ItemViewGroupBinding> mParticipantsAdapter;
    private List<SettingMenuBean> listParticipants;

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
        return R.layout.activity_add_participants;
    }

    @Override
    public void onResume() {
        super.onResume();
        //getParticipants();

        new Handler().postDelayed(() -> {
            binding.shimmerViewContainer.stopShimmerAnimation();
            binding.shimmerViewContainer.setVisibility(View.GONE);
            mParticipantsAdapter.setList(listParticipants);
        }, 2000);
    }


    @Override
    public void onPause() {
        binding.shimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    private void initView() {
        binding.fabNext.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue_primary)));
        binding.toolbar.textTitle.setText(getString(R.string.add_participants));
        binding.toolbar.imageBack.setVisibility(View.VISIBLE);
        binding.toolbar.imageSearch.setVisibility(View.VISIBLE);

        List<SettingMenuBean> listAddedParticipants = new ArrayList<>();
        listParticipants = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            SettingMenuBean bean = new SettingMenuBean(AppData.names[i], AppData.icons[i], i, false);
            listParticipants.add(bean);
            //listAddedParticipants.add(bean);
        }


        mParticipantsAdapter = new SimpleRecyclerViewAdapter<>(R.layout.item_view_add_participant, BR.bean, (v, participantBean) -> {

            if (participantBean.isSelected()) {
                participantBean.setSelected(false);
            } else {
                participantBean.setSelected(true);
            }
            mParticipantsAdapter.notifyItemChanged(participantBean.getIndex());
            binding.rvAddedParticipants.setVisibility(View.VISIBLE);
            SettingMenuBean bean = new SettingMenuBean(AppData.names[0], AppData.icons[0], 0, false);
            listAddedParticipants.add(bean);
            mAddedParticipantsAdapter.setList(listAddedParticipants);

        });
        binding.rvParticipants.setLayoutManager(new LinearLayoutManager(this));
        binding.rvParticipants.setAdapter(mParticipantsAdapter);

        mAddedParticipantsAdapter = new SimpleRecyclerViewAdapter<>(R.layout.item_view_added_participants, BR.bean, (v, participantBean) -> {

            mAddedParticipantsAdapter.getList().remove(participantBean.getIndex());
            mAddedParticipantsAdapter.notifyItemChanged(participantBean.getIndex());

        });

        binding.rvAddedParticipants.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvAddedParticipants.setAdapter(mAddedParticipantsAdapter);
        mAddedParticipantsAdapter.setList(listAddedParticipants);

        binding.shimmerViewContainer.startShimmerAnimation();
        setBaseCallback(baseCallback);
    }

    private BaseCallback baseCallback = view -> {

        switch (view.getId()) {
            case R.id.image_back:
                goBack();
                break;
            case R.id.image_search:
                if (binding.llSearch.getVisibility() == View.VISIBLE)
                    binding.llSearch.setVisibility(View.GONE);
                else binding.llSearch.setVisibility(View.VISIBLE);
                break;
            case R.id.fab_next:
                startActivity(new Intent(AddParticipantsActivity.this, CreateGroupActivity.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
        }
    };
}