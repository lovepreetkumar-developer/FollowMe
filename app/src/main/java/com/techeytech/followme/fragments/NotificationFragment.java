package com.techeytech.followme.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseFragment;
import com.techeytech.followme.base.SimpleRecyclerViewAdapter;
import com.techeytech.followme.beans.response_beans.FeedBean;
import com.techeytech.followme.beans.response_beans.GetDriverJobBoardBean;
import com.techeytech.followme.databinding.FragmentFeedsBinding;
import com.techeytech.followme.databinding.FragmentNotificationsBinding;
import com.techeytech.followme.databinding.ItemViewFeedBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseFragment<FragmentNotificationsBinding> {

    private Call<GetDriverJobBoardBean> callGetJobBoard;
    private SimpleRecyclerViewAdapter<FeedBean, ItemViewFeedBinding> mFeedsAdapter;
    List<FeedBean> feedBeans;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    protected void onFragmentCreateView(Bundle savedInstanceState) {
        super.onFragmentCreateView(savedInstanceState);

        initView();
    }

    private void initView() {
        binding.toolbar.textTitle.setText(getString(R.string.notifications));
        binding.toolbar.imageSearch.setVisibility(View.VISIBLE);
        mFeedsAdapter = new SimpleRecyclerViewAdapter<>(R.layout.item_view_notification, BR.bean, (v, jobBoardBean) -> {

        });
        binding.rvFeeds.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvFeeds.setAdapter(mFeedsAdapter);


        feedBeans = new ArrayList<>();
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        feedBeans.add(new FeedBean());
        binding.shimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_notifications;
    }

    @Override
    public void onDestroy() {
        if (callGetJobBoard != null) callGetJobBoard.cancel();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //getNotifications();

        new Handler().postDelayed(() -> {
            binding.shimmerViewContainer.stopShimmerAnimation();
            binding.shimmerViewContainer.setVisibility(View.GONE);
            mFeedsAdapter.setList(feedBeans);
        }, 2000);
    }


    @Override
    public void onPause() {
        binding.shimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    private void getJobBoard() {
        /*showBaseProgress();
        callGetJobBoard = AppController.getInstance().getApis().getJobBoardDriver(getHeader());
        callGetJobBoard.enqueue(new ResponseHandler<GetDriverJobBoardBean>() {

            @Override
            public void onSuccess(GetDriverJobBoardBean model) {

                if (model != null) {
                    if (model.getStatus() == Constants.SUCCESS_CODE) {
                        if (model.getData().size() > 0) mFeedsAdapter.setList(model.getData());
                        else binding.tvNoData.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void apiError(ApiError t) {
                hideBaseProgress();
                if (t != null) showErrorToast(t.getMessage());
            }

            @Override
            public void onComplete(Call<GetDriverJobBoardBean> call, Throwable t) {
                hideBaseProgress();
                onCallComplete(call, t);
            }

            @Override
            public void statusCode(int t) {
                super.statusCode(t);
                handleCodes(t);
                if (t == Constants.NO_CONTENT_FOUND) binding.tvNoData.setVisibility(View.VISIBLE);
            }
        });*/
    }
}
