package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.timepicker.TimeFormat;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

public class AddNewFoodActivity extends AppCompatActivity {
    DataBaseHelper dbh ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_food);
        EditText newName = findViewById(R.id.inputNameFood);
        EditText newPrice = findViewById(R.id.inputPriceNum);
        EditText newAmount = findViewById(R.id.inputAmountNum);
        EditText newDiscrip = findViewById(R.id.inputDisc);
        Button btnPost = findViewById(R.id.btnPost);
        EditText newtimePickUp = findViewById(R.id.edTimePickUp);
        TextView txtResult = findViewById(R.id.txtResult2);
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
        dbh = new DataBaseHelper(this);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = newName.getText().toString();
                String price = newPrice.getText().toString();
                int amount = Integer.parseInt(newAmount.getText().toString());
                String discrip = newDiscrip.getText().toString();
                String timePickUp = newtimePickUp.getText().toString();
                DateFormat foodDate = new SimpleDateFormat("yyyy-mm-dd");
                Cursor cursorFoodStockName = dbh.viewFoodStocksName(name);
                boolean sameFood =false;
                if(cursorFoodStockName.getCount()>0) {
                    while (cursorFoodStockName.moveToNext()) {
                        if((cursorFoodStockName.getString(1)).equals(name)){
                            amount+=Integer.parseInt(cursorFoodStockName.getString(3).toString());
                            dbh.updateAmount(name,Integer.toString(amount));
                            sameFood = true;
                        };
                    }
                }
                if(!sameFood){
                    if(dbh.addDataFoodStocksTable(restaurantId, name, foodDate.format(new Date()), Integer.toString(amount), price, timePickUp ,discrip)){
                        Toast.makeText(AddNewFoodActivity.this, "New Food posted", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(AddNewFoodActivity.this, "Unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }


//                ((new Date()).toString())

//                dbh.updateAmount(name, "7");
//                Cursor cursorFoodStockName = dbh.viewFoodStocksName(name);
//                if(cursorFoodStockName.getCount()>0) {
//                    while (cursorFoodStockName.moveToNext()) {
//
//                    }
//                }
            }
        });
    }
}