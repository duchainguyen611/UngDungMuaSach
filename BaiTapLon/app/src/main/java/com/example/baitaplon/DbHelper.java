package com.example.baitaplon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myapp.db";
    private static final String TABLE_NAME = "accounts";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_user_table = String.format("CREATE TABLE %s(%s INTEGER AUTOINCREMENT PRIMARY KEY, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_USERNAME, KEY_PASSWORD);
        db.execSQL(create_user_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_User_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_User_table);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUserName());
        values.put(KEY_PASSWORD, user.getPassWord());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteAllUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public void createDefaultUserIfNeed()  {
        int count = this.getUserCount();
        if(count ==0 ) {
            User u1 = new User(1,"Hai","123456");
            this.addUser(u1);
        }
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT*FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            User user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            userList.add(user);
            cursor.moveToNext();
        }
        return userList;
    }

    public boolean checkAccountLogin(String username, String password) {
        String[] columns = { KEY_ID };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = KEY_USERNAME + " = ?" + " AND " + KEY_PASSWORD + " = ?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        if(count > 0) return true;
        cursor.close();
        db.close();
        return false;
    }

    public boolean checkUser(String username) {
        String[] columns = { KEY_ID };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = KEY_USERNAME + " = ?";
        String[] selectionArgs = { username };
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        if(count > 0) return true;
        cursor.close();
        db.close();
        return false;
    }

}


