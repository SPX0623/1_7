package com.example.a1_7.bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class WDLS extends SQLiteOpenHelper {
    public WDLS(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table TQ(" +
                "id integer primary key autoincrement," +
                "wd text" +
                "sd text" +
                "gz text" +
                "co text" +
                "pm text" +
                "dl text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
