package com.example.foodster_app_mobileappdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityLogin extends AppCompatActivity {
    DataBaseHelper  dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbh = new DataBaseHelper(this);
        EditText editTextLoginUser =findViewById(R.id.editTextForUserEmailLogin);
        EditText editTextPasswordLogin = findViewById(R.id.editTextForLoginPassword);
        Button registerButton = findViewById(R.id.btnOpenRegisterActivity);
        Button loginButton = findViewById(R.id.btnLogin);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        //DummyData
      dbh.addDataRestaurantTable("1", "1", "Kozak", "Nikolay",
                "Shevchenko", "12367897789978", "New Westminster", "777 Royal st");
        dbh.addDataRestaurantTable("murmur@gmail.com", "pasiki", "BestHavchik", "Vasiliy",
                "Marchenko", "128129978", "North Vancouver", "1 Golden st");
        dbh.addDataRestaurantTable("chebur@gmail.com", "orange", "FreshOranges", "Cheburashka",
                "Bezfamiliy", "0989978", "Burnaby", "34 Fifth st");
        dbh.addDataRestaurantTable("shapo@gmail.com", "badStuff", "Dirty Monk", "Staruha",
                "Shapoklyak", "666", "Surrey", "89 Stones st");
        dbh.addDataCustomerTable("helicopter@gmail.com", "heli", "Karlson", "Bezfamiliy",
                "Roof 54", "392478238");
        dbh.addDataCustomerTable("revolution@gmail.com", "revo", "Ulyanov", "Lenin",
                "1 Lenina st.", "392478238");
        dbh.addDataCustomerTable("corn@gmail.com", "corn", "Nikita", "Hrushev",
                "9 Tention st.", "52345235");
        dbh.addDataFoodStocksTable("1", "Red Borsch","2023-01-14", "3", "23.23", "12:30", "Mammy approved");
        dbh.addDataFoodStocksTable("1", "Salo","2023-01-14", "2", "50.23", "12:30", "Best appetiser");
        dbh.addDataFoodStocksTable("1", "Pelmeni","2023-01-14", "2", "10.23", "12:30", "with pork and beef");
        dbh.addDataFoodStocksTable("1", "Vodka","2023-01-14", "3", "27.23", "12:30", "0,5 litre");
        dbh.addDataFoodStocksTable("1", "Donation for Ukraine","2023-01-14", "1000", "5", "", "Slava Ukraine");
        dbh.addDataFoodStocksTable("2", "BLUE Borsch","2023-01-14", "3", "23.23", "12:30", "Mammy approved");

        dbh.addDataOrderTable("customer1@gmail.com", "1","2023-01-14", "Red Borsch|Pelmeni|Vodka","2|1|3", "PickUp", "Delivered",1);
        dbh.addDataOrderTable("customer2@gmail.com", "2","2023-01-20", "Pelmeni|Vodka","1|1", "Delivery", "In progress",0);
        dbh.addDataOrderTable("customer1@gmail.com", "1","2023-01-25", "Vodka","1", "PickUp", "Delivered",1);
        dbh.addDataOrderTable("customer3@gmail.com", "1","2023-01-29", "Vodka|Pelmeni","2|1", "Delivery", "In progress",0);
        dbh.addDataOrderTable("customer3@gmail.com", "1","2023-01-29", "Vodka|Red Borsch","2|1", "Delivery", "In progress",0);
        dbh.addDataOrderTable("customer4@gmail.com", "1","2023-01-29", "Donation for Ukraine","2", "PickUp","Delivered",0);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityLogin.this, RegisterActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(editTextLoginUser.getText().toString()) || TextUtils.isEmpty(editTextPasswordLogin.getText().toString()) ) {
                    Toast.makeText(MainActivityLogin.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    String passwordOfLogin = editTextPasswordLogin.getText().toString() ;
                    String loginUser = editTextLoginUser.getText().toString();

                    Cursor cursorRestaurantTable = dbh.viewDataFromRestaurantTable();
                    String strForRestaurantTablePassword = "";

                    Cursor cursorCustomerTable = dbh.viewDataFromCustomerTable();
                    String strForCustomerTablePassword = "";
                    boolean userFoundInRestaurantTable = false;
                    boolean userFoundInCustomerTable = false;

                    String userType = "";
                    //check Restaurant Table OR  the customer table to loop in both of them and find the login user
                    if (cursorRestaurantTable.getCount() > 0  || cursorCustomerTable.getCount() > 0 ) {
                        //to move through the whole data rows
                        while (cursorRestaurantTable.moveToNext()) {
                            //get string 8= restaurant email string 1 = password string 3 = Restaurant Name st 4 =FnOwner
                            //string 0 = restaurantID THIS ARE OBTAINED IN THE SAME ORDER AS THE DATABASE WAS CREATED
                            if (loginUser.equals(cursorRestaurantTable.getString(8))){
                                strForRestaurantTablePassword = (cursorRestaurantTable.getString(1));
                                userFoundInRestaurantTable = true;
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("RestaurantID", cursorRestaurantTable.getString(0));
                                editor.putString("PasswordOfRestaurant", cursorRestaurantTable.getString(1));
                                editor.putString("RestaurantName", cursorRestaurantTable.getString(2));
                                editor.putString("FirstNameOwner", cursorRestaurantTable.getString(3));
                                editor.putString("LastNameOwner", cursorRestaurantTable.getString(4));
                                editor.putString("PhoneNumberRestaurant", cursorRestaurantTable.getString(5));
                                editor.putString("City", cursorRestaurantTable.getString(6));
                                editor.putString("AddressRestaurant", cursorRestaurantTable.getString(7));
                                editor.putString("RestaurantEmail", cursorRestaurantTable.getString(8));
                                editor.commit();
                            }
                        }
                        if (userFoundInRestaurantTable) {
                            if (strForRestaurantTablePassword.equals(passwordOfLogin)) {
                                startActivity(new Intent(MainActivityLogin.this, RestaurantOpeningActivity.class));
                                editTextLoginUser.setText("");
                                editTextPasswordLogin.setText("");
                            } else {
                                Toast.makeText(MainActivityLogin.this, "password not correct", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            while (cursorCustomerTable.moveToNext()) {
                                //get string 0= customer email string 1 = password string 3 = FN st 4 =LN
                                if (loginUser.equals(cursorCustomerTable.getString(0))){
                                    strForCustomerTablePassword = cursorCustomerTable.getString(1);
                                    userFoundInCustomerTable = true;
                                }
                            }
                            if (userFoundInCustomerTable) {
                                if(strForCustomerTablePassword.equals(passwordOfLogin)){
                                    startActivity(new Intent(MainActivityLogin.this,CustomerOpeningActivity.class));
                                    editTextLoginUser.setText("");
                                    editTextPasswordLogin.setText("");
                                }
                                else {
                                    Toast.makeText(MainActivityLogin.this, "password not correct", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(MainActivityLogin.this, "User not found, please check your information", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    cursorCustomerTable.close();
                    cursorRestaurantTable.close();
                }
            }
        });
    }
}