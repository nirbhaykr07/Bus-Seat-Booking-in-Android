package com.project.MyBus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.net.PasswordAuthentication;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String  DATABASE_NAME                 = "MyBus.db";
    public static final String  TABLE_NAME_USER               = "registeruser";
    public static final String  TABLE_NAME_BUS                = "registerbus";
    public static final String  TABLE_NAME_TICKETS            = "tickets";
    public static final String  COL_2                         = "username";
    public static final String  COL_3                         = "password";
    public static final String  COL_4                         = "BUSID";
    public static final String  COL_5                         = "source";
    public static final String  COL_6                         = "destination";
    public static final String  COL_7                         = "seats";
    public static final String  COL_8                         = "date";
    public static final String  COL_9                         = "time";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE registerUser (username TEXT PRIMARY KEY, password TEXT) ");
        sqLiteDatabase.execSQL(" CREATE TABLE registerBus (BUSID TEXT PRIMARY KEY , source TEXT, destination TEXT, seats INTEGER, date TEXT, time TEXT) ");
        sqLiteDatabase.execSQL(" CREATE TABLE tickets (username TEXT , BUSID TEXT, source TEXT, destination TEXT, seats INTEGER, date TEXT, time TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME_BUS);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME_TICKETS);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);
        long res = db.insert("registeruser",null,contentValues);
        db.close();
        return res;
    }

    public long addBus(String busid, String source, String destination, int seats, String date, String time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("busid", busid);
        contentValues.put("source", source);
        contentValues.put("destination", destination);
        contentValues.put("seats", seats);
        contentValues.put("date", date);
        contentValues.put("time", time);
        long res = db.insert("registerbus",null,contentValues);
        db.close();
        return res;
    }

    public long addBus()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("busid", "ajf");
        contentValues.put("source", "b");
        contentValues.put("destination", "c");
        contentValues.put("seats", 100);
        contentValues.put("date", "d");
        contentValues.put("time", "e");
        long res = db.insert("registerbus",null,contentValues);
        db.close();
        return res;
    }

    public boolean updateBus(String busid, String source, String destination, int seats, String date, String time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("busid", busid);
        contentValues.put("source", source);
        contentValues.put("destination", destination);
        contentValues.put("seats", seats);
        contentValues.put("date", date);
        contentValues.put("time", time);
        long res = db.insert("registerbus",null,contentValues);
        db.close();
        return true;
    }

    public boolean removeBus(String busID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TABLE_NAME_BUS, "BUSID = ?",new String[] {busID});
        db.close();
        if(deletedRows > 0)
            return true;
        else
            return false;
    }

    public boolean removeUser(String userID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TABLE_NAME_USER, "ID = ?",new String[] {userID});
        db.close();
        if(deletedRows > 0)
            return true;
        else
            return false;
    }

    public boolean checkUser(String username, String password)
    {
        String [] columns = {COL_2} ;
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String [] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME_USER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count > 0)
            return true;
        else
            return false;
    }

    public List<String> getAvailableBus(String src, String dest, String date)
    {
        List<String> source = new ArrayList<String>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(true, TABLE_NAME_BUS, new String[] {
                        COL_4,
                        COL_5,
                        COL_6,
                        COL_7,
                        COL_8,
                        COL_9,},
                COL_5 + "=?" + " and "  +
                        COL_6 + "=?" + " and "  +
                        COL_8 + "=?",
                new String[] {src,dest,date},
                null, null, COL_4 , null);
        if (cursor.moveToFirst()) {
            do {
                source.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return source;
    }

    public List<String> getBusSource()
    {
        List<String> source = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(true, TABLE_NAME_BUS, new String[] { COL_4 ,COL_5, COL_6, COL_7 ,COL_8, COL_9 }, null, null, COL_5, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                source.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return source;
    }

    public List<String> getBusDest()
    {
        List<String> source = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(true, TABLE_NAME_BUS, new String[] { COL_4 ,COL_5, COL_6, COL_7 ,COL_8, COL_9 }, null, null, COL_6, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                source.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return source;
    }

    public boolean showSeats(String source, String dest, String date)
    {
        String [] columns = {"source"} ;
        SQLiteDatabase db = getReadableDatabase();
        String selection = "destination" + "=?" + " and " + COL_3 + "=?";
        String [] selectionArgs = {source, source};
        Cursor cursor = db.query(TABLE_NAME_USER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;
    }

    public String getSelectedSeat(String busID)
    {
        String selectQuery = "SELECT * FROM " + TABLE_NAME_BUS + " WHERE BUSID" + " = '" + busID+"'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{});
        String totalSeats=null;
        if(cursor.getCount() == 1){
            cursor.moveToFirst();
            totalSeats = cursor.getString(3);
        }
        cursor.close();
        db.close();

        return totalSeats;
    }

    public String getSelectedTime(String busID)
    {
        String selectQuery = "SELECT * FROM " + TABLE_NAME_BUS + " WHERE BUSID" + " = '" + busID+"'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{});
        String busTime=null;
        if(cursor.getCount() == 1){
            cursor.moveToFirst();
            busTime = cursor.getString(5);
        }
        cursor.close();
        db.close();

        return busTime;
    }

    public boolean bookTickets(String busID, String bookingSeats, String user, String src, String dest, String date, String time)
    {
        String selectQuery = "SELECT * FROM " + TABLE_NAME_BUS + " WHERE BUSID" + " = '" + busID+"'";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{});
        String totalSeats=null;
        if(cursor.getCount() == 1){
            cursor.moveToFirst();
            totalSeats = cursor.getString(3);
        }
        cursor.close();

        int tSeats = Integer.parseInt(totalSeats);
        int bSeats = Integer.parseInt(bookingSeats);
        int change = tSeats - bSeats;
        System.out.println("This is a test2");

        if(change < 0)
        {
            db.close();
            return false;
        }
        else
        {
            ContentValues cv = new ContentValues();
            cv.put("seats",change); //These Fields should be your String values of actual column names
            db.update(TABLE_NAME_BUS, cv, "BUSID=?", new String[] {busID});
            ContentValues cvTickets = new ContentValues();
            cvTickets.put("username",user);
            cvTickets.put("BUSID",busID);
            cvTickets.put("source",src);
            cvTickets.put("destination",dest);
            cvTickets.put("seats",bSeats);
            cvTickets.put("date",date);
            cvTickets.put("time",time);
            long res = db.insert(TABLE_NAME_TICKETS,null,cvTickets);
            db.close();
            return true;
        }
    }

    public Cursor getAllData(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_TICKETS +" WHERE username= " + user,null);
        return res;
    }
}
