package org.meicode.nutritionapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "NutritionData.db";

    public DBHelper(@Nullable Context context) {
        super(context, "NutritionData.db", null, 3);
    }

    //Creating the table
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT, insulin TEXT)");
        MyDB.execSQL("create Table restaurants(restaurantname TEXT primary key, subcategory TEXT, itemname TEXT, carbohydrates INT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists restaurants ");

    }
    //Function to insert the data from fields on the frontend into the database
    public Boolean insertData(String username, String password, String insulin){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("insulin", insulin);

        long result = MyDB.insert("users", null, contentValues);
        if(result ==-1) return false;
        else
            return true;
    }

    public Boolean insertDataRestaurants(String restaurantname, String subcategory, String itemname, String carbohydrates){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("restaurantname", restaurantname);
        contentValues.put("subcategory", subcategory);
        contentValues.put("itemname", itemname);
        contentValues.put("carbohydrates",carbohydrates);
        long result = MyDB.insert("restaurants", null, contentValues);
        if(result ==-1) return false;
        else
            return true;
    }
    //Function to check username in the database
    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username  = ?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //Function to check username and password in the database
    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MYDB = this.getWritableDatabase();
        Cursor cursor = MYDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            String rowUsername = cursor.getString(0);
            String rowPassword = cursor.getString(1);
            return true;
        }
        else
            return false;
    }

    public String retrieveInsulin (String username) {

        SQLiteDatabase MYDB = this.getReadableDatabase();
        Cursor cursor = MYDB.query("users", new String[]{"insulin"},"username = ?", new String[]{username}, null,null,"username DESC", "1");
        if (cursor.moveToFirst()) {
            String insulin = cursor.getString(cursor.getColumnIndexOrThrow("insulin"));
            return insulin;
        }
        else{
            return "no value";
        }
    }
    public Boolean updateInsulin (String username, String insulin){
        SQLiteDatabase MYDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("insulin", insulin);
        MYDB.update("users", contentValues, "username = ?",new String[]{username});
        return true;
        }
    }