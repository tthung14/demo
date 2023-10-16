package com.conga.demo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "person.db";
    private static final int VERSION = 1;
    public static final String TABLE_NAME = "person";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String GENDER = "gender";
    public static final String ADDRESS = "address";

    public static final String NATIONALITY = "nationality";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT NOT NULL,"
                + GENDER + " TEXT NOT NULL,"
                + ADDRESS + " TEXT NOT NULL,"
                + NATIONALITY + " TEXT NOT NULL)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addPerson(Person person) {
        if (person != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME, person.getName());
            contentValues.put(GENDER, person.getGender());
            contentValues.put(ADDRESS, person.getAddress());
            contentValues.put(NATIONALITY, person.getNationality());

            long response = db.insert(TABLE_NAME, null, contentValues);
            db.close();

            if (response > -1) {
                return false;
            }
            return  true;
        }
        return false;
    }

    public boolean updatePerson(int id, Person person) {
        if (id >= 0 && person != null) {
            SQLiteDatabase db = getWritableDatabase();
            String whereClause = ID + " =?";
            String[] whereArgs = {id + ""}; // điều kiện so sánh
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME, person.getName());
            contentValues.put(GENDER, person.getGender());
            contentValues.put(ADDRESS, person.getAddress());
            contentValues.put(NATIONALITY, person.getNationality());
            db.update(TABLE_NAME, contentValues, whereClause, whereArgs);
            db.close();
            return true;
        }
        return false;
    }

    public boolean deletePerson(int id) {
        if (id >= 0) {
            SQLiteDatabase db = getWritableDatabase();
            String whereClause = ID + " =?";
            String[] whereArgs = {id + ""}; // điều kiện so sánh

            db.delete(TABLE_NAME, whereClause, whereArgs);
            db.close();
            return true;
        }
        return false;
    }

    @SuppressLint("Range") // để khônng phải nhớ chỉ số hàng
    public Person getPersonById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id +"";
        Cursor cursor = db.rawQuery(sql, null);
        Person person = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                person = new Person();
                person.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                person.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                person.setGender(cursor.getString(cursor.getColumnIndex(GENDER)));
                person.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
                person.setNationality(cursor.getString(cursor.getColumnIndex(NATIONALITY)));
            }
            cursor.close();
        }
        db.close();
        return person;
    }

    public Boolean chkPersonById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id +"";
        Cursor cursor = db.rawQuery(sql, null);
        boolean personExists = false;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                personExists = true;
            }
            cursor.close();
        }
        db.close();

        return personExists;
    }
}
