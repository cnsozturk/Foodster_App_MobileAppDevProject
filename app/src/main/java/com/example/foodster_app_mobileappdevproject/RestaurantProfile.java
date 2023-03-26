package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestaurantProfile extends AppCompatActivity {
    TextView RestaurantFirstName;
    TextView RestaurantLastName;
    TextView RestaurantMail;
    TextView RestaurantPhone;
    TextView RestaurantAdress;
    TextView Restaurant;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);
        RestaurantFirstName=findViewById(R.id.txtRName);
        RestaurantLastName=findViewById(R.id.txtRLName);
        RestaurantMail=findViewById(R.id.txtREmail);
        RestaurantPhone=findViewById(R.id.txtRPhone);
        RestaurantAdress=findViewById(R.id.txtRAdress);
        Restaurant=findViewById(R.id.txtRRestaurant);
        Button btnEditProfile=findViewById(R.id.btnCEditProfile);
        Button btnLogout=findViewById(R.id.btnSaveChanges);

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String email = preferences.getString("RestaurantEmail", "defaultValue");
        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
        String password = preferences.getString("PasswordOfRestaurant", "defaultValue");
        String restaurantName = preferences.getString("RestaurantName", "defaultValue");
        String firstNameOwner = preferences.getString("FirstNameOwner", "defaultValue");
        String lastNameOwner = preferences.getString("LastNameOwner", "defaultValue");
        String phoneNumberRestaurant = preferences.getString("PhoneNumberRestaurant", "defaultValue");
        String city = preferences.getString("City", "defaultValue");
        String address = preferences.getString("AddressRestaurant", "defaultValue");


        RestaurantFirstName.setText(firstNameOwner);
        RestaurantLastName.setText(lastNameOwner);
        RestaurantMail.setText(email);
        RestaurantPhone.setText(phoneNumberRestaurant);
        RestaurantAdress.setText(address);
        Restaurant.setText(restaurantName);



        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantProfile.this,RestaurantEditProfile.class));
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
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Retrieve updated preferences
        String email = preferences.getString("RestaurantEmail", "defaultValue");
        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
        String password = preferences.getString("PasswordOfRestaurant", "defaultValue");
        String restaurantName = preferences.getString("RestaurantName", "defaultValue");
        String firstNameOwner = preferences.getString("FirstNameOwner", "defaultValue");
        String lastNameOwner = preferences.getString("LastNameOwner", "defaultValue");
        String phoneNumberRestaurant = preferences.getString("PhoneNumberRestaurant", "defaultValue");
        String city = preferences.getString("City", "defaultValue");
        String address = preferences.getString("AddressRestaurant", "defaultValue");

        // Update text views
        RestaurantFirstName.setText(firstNameOwner);
        RestaurantLastName.setText(lastNameOwner);
        RestaurantMail.setText(email);
        RestaurantPhone.setText(phoneNumberRestaurant);
        RestaurantAdress.setText(address);
        Restaurant.setText(restaurantName);
    }
}