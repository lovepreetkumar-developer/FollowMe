package com.techeytech.followme.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.techeytech.followme.beans.response_beans.LoginBean;


/**
 * Created by jatin on 2/21/2018.
 */

public class PrefUtils {

    private final String TAG = PrefUtils.class.getName();
    private final String SHARED_PREF_NAME = "FollowMe";
    private final SharedPreferences _pref;
    private static PrefUtils _instance;

    private PrefUtils() {
        _pref = AppController.getInstance().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static PrefUtils getInstance() {
        if (_instance == null) {
            _instance = new PrefUtils();
        }
        return _instance;
    }


    public boolean clear() {
        SharedPreferences.Editor editor = _pref.edit();
        editor.clear();
        return editor.commit();
    }


    public boolean setUser(LoginBean.DataBean userBean) {
        SharedPreferences.Editor editor = _pref.edit();
        editor.putString(KEY.USER, new Gson().toJson(userBean));
        return editor.commit();

    }

    public LoginBean.DataBean getUser() {
        try {
            String s = _pref.getString(KEY.USER, null);
            if (s == null)
                return null;
            return new Gson().fromJson(s, LoginBean.DataBean.class);
        } catch (Exception e) {
            return null;
        }
    }

    public void setTimezone(String timezone) {
        SharedPreferences.Editor editor = _pref.edit();
        editor.putString(KEY.TIMEZONE, timezone);
        editor.commit();
    }

    public String getTimezone() {
        return _pref.getString(KEY.TIMEZONE, "");
    }

    public int getLock() {
        return _pref.getInt(KEY.LOCK, 0);
    }

    public boolean setLock(int lock) {
        SharedPreferences.Editor editor = _pref.edit();
        editor.putInt(KEY.LOCK, lock);
        return editor.commit();
    }

    public void setLatitude(String latitude) {
        SharedPreferences.Editor editor = _pref.edit();
        editor.putString(KEY.LATITUDE, latitude);
        editor.apply();
    }

    public String getLatitude() {
        return _pref.getString(KEY.LATITUDE, "30.704649");
    }

    public void setLongtitude(String longtitude) {
        SharedPreferences.Editor editor = _pref.edit();
        editor.putString(KEY.LONGTITUDE, longtitude);
        editor.apply();
    }

    public String getLongtitude() {
        return _pref.getString(KEY.LONGTITUDE, "76.717873");
    }


    public boolean setNotification(boolean value) {
        SharedPreferences.Editor editor = _pref.edit();
        editor.putBoolean(KEY.NOTIFICATION, value);
        return editor.commit();
    }

    public boolean getNotification() {
        return _pref.getBoolean(KEY.NOTIFICATION, false);
    }


    public boolean setRemember() {
        SharedPreferences.Editor editor = _pref.edit();
        editor.putBoolean(KEY.REMEMBER, true);
        return editor.commit();
    }

    public boolean getRemember(){
        return _pref.getBoolean(KEY.REMEMBER,false);
    }


    public final class KEY {
        public static final String USER = "userData";
        public static final String QOUTE = "qoute";
        public static final String USER_SETTINGS = "user_settings";
        public static final String SHOW_LOCK_SCREEN = "show_lock_screen";
        public static final String REMEMBER = "remember";
        public static final String NOTIFICATION = "notification";
        public static final String TIMEZONE = "timezone";
        public static final String LATITUDE = "latitude";
        public static final String LONGTITUDE = "longtitude";
        public static final String LOCK = "lock";

    }
}
