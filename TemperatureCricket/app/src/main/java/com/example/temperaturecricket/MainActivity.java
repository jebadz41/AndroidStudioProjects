package com.example.temperaturecricket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText chirpNum;
    Button btnCal;
    TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        chirpNum = findViewById(R.id.chirpNum);
        btnCal = findViewById(R.id.btnCal);
        tvRes = findViewById(R.id.tvRes);

        tvRes.setVisibility(View.GONE);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(chirpNum.getText().toString().isEmpty())
                    Toast.makeText(MainActivity.this,"Please enter all fields!",Toast.LENGTH_SHORT).show();
                else
                {
                    int chirp = Integer.parseInt(chirpNum.getText().toString().trim());

                    Double temp = (chirp / 3.0) + 4;

                    String text = "The approximate temperature outside is \n" +
                            temp.toString() + "degrees Celcius";

                    tvRes.setText(text);
                }
          tvRes.setVisibility(View.VISIBLE);

            }
        });
    }
}