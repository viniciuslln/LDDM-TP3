package com.example.vinih.tp3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vinih on 04/05/2015.
 */
public class LatLongDatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_DB = "LatLongDB";
    private static final int VERSAO_DB = 1;

    public LatLongDatabaseHelper(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATA_TABLE = "CREATE TABLE lldb ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lat TEXT, "+
                "long TEXT )";
        db.execSQL(CREATE_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS lldb");
        this.onCreate(db);
    }

    //@Override
    public void onUpgrade(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS lldb");
        this.onCreate(db);
    }
}
