package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class CustomerEditProfile extends AppCompatActivity {
    EditText CustomerFirstNameEditProfile=findViewById(R.id.etxtCNameEdit);
    EditText CustomerLastNameEditProfile=findViewById(R.id.etxtCLastNameEdit);
    EditText CustomerMailEditProfile=findViewById(R.id.etxtCEmailEdit);
    EditText CustomerPhoneEditProfile=findViewById(R.id.etxtCPhoneEdit);
    EditText CustomerAdressEditProfile=findViewById(R.id.etxtCAdressEdit);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_profile);
    }
}