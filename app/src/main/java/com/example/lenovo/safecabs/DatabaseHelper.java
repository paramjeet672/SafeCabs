package com.example.lenovo.safecabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lenovo on 13-06-2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME= "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "pass";
    SQLiteDatabase db;
    public static final String TABLE_CREATE = "CREATE TABLE users (id integer primary key not null, " +
            "name text not null, email text not null, pass text not null);";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    public void insertContact(Contact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_PASS,c.getPass());

        db.insert(TABLE_NAME, null, values);
    }

    public String searchPass(String email){
        db = this.getReadableDatabase();
        String query = "SELECT email,pass FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b = "Not Found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);


                if(a.equals(email)){
                    b = cursor.getString(1);
                    break;
                }

            }while (cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
