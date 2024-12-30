package com.techeytech.followme.fragments;


import android.os.Bundle;
import android.util.Log;

import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseFragment;
import com.techeytech.followme.base.SimpleRecyclerViewAdapter;
import com.techeytech.followme.beans.response_beans.FeedBean;
import com.techeytech.followme.beans.response_beans.GetDriverJobBoardBean;
import com.techeytech.followme.databinding.FragmentFeedsBinding;
import com.techeytech.followme.databinding.ItemViewFeedBinding;
import com.techeytech.followme.utils.AppController;
import com.techeytech.followme.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedsFragment extends BaseFragment<FragmentFeedsBinding> {

    private Call<GetDriverJobBoardBean> callGetJobBoard;
    private SimpleRecyclerViewAdapter<FeedBean, ItemViewFeedBinding> mFeedsAdapter;


    public FeedsFragment() {
        // Required empty public constructor
    }


    @Override
    protected void onFragmentCreateView(Bundle savedInstanceState) {
        super.onFragmentCreateView(savedInstanceState);

        initView();
    }

    private void initView() {
        binding.toolbar.textTitle.setText(getString(R.string.feeds));
        mFeedsAdapter = new SimpleRecyclerViewAdapter<>(R.layout.item_view_feed, BR.bean, (v, feedBean) -> {

            /*if (v.getId() == R.id.cb_like) {
                        //Log.e("Home fragment", new Gson().toJson(buyerSellerDetails));

                        likeUnlikeSellerProfile(buyerSellerDetails);
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("buyerSellerDetails", buyerSellerDetails);
                        startActivity(new Intent(getActivity(), SellerProfileActivity.class).putExtras(bundle));
                        Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                        Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    }*/

            if (v.getId() == R.id.relative_like) {
                feedBean.setLikes(feedBean.getLikes() + 1);
                mFeedsAdapter.notifyItemChanged(feedBean.getIndex());
            }
        });
        binding.rvFeeds.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvFeeds.setAdapter(mFeedsAdapter);


        List<FeedBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FeedBean feedBean = new FeedBean(Constants.names[i], Constants.feedMessage[i], 0, i);
            list.add(feedBean);
        }
        mFeedsAdapter.setList(list);
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_feeds;
    }

    @Override
    public void onDestroy() {
        if (callGetJobBoard != null) callGetJobBoard.cancel();
        super.onDestroy();
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

    ////////////////////api code

    /*private void likeUnlikeSellerProfile(GetBuyerSellerDetailsBean.DataBeanX.DataBean buyerSellerDetails) {

        mCallLikeProfile = AppController.getInstance().getApis().likeBuyerProfile(getHeader(), String.valueOf(buyerSellerDetails.getId()));
        mCallLikeProfile.enqueue(new ResponseHandler<SuccessBean>() {
            @Override
            public void onSuccess(SuccessBean model) {

                if (model != null) {
                    if (model.getStatus() == Constants.SUCCESS_CODE) {
                        Log.d("dfd", "dfd");
                        //showSuccessToast(model.getMessage());
                    }
                }
            }

            @Override
            public void apiError(ApiError t) {
                buyerSellerDetails.setIs_like(0);
                mAdapterHomeSubSeller.notifyDataSetChanged();
                if (t != null) showErrorToast(t.getMessage());
            }

            @Override
            public void onComplete(Call<SuccessBean> call, Throwable t) {
                onCallComplete(call, t);
            }

            @Override
            public void statusCode(int t) {
                super.statusCode(t);
                handleCodes(t);
            }
        });
    }*/
}
