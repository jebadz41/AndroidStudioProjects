package com.example.backendlessmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.geo.BackendlessGeoQuery;
import com.backendless.geo.GeoPoint;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private String provider;
    double lat = 10.2818, lng = 123.8798;
    ImageButton ibSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng position = new LatLng(lat, lng);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,10));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000,null);

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();

        if(mMap != null)
        {
            LatLng position = new LatLng(lat,lng);

            mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.person))
                .anchor(0.0f,1.0f)
                .title("Your last known location")
                .position(position));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        }
    }

}