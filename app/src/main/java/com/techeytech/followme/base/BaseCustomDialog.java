package com.techeytech.followme.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;

public class BaseCustomDialog<V extends ViewDataBinding> extends Dialog {

    private Context context;
    private V binding;
    private @LayoutRes
    int layoutId;
    private final DialogListener dialogListener;

    public BaseCustomDialog(@NonNull Context context, @LayoutRes int layoutId, DialogListener dialogListener) {
        super(context);
        this.context = context;
        this.layoutId = layoutId;
        this.dialogListener = dialogListener;
    }

    public V getBinding() {
        init();
        return binding;
    }

    private void init() {
        if (binding == null)
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false);
        if (dialogListener != null) binding.setVariable(BR.callback, dialogListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(binding.getRoot());
    }

    public interface DialogListener {
        void onViewClick(View view);
    }
}
