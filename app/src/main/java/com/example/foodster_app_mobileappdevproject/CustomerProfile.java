package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomerProfile extends AppCompatActivity {
//EditText CustomerFirstName=findViewById(R.id.etxtCFirstName);
//EditText CustomerLastName=findViewById(R.id.etxtCLastName);
//EditText CustomerMail=findViewById(R.id.etxtCMail);
//EditText CustomerPhone=findViewById(R.id.etxtCPhone);
//EditText CustomerAdress=findViewById(R.id.etxtCAdress);
    Button btnEditProfile=findViewById(R.id.btnCEditProfile);
    Button btnLogout=findViewById(R.id.btnCLogout);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
            btnEditProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CustomerProfile.this, CustomerEditProfile.class);
//                RestaurantProfile.startActivity(intent);

                }
            });

            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CustomerProfile.this, MainActivityLogin.class);
                    startActivity(intent);
                    finish();
                }
            });
    }
}