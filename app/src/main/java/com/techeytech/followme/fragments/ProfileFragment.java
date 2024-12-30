package com.techeytech.followme.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techeytech.followme.R;
import com.techeytech.followme.activities.EditProfileActivity;
import com.techeytech.followme.base.BaseCallback;
import com.techeytech.followme.base.BaseFragment;
import com.techeytech.followme.base.SimpleRecyclerViewAdapter;
import com.techeytech.followme.beans.response_beans.FeedBean;
import com.techeytech.followme.beans.response_beans.GetDriverJobBoardBean;
import com.techeytech.followme.databinding.FragmentProfileBinding;
import com.techeytech.followme.databinding.ItemViewFeedBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment<FragmentProfileBinding> {

    private Call<GetDriverJobBoardBean> callGetJobBoard;
    private SimpleRecyclerViewAdapter<FeedBean, ItemViewFeedBinding> mFeedsAdapter;
    private SimpleRecyclerViewAdapter<FeedBean, ItemViewFeedBinding> mProfilePhotosAdapter;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    protected void onFragmentCreateView(Bundle savedInstanceState) {
        super.onFragmentCreateView(savedInstanceState);

        initView();
        setBaseCallback(baseCallback);
    }

    private BaseCallback baseCallback = view -> {
        switch (view.getId()) {
            case R.id.image_edit_profile:
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
        }
    };

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_profile;
    }

    private void initView() {
        binding.toolbar.textTitle.setText(getString(R.string.profile));
        binding.toolbar.imageEditProfile.setVisibility(View.VISIBLE);
        mFeedsAdapter = new SimpleRecyclerViewAdapter<>(R.layout.item_view_profile_interest, BR.bean, (v, jobBoardBean) -> {

        });
        binding.rvInterests.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        binding.rvInterests.setAdapter(mFeedsAdapter);


        List<FeedBean> feedBeans = new ArrayList<>();
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        mFeedsAdapter.setList(feedBeans);

        mProfilePhotosAdapter = new SimpleRecyclerViewAdapter<>(R.layout.item_view_profile_photos, BR.bean, (v, jobBoardBean) -> {

        });
        binding.rvPhotos.setLayoutManager(new GridLayoutManager(getActivity(),3));
        binding.rvPhotos.setAdapter(mProfilePhotosAdapter);
        mProfilePhotosAdapter.setList(feedBeans);
    }
}
