package com.techeytech.followme.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.techeytech.followme.R;
import com.techeytech.followme.activities.SettingsActivity;
import com.techeytech.followme.base.BaseCallback;
import com.techeytech.followme.base.BaseFragment;
import com.techeytech.followme.databinding.FragmentHomeBinding;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding>
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private GoogleMap mMap;
    private boolean mCameraMoved = false;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    protected void onFragmentCreateView(Bundle savedInstanceState) {
        super.onFragmentCreateView(savedInstanceState);
        initView();
        setBaseCallback(baseCallback);
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                if (marker.getSnippet() != null) {
                    View view = getLayoutInflater().inflate(R.layout.marker_info_window_layout, null);
                    view.setPadding(0, 0, 0, 0);
                    ((TextView) view.findViewById(R.id.tv_name)).setText(marker.getSnippet());
                    return view;
                }
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                return null;
            }

        });*/

        mMap.setOnMarkerClickListener(this);

        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
    }


    @Override
    public void onResume() {
        super.onResume();
        mCameraMoved = false;
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return false;
    }

    private void initView() {
        /*SupportMapFragment mapFragment =
                (SupportMapFragment) Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        if (mapFragment != null) mapFragment.getMapAsync(this);*/
    }

    private BaseCallback baseCallback = view -> {
        if (view.getId() == R.id.image_settings) {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
    };

    public Bitmap createCustomMarker(String name) {
        View view = getLayoutInflater().inflate(R.layout.custom_marker_layout, null);

        //((TextView) view.findViewById(R.id.tv_name)).setText(name);
        return createDrawableFromView(view, name);
    }

    public Bitmap createDrawableFromView(View view, String name) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        /*TextView textViewName=view.findViewById(R.id.tv_name);
        textViewName.setText(name);
        textViewName.setTextColor(getResources().getColor(R.color.white));*/
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }
}
