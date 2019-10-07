package com.example.android.uchu.ui.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.uchu.DataType;
import com.example.android.uchu.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static int USER_ID = 1;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "uchu";
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_CITY = "city";
    private static final String KEY_SKILL = "skill";
    private static final String KEY_PERSONAL_INFO = "info";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_EMAIL + " TEXT, "
                + KEY_PASSWORD + " TEXT, "
                + KEY_NAME + " TEXT, "
                + KEY_SURNAME + " TEXT, "
                + KEY_BIRTHDAY + " TEXT, "
                + KEY_CITY + " TEXT, "
                + KEY_SKILL + " TEXT, "
                + KEY_PERSONAL_INFO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.i("my_log", "DBHandler db created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_SURNAME, user.getSurname());
        values.put(KEY_BIRTHDAY, user.getBirthday());
        values.put(KEY_CITY, user.getCity());
        values.put(KEY_SKILL, user.getSkill());
        values.put(KEY_PERSONAL_INFO, user.getInfo());
        USER_ID = (int) db.insert(TABLE_USERS, null, values);
        Log.i("my_log", "DBHandler id: " + USER_ID + " " + values);
        db.close();
    }

    public long getUserId(String useremail, String userpassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id from users where email=? and password=?", new String[]{useremail, userpassword});
        try {
            if (cursor != null && cursor.moveToFirst())
                USER_ID = Integer.parseInt(cursor.getString(0));
            Log.i("my_log", "DBHandler id = " + USER_ID);
            cursor.close();
        } catch (NullPointerException e) {
        }
        return USER_ID;
    }

    public String getUserData(DataType dataType) {
        String data = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where id=?", new String[]{String.valueOf(USER_ID)});
        try {
            if (cursor.moveToFirst())
                USER_ID = Integer.parseInt(cursor.getString(0));
            Log.i("my_log", "id = " + DatabaseHandler.USER_ID);
        } catch (NullPointerException e) {
        }
        switch (dataType) {
            case EMAIL:
                data = cursor.getString(1);
                break;
            case PASSWORD:
                data = cursor.getString(2);
                break;
            case NAME:
                data = cursor.getString(3);
                break;
            case SURNAME:
                data = cursor.getString(4);
                break;
            case BIRTHDAY:
                data = cursor.getString(5);
                break;
            case CITY:
                data = cursor.getString(6);
                break;
            case SKILL:
                data = cursor.getString(7);
                break;
            case PERSONAL_INFO:
                data = cursor.getString(8);
                break;
        }
        cursor.close();
        return data;
    }

    public List<User> getAllUsers() {
        List<User> contactList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                int userId = Integer.parseInt(cursor.getString(0));
                user.setEmail(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                /* Do the rest */
                contactList.add(user);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_SURNAME, user.getSurname());
        values.put(KEY_BIRTHDAY, user.getBirthday());
        values.put(KEY_CITY, user.getCity());
        values.put(KEY_SKILL, user.getSkill());
        values.put(KEY_PERSONAL_INFO, user.getInfo());

        db.update(
                TABLE_USERS,
                values,
                KEY_ID + " = ?",
                new String[]{String.valueOf(USER_ID)});
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, null, null);
        db.close();
    }

}
