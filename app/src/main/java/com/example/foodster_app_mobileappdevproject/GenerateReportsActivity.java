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

import java.util.ArrayList;
import java.util.HashMap;

public class GenerateReportsActivity extends AppCompatActivity {

    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_reports);
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
        Cursor cursorOrderTable = dbh.viewDataOrderTable(restaurantId);
        String[]orderNum = new String[cursorOrderTable.getCount()];
        String[]foodItems = new String[cursorOrderTable.getCount()];
        String[]amount = new String[cursorOrderTable.getCount()];
        String[]typeDelivery = new String[cursorOrderTable.getCount()];
        if(cursorOrderTable.getCount()>0){
            int count = 0;
            while(cursorOrderTable.moveToNext()){
//                if(cursorOrderTable.getString(7).equals("In progress")){
                    orderNum[count] = cursorOrderTable.getString(0);
                    foodItems[count] = cursorOrderTable.getString(4);
                    amount[count] = cursorOrderTable.getString(5);
                    typeDelivery[count] = cursorOrderTable.getString(6);
                    count++;
//                }
            }
        }

        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        for(int i=0; i <orderNum.length; i++){
            HashMap<String,String> data = new HashMap<>();
            String name="";
            if(foodItems[i].contains("|")){
                String[]foodNamesSepar = foodItems[i].split("\\|");
                String[]amountSepar = amount[i].split("\\|");
                for(int j=0; j<foodNamesSepar.length; j++) {
                    name += foodNamesSepar[j] + "(" + amountSepar[j] + ")\n";
                }
            }else{
                name = foodItems[i]+"("+amount[i]+")";
            }
            data.put("OrderNum", "Order #"+orderNum[i]);
            data.put("Name", name);
            data.put("Delivery", typeDelivery[i]);
            list.add(data);
        }
        String[]from={"OrderNum", "Name", "Delivery"};
        int[]to = {R.id.orderNum,R.id.orderDisc,R.id.deliveryType};
        SimpleAdapter adapter = new SimpleAdapter(GenerateReportsActivity.this,list,R.layout.restaurant_report_layoyt,from,to);
        ListView listView = findViewById(R.id.lstGenRepo);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("OrderNum", orderNum[position]);
                editor.commit();
                startActivity(new Intent(GenerateReportsActivity.this,RestaurantReportFormActivity .class));
            }
        });
    }
}