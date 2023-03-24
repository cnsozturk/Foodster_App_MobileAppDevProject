package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class RemoveFoodActivity extends AppCompatActivity {
    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_food);
//        Button saveChanges = findViewById(R.id.btnSave);
//        TextView txtResult = findViewById(R.id.txtTest);
//
//        SharedPreferences preferences =
//                PreferenceManager.getDefaultSharedPreferences(this);
//        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
//        String password = preferences.getString("PasswordOfRestaurant", "defaultValue");
//        String restaurantName = preferences.getString("RestaurantName", "defaultValue");
//        String firstNameOwner = preferences.getString("FirstNameOwner", "defaultValue");
//        String lastNameOwner = preferences.getString("LastNameOwner", "defaultValue");
//        String phoneNumberRestaurant = preferences.getString("PhoneNumberRestaurant", "defaultValue");
//        String city = preferences.getString("City", "defaultValue");
//        String address = preferences.getString("AddressRestaurant", "defaultValue");
//        String email = preferences.getString("RestaurantEmail", "defaultValue");
//
//        dbh = new DataBaseHelper(this);
//        Cursor foodStocksTable = dbh.viewDataFromFoodStocksTable(restaurantId);
//        String[] name = new String[foodStocksTable.getCount()];
//        String[] price = new String[foodStocksTable.getCount()];
//        int count = 0;
//        if(foodStocksTable.getCount()>0){
//            while(foodStocksTable.moveToNext()){
//                name[count] = foodStocksTable.getString(1);
//                price[count] = foodStocksTable.getString(4);
//                count++;
//            }
//            count=0;
//        }
//        ArrayList<HashMap<String,String>> list = new ArrayList<>();
//        for(int i=0; i<name.length; i++){
//            HashMap<String,String> data = new HashMap<>();
//            data.put("Name",name[i]);
//            data.put("Price", "$"+price[i]);
//            list.add(data);
//        }
//        String[]from = {"Name", "Price"};
//        int[]to = {R.id.txtNameRemove,R.id.txtPriceRemove};
//        SimpleAdapter adapter = new SimpleAdapter(RemoveFoodActivity.this,list,R.layout.remove_food_layout,from,to);
//        ListView listView = findViewById(R.id.lstRemove);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                HashMap<String, String> chosenData = list.get(position);
//                String chosenName = chosenData.get("Name");
//                txtResult.setText(chosenName);
//                switch (position){
//                    case 0:
//                        startActivity(new Intent(RemoveFoodActivity.this,RestaurantEditFoodActivity.class));
//                        break;
//                }
//
//            }
//        });
//        setContentView(R.layout.remove_food_layout);
//        View itemView = setContentView(R.layout.remove_food_layout);
//        Button btnRemove = findViewById(R.id.btnRemove);

    }
}