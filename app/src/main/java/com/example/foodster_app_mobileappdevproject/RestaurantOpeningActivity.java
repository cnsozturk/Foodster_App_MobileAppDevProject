package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantOpeningActivity extends AppCompatActivity {
    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_opening);
        TextView txtWelcome = findViewById(R.id.txtWelcome);
        Button btnAddRemove = findViewById(R.id.btnAddRemove);
        Button btnViewEdit = findViewById(R.id.btnViewEdit);
        Button btnSendRemind = findViewById(R.id.btnSendRemind);
        Button btnGenReport = findViewById(R.id.btnGenReport);
        Button btnLogOut = findViewById(R.id.btnRlogOut);

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
        String password = preferences.getString("PasswordOfRestaurant", "defaultValue");
        String restaurantName = preferences.getString("RestaurantName", "defaultValue");
        String firstNameOwner = preferences.getString("FirstNameOwner", "defaultValue");
        String lastNameOwner = preferences.getString("LastNameOwner", "defaultValue");
        String phoneNumberRestaurant = preferences.getString("PhoneNumberRestaurant", "defaultValue");
        String city = preferences.getString("City", "defaultValue");
        String address = preferences.getString("AddressRestaurant", "defaultValue");
        String email = preferences.getString("RestaurantEmail", "defaultValue");
        txtWelcome.setText("Welcome, "+firstNameOwner+"!");

        btnAddRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantOpeningActivity.this,AddNewFoodActivity.class));
            }
        });

        btnViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantOpeningActivity.this,RestaurantOrdersViewEditActivity.class));
            }
        });


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantOpeningActivity.this,MainActivityLogin.class));
            }
        });
        btnSendRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantOpeningActivity.this,SendReminderActivity.class));
            }
        });
        btnGenReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantOpeningActivity.this,GenerateReportsActivity.class));
            }
        });
    }
}