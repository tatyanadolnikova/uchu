package com.example.android.uchu.ui.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;


public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "uchu.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TASKS_TABLE = "CREATE TABLE " + "users" + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "email" + " TEXT NOT NULL, "
                + "password" + " TEXT NOT NULL, "
                + "name" + " TEXT NOT NULL, "
                + "surname" + " TEXT NOT NULL, "
                + "gender" + " INTEGER NOT NULL, "
                + "birthday" + " TEXT NOT NULL, "
                + "city" + " TEXT NOT NULL, "
                + "skill" + " TEXT NOT NULL)";
        db.execSQL(SQL_CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long findUser(String email, String password) {
        long userID;
        String query = "Select _id " +
                "from users " +
                "where email ='" + email + "' and password ='" + password + "'";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor == null) userID = -1;
        else userID = cursor.getLong(cursor.getColumnIndex("_id"));

        return userID;
    }
}
