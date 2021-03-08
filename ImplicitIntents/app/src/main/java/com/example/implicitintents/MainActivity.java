package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCall, btnCallFriend, btnOpenWeb, btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = findViewById(R.id.btnCall);
        btnCallFriend = findViewById(R.id.btnCallFriend);
        btnOpenWeb = findViewById(R.id.btnOpenWeb);
        btnMap = findViewById(R.id.btnMap);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }
        });

        btnCallFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+639087020017"));
                startActivity(intent);

            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=DAS Lutopan,Toledo City, Cebu"));
                startActivity(intent);
            }
        });

        btnOpenWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.backendless.com"));
                startActivity(intent);
            }
        });



    }
}