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

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.Currency;
import java.util.Formatter;

public class RestaurantReportFormActivity extends AppCompatActivity {

    DataBaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_report_form);
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
        String orderNum = preferences.getString("OrderNum", "defaultValue");
        TextView titleReport = findViewById(R.id.txtTitleReportForm);
        TextView ordersItems = findViewById(R.id.orderItems);
        TextView totalPrice = findViewById(R.id.txtTotalPrice);
        TextView deliveryType = findViewById(R.id.deliveryType);
        TextView dateReport = findViewById(R.id.dateReport);
        Button btnSendReport = findViewById(R.id.btnSendReport);
        String foodName="";
        String date ="";
        String amount = "";
        String delivery = "";
        String itemsDisplay ="";
        double price=0;
        double total =0;
        DecimalFormat currency = new DecimalFormat("$###,###.##");
        dbh = new DataBaseHelper(this);
        Cursor cursorOrderTable = dbh.viewDataOrderTable(restaurantId);
        if(cursorOrderTable.getCount()>0){
            while(cursorOrderTable.moveToNext()) {
                if(cursorOrderTable.getString(0).equals(orderNum)){
                    date = cursorOrderTable.getString(3);
                    foodName=cursorOrderTable.getString(4);
                    amount=cursorOrderTable.getString(5);
                    delivery=cursorOrderTable.getString(6);
                }
            }
        }
        if(foodName.contains("|")){
            String[] nameSepar = foodName.split("\\|");
            String[] amountSepar = amount.split("\\|");
            for(int i=0;i<nameSepar.length;i++){
                Cursor cursorRestFoodStock = dbh.viewFoodStocksNameFoodRestaurantID(nameSepar[i],restaurantId);
                if(cursorRestFoodStock.getCount()>0) {
                    while (cursorRestFoodStock.moveToNext()) {
                        price = Double.parseDouble(cursorRestFoodStock.getString(4));
                        total += price*Integer.parseInt(amountSepar[i]);
                    }
                }
                itemsDisplay += i+1+". "+nameSepar[i]+" ("+amountSepar[i]+")        "+currency.format(price*Integer.parseInt(amountSepar[i]))+"\n";
            }
        }else{
            Cursor cursorRestFoodStock = dbh.viewFoodStocksNameFoodRestaurantID(foodName,restaurantId);
            if(cursorRestFoodStock.getCount()>0) {
                while (cursorRestFoodStock.moveToNext()) {
                    price = Double.parseDouble(cursorRestFoodStock.getString(4));
                }
            }
            itemsDisplay="1. "+foodName+" ("+amount+")";
            total = price*Integer.parseInt(amount);
        }
        titleReport.setText("Order #"+orderNum);
        ordersItems.setText(itemsDisplay);
        totalPrice.setText("Total price: "+currency.format(total));
        deliveryType.setText(delivery);
        dateReport.setText(date);

        btnSendReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dbh.setStatusDelivered(orderNum);
               startActivity(new Intent(RestaurantReportFormActivity.this,GenerateReportsActivity.class));
            }
        });

    }
}