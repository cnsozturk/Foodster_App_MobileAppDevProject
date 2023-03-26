package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RestaurantEditProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_edit_profile);
        EditText RestaurantFirstName=findViewById(R.id.RPersonNameEditProfile);
        EditText RestaurantLastName=findViewById(R.id.etxtRLastName);
        EditText RestaurantMail=findViewById(R.id.etxtREmailEditProfile);
        EditText RestaurantPhone=findViewById(R.id.etxtPhoneEditProfile);
        EditText RestaurantAdress=findViewById(R.id.etxtRAdressEditProfile);
        EditText Restaurant=findViewById(R.id.etxtRestaurantEditProfile);
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String firstNameOwner = preferences.getString("FirstNameOwner", "");
        String lastNameOwner = preferences.getString("LastNameOwner", "");
        String phoneNumberRestaurant = preferences.getString("PhoneNumberRestaurant", "");
        String address = preferences.getString("AddressRestaurant", "");

        RestaurantFirstName.setText(firstNameOwner);
        RestaurantLastName.setText(lastNameOwner);
        RestaurantPhone.setText(phoneNumberRestaurant);
        RestaurantAdress.setText(address);
        Button buttonSaveChanges = findViewById(R.id.btnSaveChanges);
        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String newFirstName = RestaurantFirstName.getText().toString();
                    String newLastName = RestaurantLastName.getText().toString();
                    String newPhone = RestaurantPhone.getText().toString();
                    String newAddress = RestaurantAdress.getText().toString();

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("FirstNameOwner", newFirstName);
                    editor.putString("LastNameOwner", newLastName);
                    editor.putString("PhoneNumberRestaurant", newPhone);
                    editor.putString("AddressRestaurant", newAddress);
                    editor.apply();


                Toast.makeText(RestaurantEditProfile.this, "Related field is updated", Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }
}