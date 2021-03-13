package com.raykaco.andriod.database;

import android.database.Cursor;
import android.util.Log;


public class Check extends DataAccess {

    private String  shahr;
    private Integer ostan;
    private int     id;


    public void setOn() {
        openDatabase();
        String sql = "update checkInvoke set invoke_on_off=1";
        newDb.execSQL(sql);
        newDb.close();

    }


    public void setOff() {
        openDatabase();
        String sql = "update checkInvoke set invoke_on_off=0";
        newDb.execSQL(sql);
        newDb.close();

    }


    public int selectId() {
        Log.i("SQL", "sql");
        openDatabase();
        String sql = "select invoke_on_off from checkInvoke";
        Cursor cursor = newDb.rawQuery(sql, null);
        int count = 0;
        Log.i("SQL", sql);
        while (cursor.moveToNext()) {

            count = cursor.getInt(cursor.getColumnIndex("invoke_on_off"));
        }
        Log.i("SQL", "" + count);
        cursor.close();
        newDb.close();
        return count;

    }


    public String selectText() {
        Log.i("SQL", "sql");
        openDatabase();
        String sql = "select code from checkInvoke";
        Cursor cursor = newDb.rawQuery(sql, null);
        String count = " ";
        Log.i("SQL", sql);
        while (cursor.moveToNext()) {
            count = cursor.getString(cursor.getColumnIndex("code"));
        }
        Log.i("SQL", "" + count);
        cursor.close();
        newDb.close();
        Log.i("DDD", "" + count);
        return count;

    }

}
