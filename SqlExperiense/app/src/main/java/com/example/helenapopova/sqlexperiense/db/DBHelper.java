package com.example.helenapopova.sqlexperiense.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

   public static final  int DATABASE_VERSION = 1;
   public static final String DATABASE_NAME  = "contactDB";
   public static final String TABLE = "contactDB";
   public static final String KEY_ID  = "_id";
   public static final String KEY_NAME  = "name";
   public static final String KEY_MAIL  = "email";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sq) {
        sq.execSQL("create table "
                + TABLE + "("
                + KEY_ID + " integer primary key, "
                + KEY_NAME +  " text,"
                + KEY_MAIL + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sq, int i, int i1) {
        sq.execSQL("drop table if exists " + TABLE);
        onCreate(sq);
    }
}
