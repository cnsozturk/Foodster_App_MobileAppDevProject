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
        TextView txtTEST = findViewById(R.id.txtTEST);
//        RecyclerView recyclerFoodStock = findViewById(R.id.recyclerFoodStock);
        Button addFood = findViewById(R.id.btnAddDish);
        Button removeFood = findViewById(R.id.btnRemoveDish);
//        RestaurantOpeningAdapter adapter;

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
        dbh =new DataBaseHelper(this);
        Cursor foodStocksTable = dbh.viewDataFromFoodStocksTable(restaurantId);
        String[] name = new String[foodStocksTable.getCount()];
        String[] price = new String[foodStocksTable.getCount()];
        String[] amount = new String[foodStocksTable.getCount()];
        int count = 0;
        if(foodStocksTable.getCount()>0){
            while(foodStocksTable.moveToNext()){
                name[count] = foodStocksTable.getString(1);
                amount[count] = foodStocksTable.getString(3);
                price[count] = foodStocksTable.getString(4);
                count++;
            }
            count=0;
        }
//        recyclerFoodStock.setLayoutManager(new GridLayoutManager(this,1));
//        adapter = new RestaurantOpeningAdapter(this, name, price, amount);
//        recyclerFoodStock.setAdapter(adapter);


        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<name.length;i++){
            HashMap<String,String> data = new HashMap<>();
            data.put("Name", name[i]);
            data.put("Price","$"+price[i]);
            data.put("Amount",amount[i]);
            list.add(data);
        }
        String[]from = {"Name","Price","Amount"};
        int[]to = {R.id.txtFoodName,R.id.txtFoodPrice,R.id.txtFoodAmount};

        SimpleAdapter adapter = new SimpleAdapter(RestaurantOpeningActivity.this,list,R.layout.restaurant_list_food_stock,from,to);
        ListView listView = findViewById(R.id.lstFoodStock);
        listView.setAdapter(adapter);
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantOpeningActivity.this,AddNewFoodActivity.class));
            }
        });



//        String strTest="";
//        for(int i = 0; i< name.length; i++){
//             strTest +=name[i];
//        }
//        txtTEST.setText(strTest);







    }
}