package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantOrdersViewEditActivity extends AppCompatActivity {
    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_orders_view_edit);
        TextView txtTEST = findViewById(R.id.txtTEST);
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
        }


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

        SimpleAdapter adapter = new SimpleAdapter(RestaurantOrdersViewEditActivity.this,list,R.layout.restaurant_list_food_stock,from,to);
        ListView listView = findViewById(R.id.lstFoodStock);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String> chosenData = list.get(position);
                String chosenname = chosenData.get("Name");
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("editDish", chosenname);
                editor.commit();
                startActivity(new Intent(RestaurantOrdersViewEditActivity.this,RestaurantEditFoodActivity.class));
            }
        });
    }
}