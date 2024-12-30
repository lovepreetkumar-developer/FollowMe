package com.techeytech.followme.activities;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.techeytech.followme.R;
import com.techeytech.followme.base.BaseActivity;
import com.techeytech.followme.databinding.ActivityHomeBinding;
import com.techeytech.followme.fragments.FeedsFragment;
import com.techeytech.followme.fragments.HomeFragment;
import com.techeytech.followme.fragments.NotificationFragment;
import com.techeytech.followme.fragments.ProfileFragment;
import com.techeytech.followme.interfaces.updatingLocation;
import com.techeytech.followme.utils.BackStackManager;
import com.techeytech.followme.utils.LiveLocationDetector;

public class HomeActivity extends BaseActivity<ActivityHomeBinding>
        implements updatingLocation {


    public static final String TAB_HOME = "tab_home";
    public static final String TAB_FEEDS = "tab_feeds";
    public static final String TAB_NOTIFICATIONS = "tab_notifications";
    public static final String TAB_PROFILE = "tab_profile";
    private static final int CODE_LOCATION_PERMISSION = 121;
    private LiveLocationDetector locationDetector;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initView();
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        if (requestCode == CODE_LOCATION_PERMISSION) {
            //locationDetector.startLocationUpdates();
            /*mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mMap.getUiSettings().setMapToolbarEnabled(false);
            mMap.getUiSettings().setZoomControlsEnabled(false);*/
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void onDestroy() {
        //locationDetector.removeLocationUpdates();
        super.onDestroy();
    }

    @Override
    public void updateLocation() {
        Location mLastLocation = locationDetector.getLastLocation();

        if (mLastLocation != null) {

            /*LatLng latLng;
            MarkerOptions markerOptions;

            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            mMap.addMarker(markerOptions);

            for (int i = 0; i < 4; i++) {
                latLng = new LatLng(AppData.latitude[i], AppData.longtitude[i]);
                markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(AppData.names[i])));
                markerOptions.snippet(AppData.names[i]);
                mMap.addMarker(markerOptions);

            }

            if (!mCameraMoved) {
                //moving map camera
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude())));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
                mCameraMoved = true;
            }*/
        }
    }

    private void initView() {
        //locationDetector = new LiveLocationDetector(this, this);
        /*requestAppPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                R.string.enable_location_permission, CODE_LOCATION_PERMISSION);*/
        replaceFragment(TAB_HOME, new HomeFragment(), true);

        binding.bottomMenus.setOnNavigationItemSelectedListener(itemSelectedListener);
        binding.bottomMenus.setSelectedItemId(R.id.menu_home);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = menuItem -> {
        switch (menuItem.getItemId()) {
            case R.id.menu_home:
                replaceFragment(TAB_HOME, new HomeFragment(), true);
                break;
            case R.id.menu_all_post:
                replaceFragment(TAB_FEEDS, new FeedsFragment(), true);
                break;
            case R.id.menu_create_post:
                startActivity(new Intent(HomeActivity.this, AddPostActivity.class));
                overridePendingTransition(R.anim.animate_zoom_enter, R.anim.animate_zoom_exit);
                break;
            case R.id.menu_notifications:
                replaceFragment(TAB_NOTIFICATIONS, new NotificationFragment(), true);
                break;
            case R.id.menu_profile:
                replaceFragment(TAB_PROFILE, new ProfileFragment(), true);
                break;
        }
        return true;
    };

    public void replaceFragment(String tag, Fragment fragment, boolean animation) {
        BackStackManager.getInstance(this).pushFragments(R.id.frame_layout, tag, fragment, animation);
    }
}
