package com.example.intentschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Create_Contact_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etNumber, etWeb, etMap;
    ImageView imgHappy, imageFair, imgSad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__contact_);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etWeb = findViewById(R.id.etWeb);
        etMap = findViewById(R.id.etMap);

        imgHappy = findViewById(R.id.imgHappy);
        imageFair = findViewById(R.id.imgFair);
        imgSad = findViewById(R.id.imgSad);

        imgHappy.setOnClickListener(this);
        imageFair.setOnClickListener(this);
        imgSad.setOnClickListener(this);

    }

    @Override
    public  void onClick(View view)
    {
        if(etName.getText().toString().isEmpty()
                || etNumber.getText().toString().isEmpty()
                || etMap.getText().toString().isEmpty()
                || etWeb.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Please enter all fields", Toast.LENGTH_SHORT).show();
        }
        else
            {
                Intent intent = new Intent();
                intent.putExtra("name",etName.getText().toString().trim());
                intent.putExtra("number" ,etNumber.getText().toString().trim());
                intent.putExtra("web",etWeb.getText().toString().trim());
                intent.putExtra("map",etMap.getText().toString().trim());

                if(view.getId() == R.id.imgHappy)
                    intent.putExtra("face","happy");
                else if(view.getId() == R.id.imgFair)
                    intent.putExtra("face","fair");
                else
                    intent.putExtra("face","sad");

                setResult(RESULT_OK, intent);
                Create_Contact_Activity.this.finish();
            }
    }

}