package com.example.diplomaproject.ui.map;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diplomaproject.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng gstu = new LatLng(52.4068247, 30.9370456);
        googleMap.addMarker(new MarkerOptions().position(gstu)
                .title("ГГТУ им. П. О. Сухого"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gstu, 15));
    }
}