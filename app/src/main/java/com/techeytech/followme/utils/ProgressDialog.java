package com.techeytech.followme.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.techeytech.followme.R;
import com.techeytech.followme.databinding.DialogProgressBinding;

/**
 * Created by Arvind on 26-07-2017.
 */

public class ProgressDialog extends Dialog {

    DialogProgressBinding binding;
    private Context context;

    public ProgressDialog(@NonNull Context context) {
        // super(context);

        super(context, R.style.Loader);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_progress, null, false);
        setContentView(binding.getRoot());

    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        binding.pbOne.start();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        binding.pbOne.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

}
