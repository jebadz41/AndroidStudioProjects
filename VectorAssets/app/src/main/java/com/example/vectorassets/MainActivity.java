package com.example.vectorassets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView ivPic, imgBeach, imgBuild, imgCake;

    Button btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivPic  = findViewById(R.id.ivPic);
        btnChange = findViewById(R.id.btnChange);
        imgBeach = findViewById(R.id.imgBeach);
        imgBuild = findViewById(R.id.imgBuild);
        imgCake = findViewById(R.id.imgCake);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ivPic.setImageResource(R.drawable.default_face);
            }
        });

        imgBeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPic.setImageResource(R.drawable.beach);
            }
        });

        imgBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPic.setImageResource(R.drawable.build);
            }
        });

        imgCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPic.setImageResource(R.drawable.cake);
            }
        });

    }
}