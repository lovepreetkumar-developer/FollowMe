package com.techeytech.followme.base;

import android.view.View;

public interface BaseCallback {
    void onClick(View view);
    default void onViewClick(View view, int position){}
}
