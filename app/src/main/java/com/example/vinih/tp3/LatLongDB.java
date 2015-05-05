package com.example.vinih.tp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinih on 04/05/2015.
 */
public class LatLongDB {

    private SQLiteDatabase db;
    private LatLongDatabaseHelper auxDB;
    Context context;
    ListView lv;
    ArrayAdapter<LatLong> adapter;


    public LatLongDB(Context context, ListView lv ) {
        this.context = context;
        this.lv = lv;
        auxDB = new LatLongDatabaseHelper(context);
    }
    public void open() {
        try {
            db = auxDB.getWritableDatabase();
        }catch (Exception e){}


    }

    public void close() {
        db.close();
    }

    public void insert(LatLong ll ){
        ContentValues value = new ContentValues();
        String mmla = ""+ll.getLat();
        String mmlo = ""+ll.getLong();

        value.put("lat", mmla);
        value.put("long", mmlo);

        db.insert("lldb", null, value);

    }

    public void limpa(){
        auxDB.onUpgrade(db);

    }

    public List<LatLong> busca(){
        List<LatLong> list = new ArrayList<LatLong>();
        String [] coluna = new String[]{"lat","long"};
        Cursor cursor = db.query("lldb", coluna, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                LatLong mll = new LatLong();
                mll.setLat(cursor.getLong(0));
                mll.setLong(cursor.getLong(1));

                list.add(mll);

            }while (cursor.moveToNext());
        }
        return list;

    }
}
