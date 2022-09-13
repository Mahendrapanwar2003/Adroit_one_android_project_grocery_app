package com.example.adroit_one_;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqLiteHelper extends SQLiteOpenHelper {
    public SqLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table User (id integer primary key autoincrement,Name text,Email text, Address text,Number text, Password text,Image blob)");
        sqLiteDatabase.execSQL("Create Table Product (id integer primary key autoincrement,Name text,Detail text,Price text,Image blob,Category text,Quantity)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
