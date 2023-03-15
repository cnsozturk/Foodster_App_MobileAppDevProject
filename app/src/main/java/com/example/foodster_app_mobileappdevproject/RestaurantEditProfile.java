package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class RestaurantEditProfile extends AppCompatActivity {
    EditText RestaurantFirstName=findViewById(R.id.RPersonNameEditProfile);
    EditText RestaurantLastName=findViewById(R.id.etxtRLastName);
    EditText RestaurantMail=findViewById(R.id.etxtREmailEditProfile);
    EditText RestaurantPhone=findViewById(R.id.etxtPhoneEditProfile);
    EditText RestaurantAdress=findViewById(R.id.etxtRAdressEditProfile);
    EditText Restaurant=findViewById(R.id.etxtRestaurantEditProfile);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_edit_profile);
    }
}