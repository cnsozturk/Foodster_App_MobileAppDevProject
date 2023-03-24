package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewFoodActivity extends AppCompatActivity {
    DataBaseHelper dbh ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_food);
        EditText newName = findViewById(R.id.txtDishName);
        EditText newPrice = findViewById(R.id.txtDishPrice);
        EditText newAmount = findViewById(R.id.txtDishNum);
        EditText newDiscrip = findViewById(R.id.txtDishDisc);
        Button btnPost = findViewById(R.id.btnSaveEdit);
        Button btnRemove = findViewById(R.id.btnRemove);
        EditText newtimePickUp = findViewById(R.id.txtDishPickUp);
        TextView txtResult = findViewById(R.id.txtResult2);
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
        dbh = new DataBaseHelper(this);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = newName.getText().toString();
                Cursor cursorFoodStockName = dbh.viewFoodStocksNameFoodRestaurantID(name,restaurantId);
                boolean foodFound = false;
                if(cursorFoodStockName.getCount()>0) {
                    while (cursorFoodStockName.moveToNext()) {
                        if((cursorFoodStockName.getString(1)).equals(name)) {
                            if(dbh.deleteItem(name, restaurantId)) {
                                Toast.makeText(AddNewFoodActivity.this, "Dish was deleted", Toast.LENGTH_LONG).show();
                                foodFound = true;
                            }else{
                                Toast.makeText(AddNewFoodActivity.this, "Unsuccessful delete", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
                if(!foodFound){
                    Toast.makeText(AddNewFoodActivity.this, "No such dish in DB", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = newName.getText().toString();
                String price = newPrice.getText().toString();
                String amount = newAmount.getText().toString();
                String discrip = newDiscrip.getText().toString();
                String timePickUp = newtimePickUp.getText().toString();
                DateFormat foodDate = new SimpleDateFormat("yyyy-mm-dd");

                if (name.isEmpty() || amount.isEmpty() || price.isEmpty() || timePickUp.isEmpty()) {
                    Toast.makeText(AddNewFoodActivity.this, "Enter the table, please", Toast.LENGTH_LONG).show();
                } else {
                    if (dbh.addDataFoodStocksTable(restaurantId, name, foodDate.format(new Date()), amount, price, timePickUp, discrip)) {
                        Toast.makeText(AddNewFoodActivity.this, "New Food posted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AddNewFoodActivity.this, "Unsuccessful posting", Toast.LENGTH_LONG).show();
                    }
                }
           }
        });
    }
}