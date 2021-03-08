package com.example.challengefragmentwithrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  CarAdapter.ItemClicked{

    Button btnCar, btnOwner;
    TextView tvCarModel, tvOwnerName, tvOwnerTel;
    ImageView carLogo;
    FragmentManager fragmentManager;
    Fragment btnFrag, listFrag, carFrag, ownerFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCar = findViewById(R.id.btnCar);
        carLogo = findViewById(R.id.carLogo);
        btnOwner = findViewById(R.id.btnOwner);
        tvCarModel = findViewById(R.id.tvCarModel);
        tvOwnerTel = findViewById(R.id.tvOwnerTel);
        tvOwnerName = findViewById(R.id.tvOwnerName);

        fragmentManager = getSupportFragmentManager();

        listFrag = fragmentManager.findFragmentById(R.id.Listfrag);
        btnFrag = fragmentManager.findFragmentById(R.id.btnFrag);
        carFrag = fragmentManager.findFragmentById(R.id.carFrag);
        ownerFrag = fragmentManager.findFragmentById(R.id.ownerFrag);

        fragmentManager.beginTransaction()
                .show(listFrag)
                .show(btnFrag)
                .show(carFrag)
                .hide(ownerFrag)
                .commit();

        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .show(carFrag)
                        .hide(ownerFrag)
                        .commit();

            }
        });

        btnOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .hide(carFrag)
                        .show(ownerFrag)
                        .commit();

            }
        });

        onItemClicked(0);

    }

    @Override
    public void onItemClicked(int index) {

        tvOwnerName.setText(ApplicationClass.cars.get(index).getOwner_name());
        tvCarModel.setText(ApplicationClass.cars.get(index).getCar_model());
        tvOwnerTel.setText(ApplicationClass.cars.get(index).getOwner_tel());

        if(ApplicationClass.cars.get(index).getCar_logo().equals(("volkswagen")))
            carLogo.setImageResource(R.drawable.volkswagen);
        else if (ApplicationClass.cars.get(index).getCar_logo().equals(("nissan")))
            carLogo.setImageResource(R.drawable.nissan);
        else
            carLogo.setImageResource(R.drawable.mercedes);
    }
}