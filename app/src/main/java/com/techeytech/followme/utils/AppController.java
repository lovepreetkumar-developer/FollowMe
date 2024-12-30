package com.techeytech.followme.utils;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.techeytech.followme.interfaces.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jatin on 2/9/2018.
 */

public class AppController extends Application {

    private static final String TAG = AppController.class.getSimpleName();
    private static AppController context;
    private static Retrofit retrofit;
    private static boolean isonline = false;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        AppVisibilityDetector.init(this, new AppVisibilityDetector.AppVisibilityCallback() {
            @Override
            public void onAppGotoForeground() {
                isonline = true;
            }

            @Override
            public void onAppGotoBackground() {
                isonline = false;
            }

        });

    }

    public static AppController getInstance() {
        return context;
    }

    public boolean isIsonline() {
        return isonline;
    }

    public Apis getApis() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new BasicAuthInterceptor(Constants.USERNAME, Constants.PASSWORD));
            httpClient.readTimeout(30, TimeUnit.SECONDS);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(Constants.BASE_URL)
                    .client(httpClient.build())
                    .build();
        }
        return retrofit.create(Apis.class);
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            getApis();
        }
        return retrofit;
    }
}
