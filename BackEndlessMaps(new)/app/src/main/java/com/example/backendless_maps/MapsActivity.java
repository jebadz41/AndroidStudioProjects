package com.example.backendless_maps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.backendless.persistence.Point;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private GPSTracker gpsTracker;
    private Location mLocation;
    double latitude , longitude;


    String name;
    Boolean isExistingPoint = false;
    Family person;
    LatLng current_pos;
    List<Family> list;
    ImageButton ibSend;
    TextView tvLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ibSend = (ImageButton) findViewById(R.id.ibSend);
        tvLocation = findViewById(R.id.tvLocation);

        name = getIntent().getStringExtra("name");

        gpsTracker = new GPSTracker(getApplicationContext());
        mLocation = gpsTracker.getLocation();

        latitude = mLocation.getLatitude();
        longitude = mLocation.getLongitude();


        if (name.equals("family")) {
            ibSend.setVisibility(View.GONE);
            tvLocation.setVisibility(View.GONE);

            DataQueryBuilder queryBuilder = DataQueryBuilder.create();
            queryBuilder.setWhereClause("name = '" + name + "'");

            Backendless.Data.of(Family.class).find(new AsyncCallback<List<Family>>() {
                @Override
                public void handleResponse(List<Family> response) {
                    list = response;

                    if (list.size() != 0) {
                        for (int i = 0; i < list.size(); i++) {
                            person = response.get(i);
                            Point location = person.getLocation();

                            if (location == null) {
                                Toast.makeText(MapsActivity.this,
                                        "location of " + person.getName() + " is not set", Toast.LENGTH_SHORT).show();
                            } else {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                                current_pos = new LatLng(latitude, longitude);

                                mMap.addMarker(new MarkerOptions().position(current_pos).title(person.getName()));
                            }
                        }
                    }
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(MapsActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            switch (name)
            {
                case "Peter": location("Peter"); break;
                case "Susan": location("Susan"); break;
                case "Thabo": location("Thabo"); break;
                case "Melson": location("Melson"); break;
            }
        }

        ibSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MapsActivity.this, "Busy sending location", Toast.LENGTH_SHORT).show();

                if (!isExistingPoint) {
                    DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                    queryBuilder.setWhereClause("name = '" + name +"'");

                    Backendless.Data.of(Family.class).find(queryBuilder, new AsyncCallback<List<Family>>() {
                        @Override
                        public void handleResponse(List<Family> response) {
                            person = response.get(0);
                            String location_res = latitude + " " + longitude ;
                            tvLocation.setText(location_res);

                            person.setLocation(new Point().setLatitude(latitude).setLongitude(longitude));

                            Backendless.Persistence.save(person, new AsyncCallback<Family>() {
                                @Override
                                public void handleResponse(Family response) {
                                    Toast.makeText(MapsActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                                    isExistingPoint = true;
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Toast.makeText(MapsActivity.this, "Not Saved \n" + fault.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                          /*  Backendless.Data.of(Family.class).save(person, new AsyncCallback<Family>() {
                                @Override
                                public void handleResponse(Family response) {
                                    Toast.makeText(MapsActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Toast.makeText(MapsActivity.this, "Not Saved", Toast.LENGTH_SHORT).show();
                                }
                            });*/
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(MapsActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                    queryBuilder.setWhereClause("name = '" + name +"'");

                    Backendless.Data.of(Family.class).find(queryBuilder, new AsyncCallback<List<Family>>() {
                        @Override
                        public void handleResponse(List<Family> response) {
                            person = response.get(0);
                            String location_res = latitude + " " + longitude ;
                            tvLocation.setText(location_res);

                            Toast.makeText(MapsActivity.this, "location " + person.getLocation(), Toast.LENGTH_SHORT).show();

                            Point loc = new Point().setLongitude(123.45).setLongitude(10.32);
                            person.setLocation(loc);

                            Backendless.Data.save(person, new AsyncCallback<Family>() {
                                @Override
                                public void handleResponse(Family response) {
                                    Toast.makeText(MapsActivity.this, "Changing Location " + person.getLocation() , Toast.LENGTH_SHORT).show();
                                    isExistingPoint = true;

                                  /*  person.setLocation(new Point().setLatitude(latitude).setLongitude(longitude));

                                    Backendless.Data.of(Family.class).save(person, new AsyncCallback<Family>() {
                                        @Override
                                        public void handleResponse(Family response) {
                                        Toast.makeText(MapsActivity.this, "Location changed", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void handleFault(BackendlessFault fault) {
                                         Toast.makeText(MapsActivity.this, "Location not changed", Toast.LENGTH_SHORT).show();
                                        }
                                    });*/
                                }
                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Toast.makeText(MapsActivity.this, "Location not changed\n" + fault.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(MapsActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



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

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
        else
        {
            mMap.setMyLocationEnabled(true);
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
    public void location(final String name)
    {
        Toast.makeText(MapsActivity.this, "Loading Data", Toast.LENGTH_SHORT).show();
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("name = '" + name +"'");

        Backendless.Data.of(Family.class).find(queryBuilder, new AsyncCallback<List<Family>>() {
            @Override
            public void handleResponse(List<Family> response) {
                person = response.get(0);
                Point location = person.getLocation();

                if(location == null) {
                    Toast.makeText(MapsActivity.this, "location not set", Toast.LENGTH_SHORT).show();
                }
                else {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    current_pos = new LatLng(latitude, longitude);

                    mMap.addMarker(new MarkerOptions().position(current_pos).title(name));
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(current_pos)
                            .zoom(10)
                            .bearing(0)
                            .tilt(30)
                            .build();

                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    isExistingPoint = true;
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MapsActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
