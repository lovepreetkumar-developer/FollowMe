package com.techeytech.followme.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonSyntaxException;
import com.techeytech.followme.R;
import com.techeytech.followme.activities.LoginActivity;
import com.techeytech.followme.beans.ApiErrorBean;
import com.techeytech.followme.beans.response_beans.LoginBean;
import com.techeytech.followme.utils.AppController;
import com.techeytech.followme.utils.Constants;
import com.techeytech.followme.utils.MyToast;
import com.techeytech.followme.utils.PrefUtils;
import com.techeytech.followme.utils.ProgressDialog;


import java.lang.annotation.Annotation;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;

public abstract class BaseFragment<V extends ViewDataBinding> extends Fragment {

    protected Context basecontext;
    protected ProgressDialog progressDialog;

    protected V binding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.basecontext = context;
    }

    @Override
    public void onDetach() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();

        super.onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    protected void setBaseCallback(BaseCallback baseCallback) {
        binding.setVariable(BR.callback, baseCallback);
    }

    protected Map<String, String> getHeader() {
        Map<String, String> map = new HashMap<>();
        LoginBean.DataBean bean = PrefUtils.getInstance().getUser();
        if (bean != null) {
            map.put(Constants.KEY_USERID, bean.getId());
            map.put(Constants.KEY_SESSION, bean.getSessionkey());

        }
        return map;

    }


    protected <T> void onCallComplete(Call<T> call, Throwable t) {
        hideBaseProgress();
        if (!call.isCanceled() && t != null) {
            showErrorToast(resolveNetworkError(t));
        }
    }

    protected String resolveNetworkError(Throwable cause) {
        if (cause instanceof UnknownHostException)
            return basecontext.getString(R.string.no_internet);
        else if (cause instanceof SocketTimeoutException)
            return basecontext.getString(R.string.server_error);
        else if (cause instanceof ConnectException)
            return basecontext.getString(R.string.no_internet);
        else if (cause instanceof JsonSyntaxException)
            return basecontext.getString(R.string.parser_error);
        return basecontext.getString(R.string.wrong);
    }

    public void addFragment(Context context, Fragment fragment, int container) {
        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(container, fragment)
                .commitAllowingStateLoss();
    }


    public void replaceFragment(Context context, Fragment fragment, int container) {
        ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(container, fragment)
                .commitAllowingStateLoss();
    }

    /* public void replaceFragment(Context context, Fragment fragment, int container, boolean animate) {
         FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
         transaction.replace(container, fragment);
         transaction.commitAllowingStateLoss();
     }
     */


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getFragmentLayout(), container, false);
        progressDialog = new ProgressDialog(basecontext);
        progressDialog.setCancelable(false);
        onFragmentCreateView(savedInstanceState);
        return binding.getRoot();
    }

    protected void showSuccessToast(String message) {
        MyToast.success(basecontext, message, Toast.LENGTH_SHORT, true).show();
    }

    protected void showInfoToast(String message) {
        MyToast.info(basecontext, message, Toast.LENGTH_SHORT, true).show();
    }

    protected void showWarnToast(String message) {
        MyToast.warning(basecontext, message, Toast.LENGTH_SHORT, true).show();
    }

    protected void showErrorToast(Throwable t) {
        try {
            showErrorToast(t.getMessage());

        } catch (Exception e) {
        }
    }

    protected void showErrorToast(Exception t) {
        try {
            showErrorToast(t.getMessage());
        } catch (Exception e) {
        }
    }


    protected void showErrorToast(String t) {
        MyToast.error(basecontext, "" + t, Toast.LENGTH_LONG, true).show();
    }


  /*
    public boolean checkInternet() {
        *//*if (UtilMethods.isNetworkAvailable())
            return true;
        showToast(basecontext.getString(R.string.no_connection));
        return false;*//*
    }*/




    /*public void ErrorLog(String message) {
        if (SyncStateContract.Constants.SHOW_LOG)
            Log.e(basecontext.getClass().getSimpleName(), "" + message);
    }*/

    /*public void InfoLog(String message) {
        if (Constants.SHOW_LOG)
            Log.i(basecontext.getClass().getSimpleName(), "" + message);
    }
*/
   /* public void DebugLog(String message) {
        if (Constants.SHOW_LOG)
            Log.d(basecontext.getClass().getSimpleName(), "" + message);
    }*/


    protected void showToast(String message) {
        Toast.makeText(basecontext, message, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(Throwable t) {
        try {
            showToast(t.getMessage());

        } catch (Exception e) {
        }
    }

    protected void showToast(Exception t) {
        try {
            showToast(t.getMessage());
        } catch (Exception e) {
        }
    }


    @CallSuper
    protected void onFragmentCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public abstract int getFragmentLayout();


    public void showBaseProgress() {
        progressDialog.show();
    }

    public void hideBaseProgress() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }



/*
    protected abstract class ResponseListioner<T> implements Callback<T> {
        public abstract void success(T t) throws Exception;

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
            if(basecontext!=null) {
                if (response.body() != null) {
                    try {
                        success(response.body());
                    } catch (Exception e) {
                      //  showErrorToast(e);
                    }

                } else
                    handleError(response);
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
            if(!call.isCanceled())
            showErrorToast(resolveNetworkError(t));

        }
    }

    protected Apis getApis() {
        showBaseProgress();
        return AppController.getInstance().getApis();
    }

    protected String resolveNetworkError(Throwable cause) {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
        if (cause instanceof UnknownHostException)
            return basecontext.getString(R.string.no_connection);
        else if (cause instanceof SocketTimeoutException)
            return basecontext.getString(R.string.server_error);
        else if (cause instanceof ConnectException)
            return basecontext.getString(R.string.no_connection);
        else if (cause instanceof JsonSyntaxException)
            return basecontext.getString(R.string.parser_error);
        return basecontext.getString(R.string.wrong);
    }
*/


    protected <T> void handleError(Response<T> response) {
        Converter<ResponseBody, ApiErrorBean> converter = AppController.getInstance().getRetrofit().responseBodyConverter(ApiErrorBean.class, new Annotation[0]);
        ApiErrorBean error = null;
        try {
            assert response.errorBody() != null;
            error = converter.convert(response.errorBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (error != null) {
            DebugLog("error Message : " + error.getMessage());
            DebugLog("error Status: " + error.getStatus());
            DebugLog("error Method: " + error.getMethod());
            showToast("" + error.getMessage());
            handleCodes(error.getStatus());

        }
    }

    protected void handleCodes(int s) {
        switch (s) {
            case 401:
                logoutUser();
                break;

        }
    }

    public void DebugLog(String message) {
        if (Constants.SHOW_LOG)
            Log.d(getActivity().getClass().getSimpleName(), "" + message);
    }

    protected void logoutUser() {
        if (PrefUtils.getInstance().getUser() != null) {
            if (PrefUtils.getInstance().clear()) {
                Intent intent = new Intent(basecontext, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                ActivityCompat.finishAffinity(getActivity());
            }
        }
    }

}
