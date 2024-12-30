package com.techeytech.followme.utils;


import com.techeytech.followme.beans.ApiErrorBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class ResponseHandler<T> implements Callback<T> {

    public abstract void onSuccess(T t);

    public void statusCode(int t) {
    }

    public abstract void apiError(ApiErrorBean t);

    public abstract void onComplete(Call<T> call, Throwable t);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            statusCode(response.code());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response.body() != null) {
            try {
                onSuccess(response.body());
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                apiError(UtilMethods.handleError(response.errorBody()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        onComplete(call, null);

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onComplete(call, t);

    }

}
