package com.example.backendlessmap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.geo.GeoCategory;

public class MainActivity extends AppCompatActivity {

    Button btnFamily, btnPeter, btnThabo, btnMelson, btnSusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Backendless.Geo.addCategory("family", new AsyncCallback<GeoCategory>() {
            @Override
            public void handleResponse(GeoCategory response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnFamily = findViewById(R.id.btnFamily);
        btnPeter = findViewById(R.id.btnPeter);
        btnMelson = findViewById(R.id.btnMelson);
        btnSusan = findViewById(R.id.btnSusan);
        btnThabo = findViewById(R.id.btnThabo);

        btnFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putExtra("type","family");
                    startActivity(intent);
                }

            }
        });

        btnPeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putExtra("type","peter");
                    startActivity(intent);
                }
            }
        });

        btnThabo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putExtra("type","thabo");
                    startActivity(intent);
                }

            }
        });

        btnMelson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putExtra("type","melson");
                    startActivity(intent);
                }
            }
        });

        btnSusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putExtra("type","susan");
                    startActivity(intent);
                }
            }
        });
    }
}