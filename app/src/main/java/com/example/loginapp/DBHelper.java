package com.example.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "Login.db";
    private static String TABLE_NAME = "LoginDetails";
    public static String NAME = "NAME";
    public static String ADDRESS = "ADDRESS";
    public static String MOBILE = "MOBILE";
    public static String DOB = "DOB";
    public static String EMAIL = "EMAIL";
    public static String USERNAME = "USERNAME";
    public static String PASSWORD = "PASSWORD";

    SQLiteDatabase db = this.getWritableDatabase();

    public DBHelper(Context context) {
        super(context,DB_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" (NAME TEXT,ADDRESS TEXT,MOBILE TEXT,DOB TEXT,EMAIL TEXT,USERNAME TEXT PRIMARY KEY,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    boolean insertData(String name,String address,String mn,String dob,String email,String username,String password){

        ContentValues values = new ContentValues();
        values.put(NAME,name);
        values.put(ADDRESS,address);
        values.put(MOBILE,mn);
        values.put(DOB,dob);
        values.put(EMAIL,email);
        values.put(USERNAME,username);
        values.put(PASSWORD,password);

        long result = db.insert(TABLE_NAME,null,values);
        return result!=-1;
    }

    Cursor showdata(String activeuser)
    {
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE USERNAME = ?",new String[]{activeuser});
        return cursor;
    }

    boolean checkuserpass(String username,String password){

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+ " WHERE USERNAME = ? AND PASSWORD = ?", new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
}
