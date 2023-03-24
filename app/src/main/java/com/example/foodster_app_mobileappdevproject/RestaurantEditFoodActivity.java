package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RestaurantEditFoodActivity extends AppCompatActivity {
    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_edit_food);
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
        String editDish = preferences.getString("editDish", "defaultValue");
        TextView txtDishName = findViewById(R.id.txtDishName);
        TextView txtDishPrice = findViewById(R.id.txtDishPrice);
        TextView txtDishNum = findViewById(R.id.txtDishNum);
        TextView txtDishDisc = findViewById(R.id.txtDishDisc);
        TextView txtDishPickUp = findViewById(R.id.txtDishPickUp);
        Button btnSaveEdit = findViewById(R.id.btnSaveEdit);
        dbh =new DataBaseHelper(this);
        Cursor stockNameDishRestaurantId = dbh.viewFoodStocksNameFoodRestaurantID(editDish,restaurantId);
        String name="";
        String price="";
        String amount="";
        String discription="";
        String timePickUp="";

        if(stockNameDishRestaurantId.getCount()>0){
            while (stockNameDishRestaurantId.moveToNext()){
                if(stockNameDishRestaurantId.getString(1).equals(editDish)) {
                    name = stockNameDishRestaurantId.getString(1);
                    price = stockNameDishRestaurantId.getString(4);
                    amount = stockNameDishRestaurantId.getString(3);
                    discription = stockNameDishRestaurantId.getString(6);
                    timePickUp = stockNameDishRestaurantId.getString(5);
                }
            }
        }
        txtDishName.setText(name);
        txtDishPrice.setText(price);
        txtDishNum.setText(amount);
        txtDishDisc.setText(discription);
        txtDishPickUp.setText(timePickUp);

        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameEdited = txtDishName.getText().toString();
                String priceEdited = txtDishPrice.getText().toString();
                String amountEdited = txtDishNum.getText().toString();
                String discEdited = txtDishDisc.getText().toString();
                String timeEdited = txtDishPickUp.getText().toString();
                if (nameEdited.isEmpty() || amountEdited.isEmpty() || priceEdited.isEmpty()) {
                    Toast.makeText(RestaurantEditFoodActivity.this, "Enter the table, please", Toast.LENGTH_LONG).show();
                } else {
                    DateFormat foodDate = new SimpleDateFormat("yyyy-mm-dd");
                    dbh.deleteItem(nameEdited,restaurantId);
                    if (dbh.addDataFoodStocksTable(restaurantId, nameEdited, foodDate.format(new Date()), amountEdited, priceEdited, timeEdited, discEdited)) {
                        startActivity(new Intent(RestaurantEditFoodActivity.this,RestaurantOrdersViewEditActivity.class));
                        Toast.makeText(RestaurantEditFoodActivity.this, "Food Saved", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RestaurantEditFoodActivity.this, "Unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}