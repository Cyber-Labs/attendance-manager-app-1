package com.example.roshni.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="Attendance.db";
    public static final String TABLE_NAME="attendance";
    public static final String COL1="id";
    public static final String COL2="subject_name";
    public static final String COL3="presents";
    public static final String COL4="absents";
    public static final String COL5="current_percent";
    public static final String COL6="minimum_percent";

    public DataBaseHelper(Context context)
    {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, "+COL2+" TEXT, "+COL3+" TEXT, "+COL4+" TEXT, "+COL5+" TEXT, "+COL6+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public long insertData(String name,String minimum)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(COL2,name);
        value.put(COL3,"0");
        value.put(COL4,"0");
        value.put(COL5,"0");
        value.put(COL6,minimum);
        long result=db.insert(TABLE_NAME,null,value);
        return result;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }
}
