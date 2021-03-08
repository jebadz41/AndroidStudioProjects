package com.example.intentschallenge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCreate;
    TextView tvName;
    ImageView imgPhone, imgWeb, imgMap,imgFace;
    final int CREATE = 1;
    String name = "",number = "",web = "",map = "", face = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btnCreate);
        imgPhone = findViewById(R.id.imgPhone);
        imgWeb = findViewById(R.id.imgWeb);
        imgMap = findViewById(R.id.imgMap);
        imgFace = findViewById(R.id.imgFace);
        tvName = findViewById(R.id.tvName);

        imgFace.setVisibility(View.GONE);
        imgPhone.setVisibility(View.GONE);
        imgWeb.setVisibility(View.GONE);
        imgMap.setVisibility(View.GONE);
        tvName.setVisibility(View.GONE);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        com.example.intentschallenge.Create_Contact_Activity.class);

                startActivityForResult(intent,CREATE);

            }
        });

        imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        imgWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http:" + web));
                startActivity(intent);
            }
        });

        imgMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + map));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE)
        {
            if(resultCode == RESULT_OK)
            {
                imgFace.setVisibility(View.VISIBLE);
                imgPhone.setVisibility(View.VISIBLE);
                imgWeb.setVisibility(View.VISIBLE);
                imgMap.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);

                name = data.getStringExtra("name");
                number = data.getStringExtra("number");
                web = data.getStringExtra("web");
                map = data.getStringExtra("map");
                face = data.getStringExtra("face");

                if(face.equals("happy"))
                    imgFace.setImageResource(R.drawable.happy_face);
                else if(face.equals("fair"))
                    imgFace.setImageResource(R.drawable.satisfied);
                else
                    imgFace.setImageResource(R.drawable.sad);

                tvName.setText(name);

            }
        }
        else
            Toast.makeText(this,"No data passed thrown",Toast.LENGTH_SHORT).show();
    }
}