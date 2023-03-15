package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RestaurantProfile extends AppCompatActivity {
    EditText RestaurantFirstName=findViewById(R.id.etxtRName);
    EditText RestaurantLastName=findViewById(R.id.etxtRLastName);
    EditText RestaurantMail=findViewById(R.id.etxtREmailAddress);
    EditText RestaurantPhone=findViewById(R.id.etxtRPhone);
    EditText RestaurantAdress=findViewById(R.id.etxtRAdress);
    EditText Restaurant=findViewById(R.id.etxtRestaurant);
    Button btnEditProfile=findViewById(R.id.btnCEditProfile);
    Button btnLogout=findViewById(R.id.btnRLogout);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantProfile.this, RestaurantEditProfile.class);
//                RestaurantProfile.startActivity(intent);

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantProfile.this, MainActivityLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}