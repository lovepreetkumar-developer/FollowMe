package com.techeytech.followme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseActivity;
import com.techeytech.followme.base.BaseCallback;
import com.techeytech.followme.base.SimpleRecyclerViewAdapter;
import com.techeytech.followme.beans.InterestBean;
import com.techeytech.followme.databinding.ActivityAddInterestBinding;
import com.techeytech.followme.databinding.ItemViewInterestBinding;
import com.techeytech.followme.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class AddInterestActivity extends BaseActivity<ActivityAddInterestBinding> {

    private SimpleRecyclerViewAdapter<InterestBean, ItemViewInterestBinding> mInterestsAdapter;

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
        return R.layout.activity_add_interest;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void initView() {
        setHighLightedText(binding.textLogin, getString(R.string.already_have_an_account_login), 25, 31);
        setBaseCallback(baseCallback);

        List<InterestBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            InterestBean bean = new InterestBean(Constants.allInterest[i], i, false);
            list.add(bean);
        }
        mInterestsAdapter = new SimpleRecyclerViewAdapter<>(R.layout.item_view_interest, BR.bean, (v, interestBean) -> {

            if (interestBean.isSelected()) interestBean.setSelected(false);
            else interestBean.setSelected(true);
            mInterestsAdapter.notifyItemChanged(interestBean.getIndex());

        });

        binding.recyclerInterest.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerInterest.setAdapter(mInterestsAdapter);
        mInterestsAdapter.setList(list);
    }

    private BaseCallback baseCallback = view -> {
        if (view.getId() == R.id.button_sign_up) {
            startActivity(new Intent(AddInterestActivity.this, HomeActivity.class));
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
    };

    public void setHighLightedText(TextView tv, String text, int start, int end) {
        Spannable wordToSpan = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View textView) {
                startActivity(new Intent(AddInterestActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                finishAffinity();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(getResources().getColor(R.color.white));
            }
        };
        wordToSpan.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(wordToSpan);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }
}