package com.example.parkshare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    private Context context;
    public static final String DATABASE_NAME = "Login.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "user_table";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRSTNAME = "First_Name";
    public static final String COLUMN_LASTNAME = "Last_Name";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_Phonenum = "Phone_num";



    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_FIRSTNAME + " TEXT, " +
                        COLUMN_LASTNAME + " TEXT, " +
                        COLUMN_PASSWORD + " TEXT, " +
                        COLUMN_EMAIL + " TEXT, " +
                        COLUMN_Phonenum + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}

