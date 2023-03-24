package com.example.foodster_app_mobileappdevproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "FoodsterDatabase.db";
    final static int DATABASE_VERSION = 1;

    //TABLE 1 -> Customer
    final static String TABLE_FOR_CUSTOMER_NAME = "Customer_Table";
    final static String T1COL_1 = "CustomerEmail";
    final static String T1COL_2 = "PasswordOfUser";
    final static String T1COL_3 = "FirstNameCustomer";
    final static String T1COL_4 = "LastNameCustomer";
    final static String T1COL_5 = "AddressCustomer";
    final static String T1COL_6 = "PhoneNumberCustomer";

    //TABLE 2 -> Order
    final static String TABLE_FOR_ORDER_NAME = "Order_Table";
    final static String T2COL_1 = "OrderID";
    final static String T2COL_2 = "CustomerEmail";
    final static String T2COL_3 = "RestaurantId";
    final static String T2COL_4 = "Date";
    final static String T2COL_5 = "FoodName";
    final static String T2COL_6 = "OrderedAmount";
    final static String T2COL_7 = "DeliveryorPickup";
    final static String T2COL_8 = "Status";
    final static String T2COL_9 = "Reminder";

    //TABLE 3 -> Restaurant
    final static String TABLE_FOR_RESTAURANT_NAME = "Restaurant_Table";
    final static String T3COL_1 = "RestaurantUserNameEmail";
    final static String T3COL_2 = "PasswordOfRestaurant";
    final static String T3COL_3 = "RestaurantName";
    final static String T3COL_4 = "FirstNameOwner";
    final static String T3COL_5 = "LastNameOwner";
    final static String T3COL_6 = "PhoneNumberRestaurant";
    final static String T3COL_7 = "City";
    final static String T3COL_8 = "AddressRestaurant";
    final static String T3COL_9 = "RestaurantId"; //primary key

    //TABLE 4 -> Food stocks of the restaurant
    final static String TABLE_FOR_FOODOFRESTAURANT_NAME = "RestaurantFoodStocks_Table";
    final static String T4COL_1 = "RestaurantId"; // primary key
    final static String T4COL_2 = "FoodName"; //Food name
    final static String T4COL_3 = "Date";
    final static String T4COL_4 = "FoodAmount";
    final static String T4COL_5 = "Price";
    final static String T4COL_6 = "ProcessPickUpTime";
    final static String T4COL_7 = "ExtraFoodDetails";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryTable1 = "CREATE TABLE " + TABLE_FOR_CUSTOMER_NAME + "(" + T1COL_1 + " TEXT UNIQUE, " +
                T1COL_2 + " TEXT, " + T1COL_3 + " TEXT, " + T1COL_4 + " TEXT, " + T1COL_5 + " TEXT, " +
                T1COL_6 + " TEXT, PRIMARY KEY (" + T1COL_1 + ") )";
        String queryTable2 = "CREATE TABLE " + TABLE_FOR_ORDER_NAME + "(" + T2COL_1 + " INTEGER PRIMARY KEY, " +
                T2COL_2 + " TEXT, " + T2COL_3 + " TEXT, " + T2COL_4 + " TEXT, " + T2COL_5 + " TEXT, " +
                T2COL_6 + " TEXT, " + T2COL_7 + " TEXT, " + T2COL_8 + " TEXT, " + T2COL_9 + " INT, " +
                "FOREIGN KEY (" + T2COL_2 + ") REFERENCES " + TABLE_FOR_CUSTOMER_NAME + "(" + T1COL_1 + ")," +
                "FOREIGN KEY (" + T2COL_3 + ") REFERENCES " + TABLE_FOR_RESTAURANT_NAME + "(" + T3COL_9 + "))";

        String queryTable3 = "CREATE TABLE " + TABLE_FOR_RESTAURANT_NAME + "(" + T3COL_9 + " INTEGER PRIMARY KEY, " +
                T3COL_2 + " TEXT, " + T3COL_3 + " TEXT UNIQUE, " + T3COL_4 + " TEXT, " + T3COL_5 + " TEXT, " +
                T3COL_6 + " TEXT, " + T3COL_7 + " TEXT, " + T3COL_8 + " TEXT, " + T3COL_1 + " TEXT UNIQUE)";

        String queryTable4 = "CREATE TABLE " + TABLE_FOR_FOODOFRESTAURANT_NAME + "(" + T4COL_1 + " INTEGER, " +
                T4COL_2 + " TEXT, " + T4COL_3 + " DATE, " + T4COL_4 + " TEXT, " + T4COL_5 + " TEXT, " +
                T4COL_6 + " TEXT, " + T4COL_7 + " TEXT, " +
                "PRIMARY KEY (" + T4COL_1 + ", " + T4COL_2 + "), " +
                "FOREIGN KEY (" + T4COL_1 + ") REFERENCES " + TABLE_FOR_RESTAURANT_NAME + "(" + T3COL_3 + "))";

        sqLiteDatabase.execSQL(queryTable1);
        sqLiteDatabase.execSQL(queryTable2);
        sqLiteDatabase.execSQL(queryTable3);
        sqLiteDatabase.execSQL(queryTable4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int olderVersion, int newerVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOR_CUSTOMER_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOR_ORDER_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOR_RESTAURANT_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOR_FOODOFRESTAURANT_NAME);
        onCreate(sqLiteDatabase);
    }

    /// METHOD TO ADD DATA TO THE the Customer TABLE
    public boolean addDataCustomerTable(String customerUserNameEmail, String passwordCustomer,
                                        String firstNameCustomer, String lastNameCustomer,
                                        String addressCustomer, String PhoneNumberCustomer) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues(); // save values in key value pairs
        values.put(T1COL_1, customerUserNameEmail);
        values.put(T1COL_2, passwordCustomer);
        values.put(T1COL_3, firstNameCustomer);
        values.put(T1COL_4, lastNameCustomer);
        values.put(T1COL_5, addressCustomer);
        values.put(T1COL_6, PhoneNumberCustomer);

        long l = sqLiteDatabase.insert(TABLE_FOR_CUSTOMER_NAME, null, values); // insert data to table 1
        //positive value indicates success
        if (l > 0)
            return true;
        else
            return false;
    }

    /// METHOD TO ADD DATA TO ORDER TABLE
    public boolean addDataOrderTable(String customerEmail, String restaurantId, String date,
                                     String foodName, String orderAmount, String deliveryPickUp,
                                     String status, int reminder) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues(); // save values in key value pairs
        values.put(T2COL_2, customerEmail);
        values.put(T2COL_3, restaurantId);
        values.put(T2COL_4, date);
        values.put(T2COL_5, foodName);
        values.put(T2COL_6, orderAmount);
        values.put(T2COL_7, deliveryPickUp);
        values.put(T2COL_8, status);
        values.put(T2COL_9, reminder);

        long l = sqLiteDatabase.insert(TABLE_FOR_ORDER_NAME, null, values);
        //positive value indicates success
        if (l > 0)
            return true;
        else
            return false;
    }


    /// METHOD TO ADD DATA TO THE the Restaurant TABLE
    public boolean addDataRestaurantTable(String restaurantUserNameEmail, String passwordRestaurant, String restaurantName, String firstNameOwnerRestaurant,
                                          String lastNameOwnerRestaurant, String phoneNumberRestaurant, String cityRestaurant, String addressOfRestaurant) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues(); // save values in key value pairs
        values.put(T3COL_1, restaurantUserNameEmail);
        values.put(T3COL_2, passwordRestaurant);
        values.put(T3COL_3, restaurantName);
        values.put(T3COL_4, firstNameOwnerRestaurant);
        values.put(T3COL_5, lastNameOwnerRestaurant);
        values.put(T3COL_6, phoneNumberRestaurant);
        values.put(T3COL_7, cityRestaurant);
        values.put(T3COL_8, addressOfRestaurant);

        long l = sqLiteDatabase.insert(TABLE_FOR_RESTAURANT_NAME, null, values); // insert data to table 1
        //positive value indicates success
        if (l > 0)
            return true;
        else
            return false;
    }

    //METHOD TO ADD DATA TO THE Food stocks TABLE
    public boolean addDataFoodStocksTable(String restaurantId, String foodName, String date, String foodAmount,
                                          String price, String processPickUpTime, String extraFoodDetails) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T4COL_1, restaurantId);
        values.put(T4COL_2, foodName);
        values.put(T4COL_3, date);
        values.put(T4COL_4, foodAmount);
        values.put(T4COL_5, price);
        values.put(T4COL_6, processPickUpTime);
        values.put(T4COL_7, extraFoodDetails);
        long l = sqLiteDatabase.insert(TABLE_FOR_FOODOFRESTAURANT_NAME, null, values);
        if (l > 0) {
            return true;
        } else {
            return false;
        }
    }

    //METHOD TO VIEW DATA of Customer Table, combined with the activity where you are using it
    public Cursor viewDataFromCustomerTable() {
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_FOR_CUSTOMER_NAME;
        Cursor cursor = database.rawQuery(query, null);

        return cursor;
    }
    //METHOD TO VIEW DATA ORDER Table in particular Restaurant
    public Cursor viewDataOrderTable(String restaurantId) {
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_FOR_ORDER_NAME + " WHERE " + "RestaurantId = '" + restaurantId+"'";
        Cursor cursor = database.rawQuery(query, null);

        return cursor;
    }


    //METHOD TO VIEW DATA of Restaurant Table
    public Cursor viewDataFromRestaurantTable() {
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_FOR_RESTAURANT_NAME;
        Cursor cursor = database.rawQuery(query, null);


        return cursor;
    }

    //METHOD TO VIEW DATA of Restaurant Food Stocks Table for the particular Restaurant
    public Cursor viewDataFromFoodStocksTable(String restaurantId) {
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_FOR_FOODOFRESTAURANT_NAME + " WHERE " + "RestaurantId = '" + restaurantId+"'";
        Cursor cursor = database.rawQuery(query, null);

        return cursor;
    }
    //METHOD TO VIEW DATA of Restaurant Food Stocks Table for the particular Food name and Restaurant.
    public Cursor viewFoodStocksNameFoodRestaurantID(String foodName, String restaurantId) {
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_FOR_FOODOFRESTAURANT_NAME + " WHERE " + "FoodName = '" + foodName+"' AND RestaurantId = '" + restaurantId+"'";
        Cursor cursor = database.rawQuery(query, null);

        return cursor;
    }

    //METHOD TO DELETE item from FoodStock Table with particular Food name
    public Boolean deleteItem(String name, String restaurantId) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "DELETE FROM " + TABLE_FOR_FOODOFRESTAURANT_NAME +" WHERE FoodName= '"+name+"' AND RestaurantId = '" + restaurantId+"'";
        database.execSQL(query);
        return true;
    }

    //METHOD TO ADD AMOUNT to FoodStock Table for the particular Food name
    public Boolean updateAmount(String name, String amount) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "UPDATE " + TABLE_FOR_FOODOFRESTAURANT_NAME + " SET " + "FoodAmount = " + amount + " WHERE FoodName= '"+ name +"'";
        database.execSQL(query);
       return true;
    }
    //METHOD TO MAKE REMINDER TRUE
    public Boolean setTrueReminder(String orderNum) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "UPDATE " + TABLE_FOR_ORDER_NAME + " SET " + "Reminder = " + 1 + " WHERE OrderID = '"+orderNum+"'";
        database.execSQL(query);
        return true;
    }
    //METHOD TO MAKE REMINDER FALSE
    public Boolean setFalseReminder(String orderNum) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "UPDATE " + TABLE_FOR_ORDER_NAME + " SET " + "Reminder = " + 0 + " WHERE OrderID = '"+orderNum+"'";
        database.execSQL(query);
        return true;
    }
    //METHOD SET STATUS TO DELIVERED OF ORDER
    public Boolean setStatusDelivered(String orderNum) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "UPDATE " + TABLE_FOR_ORDER_NAME + " SET " + "Status = 'Delivered' WHERE OrderID = '"+orderNum+"'";
        database.execSQL(query);
        return true;
    }



}

