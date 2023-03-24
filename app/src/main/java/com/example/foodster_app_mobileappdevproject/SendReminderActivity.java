package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SendReminderActivity extends AppCompatActivity implements RestaurantOpeningAdapter.ItemClickListener{
    DataBaseHelper  dbh;
    RestaurantOpeningAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_reminder);
        TextView txtTEST = findViewById(R.id.txtTest2);
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
        String [] orderNums = new String[cursorOrderTable.getCount()];
        String [] foodNames = new String[cursorOrderTable.getCount()];
        String [] foodAmounts = new String[cursorOrderTable.getCount()];
        int [] orderReminders = new int[cursorOrderTable.getCount()];
        if(cursorOrderTable.getCount()>0){
            int count = 0;
            while(cursorOrderTable.moveToNext()){
                if(cursorOrderTable.getString(7).equals("In progress")){
                    orderNums[count] = cursorOrderTable.getString(0);
                    foodNames[count] = cursorOrderTable.getString(4);
                    foodAmounts[count] = cursorOrderTable.getString(5);
                    if(cursorOrderTable.getInt(8)>0){
                        orderReminders[count]=1;
                    }else{
                        orderReminders[count]=0;
                    }
                    count++;
                }
            }
        }
        RecyclerView recyclerView = findViewById(R.id.recycleReminder);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new RestaurantOpeningAdapter(this,orderNums, foodNames,foodAmounts, orderReminders);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String restaurantId = preferences.getString("RestaurantID", "defaultValue");
        TextView txtResult=findViewById(R.id.txtResult2) ;
        txtResult.setText("YO");
        Cursor cursorOrderTable = dbh.viewDataOrderTable(restaurantId);
        if(cursorOrderTable.getCount()>0){
            while(cursorOrderTable.moveToNext()) {
            if(cursorOrderTable.getInt(8)==1){
                dbh.setTrueReminder(cursorOrderTable.getString(0));
            }else{
                dbh.setFalseReminder(cursorOrderTable.getString(0));
            }
            }
        }
    }
}