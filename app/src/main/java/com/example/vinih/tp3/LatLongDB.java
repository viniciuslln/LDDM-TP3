package com.example.vinih.tp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
    String [] colunas = new String[]{"_id","lat","long", "time"};



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

    public LatLong insert(LatLong ll ){
        ContentValues value = new ContentValues();
        String mmla = ""+ll.getLat();
        String mmlo = ""+ll.getLong();
        String mmti = ll.getMdate();

        value.put("lat", mmla);
        value.put("long", mmlo);
        value.put("time", mmti);

        long insertId = db.insert("lldb", null, value);

        Cursor cursor = db.query( "lldb", colunas,  "_id = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        LatLong newLatLong = cursorToLatLong(cursor);
        cursor.close();

        return newLatLong;
    }

    public void mDelete( LatLong ll) {
        long id = ll.getId();
        Toast.makeText(context, "Comment deleted with id: " + id, Toast.LENGTH_SHORT).show();
        db.delete("lldb", "_id = " + id, null);
    }

    public void limpa(){
        auxDB.onUpgrade(db);

    }

    public ArrayList<LatLong> busca(){
        ArrayList<LatLong> list = new ArrayList<LatLong>();
        Cursor cursor = db.query("lldb", colunas, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                LatLong latlong = new LatLong();
                latlong.setId(cursor.getLong(0));
                latlong.setLat(cursor.getDouble(1));
                latlong.setLong(cursor.getDouble(2));
                latlong.setMdate(cursor.getString(3));

                list.add(latlong);

            }while (cursor.moveToNext());
        }
        return list;

    }

    private LatLong cursorToLatLong(Cursor cursor) {
        LatLong latlong = new LatLong();
        latlong.setId(cursor.getLong(0));
        latlong.setLat(cursor.getDouble(1));
        latlong.setLong(cursor.getDouble(2));
        latlong.setMdate(cursor.getString(3));

        return latlong;
    }
}
