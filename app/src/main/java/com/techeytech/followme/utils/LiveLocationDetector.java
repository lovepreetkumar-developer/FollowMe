package com.techeytech.followme.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.techeytech.followme.interfaces.updatingLocation;

public class LiveLocationDetector {

    private static final String TAG = LiveLocationDetector.class.getSimpleName();
    public static final int REQUEST_CHECK_SETTINGS = 1908;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 1000;
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;
    private Context activity;
    private Object tag;
    private boolean active = false;


    public LiveLocationDetector(updatingLocation locationListner, Context activity) {
        this.activity = activity;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        mSettingsClient = LocationServices.getSettingsClient(activity);
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                mCurrentLocation = locationResult.getLastLocation();
                if (mCurrentLocation != null) {
                    locationListner.updateLocation();
                    //Toast.makeText(activity, "" + mCurrentLocation.getLatitude(), Toast.LENGTH_SHORT).show();
                   // Toast.makeText(activity, "" + mCurrentLocation.getLongitude(), Toast.LENGTH_SHORT).show();

                    /*LocCallback locCallback = new LocCallback(LocCallback.Type.FOUND, mCurrentLocation);
                    if (tag != null)
                        locCallback.setTag(tag);
                    postValue(locCallback);*/
                }
            }
        };
    }

    public Location getLastLocation() {
        return mCurrentLocation;
    }

    private void init() {
        if (mLocationRequest == null) {
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
            mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setSmallestDisplacement(0);
        }
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest mLocationSettingsRequest = builder.build();
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest).addOnSuccessListener(successListener);
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest).addOnFailureListener(onFailureListener);
    }


    public void onActivityResult(int requestCode, int resultCode) {
        if (this.active) {
            if (requestCode == REQUEST_CHECK_SETTINGS) {
                if (resultCode == Activity.RESULT_CANCELED) {
                    //postValue(new LocCallback(LocCallback.Type.PROMPT_CANCEL));
                }
                init();
            }

        }
    }


    public void startLocationUpdates(LocationRequest locationRequest) {
        this.mLocationRequest = locationRequest;
        this.tag = null;
        init();
    }

    public void startLocationUpdates() {
        startLocationUpdates(null);
    }

    public void startLocationUpdateswithTag(LocationRequest locationRequest, Object tag) {
        this.tag = tag;
        this.mLocationRequest = locationRequest;
        init();
    }


    public void startLocationUpdateswithTag(Object tag) {
        this.tag = tag;
        startLocationUpdateswithTag(null, tag);
    }

    public void removeLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(task -> {
                    //postValue(new LocCallback(LocCallback.Type.STOPPED);
                });
    }


    private OnSuccessListener<LocationSettingsResponse> successListener = new OnSuccessListener<LocationSettingsResponse>() {
        @Override
        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            //postValue(new LocCallback(LocCallback.Type.STARTED));
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        }
    };

    private OnFailureListener onFailureListener = e -> {
        int statusCode = ((ApiException) e).getStatusCode();
        switch (statusCode) {
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                ResolvableApiException rae = (ResolvableApiException) e;
                //postValue(new LocCallback(LocCallback.Type.OPEN_GPS, rae));
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                String errorMessage = "Location settings are inadequate, and cannot be " +
                        "fixed here. Fix in Settings.";
                Log.e(TAG, errorMessage);
                //postValue(new LocCallback(LocCallback.Type.ERROR, errorMessage));

        }
    };

}
