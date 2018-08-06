package com.example.helenapopova.sqlexperiense;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.helenapopova.sqlexperiense.db.DBHelper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

public class ContactOneActivity extends AppCompatActivity implements View.OnClickListener {
    Button bAdd, bRead, bClear, bUp, bDel, bSelect;
    EditText cName, cEmail, cId;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_one);
        registerButton();
        dbHelper = new DBHelper(this);
    }

    public void registerButton() {
        cName = findViewById(R.id.custum_name);
        cEmail = findViewById(R.id.custum_email);
        bAdd = findViewById(R.id.add_buttom);
        bAdd.setOnClickListener(this);
        bRead = findViewById(R.id.read_buttom);
        bRead.setOnClickListener(this);
        bClear = findViewById(R.id.clear_buttom);
        bClear.setOnClickListener(this);
        bUp = findViewById(R.id.update_buttom);
        bUp.setOnClickListener(this);
        bDel = findViewById(R.id.delite_buttom);
        bDel.setOnClickListener(this);
        cId = findViewById(R.id.custum_id);
        bSelect = findViewById(R.id.select_buttom);
        bSelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = cName.getText().toString();
        String email = cEmail.getText().toString();
        String id = cId.getText().toString();

        SQLiteDatabase sqDB = dbHelper.getWritableDatabase();

        ContentValues contentV= new ContentValues();

        switch (view.getId()) {
            case R.id.add_buttom:
                addDB(sqDB, contentV, name, email);
                break;
            case R.id.read_buttom:
                readDB(sqDB, contentV);
                break;
            case R.id.clear_buttom:
                deleteDB(sqDB, contentV);
                break;
            case R.id.update_buttom:
                updateDB(sqDB, contentV, name, email, id);
                break;
            case R.id.delite_buttom:
                deletePointDB(sqDB, id);
                break;
            case R.id.select_buttom:
                selectPointDB(sqDB, id);
                break;


        }
        dbHelper.close();
    }

    private void selectPointDB(SQLiteDatabase sqDB, String id) {
        Cursor offer = null;
        if ( !id.equalsIgnoreCase("")) {
            String selection = DBHelper.KEY_ID + " =? ";
            String[] selectArgs =new String[] {id};
            offer = sqDB.query(DBHelper.TABLE, null,selection, selectArgs, null,null, null );
            Log.d("mLog","Go");
            if(offer.moveToFirst())
            {
                int idIndex = offer.getColumnIndex(DBHelper.KEY_ID);
                int idName = offer.getColumnIndex(DBHelper.KEY_NAME);
                int idEmail = offer.getColumnIndex(DBHelper.KEY_MAIL);

                do {
                    Log.d("mLog", String.format("ID = %d, name = %s, email = %s /n",
                            offer.getInt(idIndex),
                            offer.getString(idName),
                            offer.getString(idEmail)));
                } while (offer.moveToNext());
            } else
                Log.d("mLog","0 row");
            offer.close();
        } else {
            Log.d("mLog","no select");
        }

    }
    private void updateDB(SQLiteDatabase sqDB, ContentValues contentV, String name, String email, String id) {
        if ( !id.equalsIgnoreCase("")) {
            contentV.put(DBHelper.KEY_NAME, name);
            contentV.put(DBHelper.KEY_MAIL, email);
            int upCount = sqDB.update(DBHelper.TABLE, contentV, DBHelper.KEY_ID + "= ?", new String[]{id});
            Log.d("mLog","update - " + upCount);
        } else {
            Log.d("mLog","no update");
        }

    }

    private void deletePointDB(SQLiteDatabase sqDB, String id) {
        if ( !id.equalsIgnoreCase("")) {
            int deleteCount = sqDB.delete(DBHelper.TABLE, DBHelper.KEY_ID + "=" + id,  null);
            Log.d("mLog","delete  - " + deleteCount);
        } else {
            Log.d("mLog","no delete");
        }

    }

    private void addDB(SQLiteDatabase sqDB, ContentValues contentV, String name, String email) {
        contentV.put(DBHelper.KEY_NAME, name);
        contentV.put(DBHelper.KEY_MAIL, email);
        sqDB.insert(DBHelper.TABLE, null, contentV);
        Log.d("mLog","add row");
    }

    private void readDB(SQLiteDatabase sqDB, ContentValues contentV) {
        Cursor cursor = sqDB.query(DBHelper.TABLE, null, null, null,null,null,null);
        if(cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int idName = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int idEmail = cursor.getColumnIndex(DBHelper.KEY_MAIL);

            do {
                Log.d("mLog", String.format("ID = %d, name = %s, email = %s",
                        cursor.getInt(idIndex),
                        cursor.getString(idName),
                        cursor.getString(idEmail)));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 row");
        cursor.close();
    }


    private void deleteDB(SQLiteDatabase sqDB, ContentValues contentV) {
        sqDB.delete(DBHelper.TABLE, null, null);
        Log.d("mLog","0 row");
    }




    public void init() {
        int[] position_id = {1, 2, 3, 4};
        String[] position_name = {"Ingineer", "Surgion", "Dantist", "Nurse"};
        int[] salary = {20000, 10000, 3000, 1000};
        String[] peple_name = {"Ann", "Helen", "Alex", "Jon", "Ben", "Albert"};
        int[] people_position ={1, 2, 3, 4, 5, 6};

        ContentValues cv  = new ContentValues();
        SQLiteDatabase sqDBNew = dbHelper.getWritableDatabase();

        sqDBNew.execSQL("create table position ("
                + " id integer primary key, "
                + " name text, "
                + " salary integer);");

        for (int i = 0; i < position_id.length; i++) {
            cv.clear();
            cv.put("id", position_id[i]);
            cv.put("name", position_name[i]);
            cv.put("salary", salary[i]);
            sqDBNew.insert("position", null, cv);
        }

        sqDBNew.execSQL("create table people ("
                + " id integer primary key autoincrement, "
                + " name text, "
                + " posidid integer);");

        for (int i = 0; i < peple_name.length; i++) {
            cv.clear();
            cv.put("name", peple_name[i]);
            cv.put("salary", people_position[i]);
            sqDBNew.insert("people", null, cv);
        }


        Cursor cursor = sqDBNew.query("position", null, null, null, null, null, null);
        cursor.close();

        cursor = sqDBNew.query("people", null, null, null, null, null, null);
        cursor.close();

        String query = "select PL.name as Name, PS.name as Position, salary as Salary" +
                "from people as PL " +
                "inner join posirion as PS" +
                "on PL.posid = PS";

    }
}
