package com.example.ayrton.hihome.bancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bancodeDados extends SQLiteOpenHelper {
    static String DATABASE_NAME = "HiHome.db";
    static int DATABASE_VERSION = 2;

    public static String TABLE_NAME = "TB_CADASTROS";

    public static String COLUMN_ID = "ID";
    public static String COLUMN_PIN = "PIN";
    public static String COLUMN_LOGIN = "LOGIN";
    public static String COLUMN_SENHA = "SENHA";

    private static final String SCRIPT_DATABASE_CREATE = "create table "+
            TABLE_NAME+" ("+
            COLUMN_ID+" integer primary key autoincrement, "+
            COLUMN_PIN+" text not null, "+
            COLUMN_LOGIN+" text not null, "+
            COLUMN_SENHA+" text not null);";

    private static final String SCRIPT_DROP_TABLE = "drop table if exists "+TABLE_NAME+";";

    bancodeDados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCRIPT_DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SCRIPT_DROP_TABLE);
        onCreate(db);
    }
}
