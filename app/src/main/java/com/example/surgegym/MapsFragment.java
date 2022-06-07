package com.example.surgegym;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng locationDDO = new LatLng(45.49401179721746, -73.82892328703181);
            googleMap.addMarker(new MarkerOptions().position(locationDDO).title("Surge Gym DDO"));

            LatLng locationVerdun = new LatLng(45.45457064253482, -73.5700611315147);
            googleMap.addMarker(new MarkerOptions().position(locationVerdun).title("Surge Gym Verdun"));

            LatLng locationStCath = new LatLng(45.49250244843627, -73.58081571951786);
            googleMap.addMarker(new MarkerOptions().position(locationStCath).title("Surge Gym St-Catherine"));

            LatLng locationStLau = new LatLng(45.498150841707975, -73.74944130332408);
            googleMap.addMarker(new MarkerOptions().position(locationStLau).title("Surge Gym St-Laurent"));

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(locationDDO));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(11.0f));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}