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

import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseActivity;
import com.techeytech.followme.base.BaseCallback;
import com.techeytech.followme.beans.response_beans.LoginBean;
import com.techeytech.followme.databinding.ActivityChooseProfileBinding;
import com.techeytech.followme.databinding.ActivityLoginBinding;

public class ChooseProfileActivity extends BaseActivity<ActivityChooseProfileBinding> {

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initView();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_choose_profile;
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    private void initView() {
        setHighLightedText(binding.textLogin, getString(R.string.already_have_an_account_login), 25, 31);
        setBaseCallback(baseCallback);
    }

    private BaseCallback baseCallback = view -> {
        if (view.getId() == R.id.button_next) {
            startActivity(new Intent(ChooseProfileActivity.this, AddInterestActivity.class));
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
    };

    public void setHighLightedText(TextView tv, String text, int start, int end) {
        Spannable wordToSpan = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View textView) {
                startActivity(new Intent(ChooseProfileActivity.this, LoginActivity.class));
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
