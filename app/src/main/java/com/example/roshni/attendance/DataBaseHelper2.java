package com.example.roshni.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper2 extends SQLiteOpenHelper
{
    public static final String TABLE_NAME2="profile";
    public static final String ID="id2";
    public static final String NAME="name";
    public static final String COLLEGE_NAME="college";
    public static final String SEMESTER="semester";

    public DataBaseHelper2(Context context)
    {
        super(context, TABLE_NAME2, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+TABLE_NAME2+" (id2 INTEGER PRIMARY KEY AUTOINCREMENT,  "+NAME+" TEXT, "+COLLEGE_NAME+" TEXT, "+SEMESTER+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(db);
    }
    public Cursor getAllDataagain()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+TABLE_NAME2,null);
        return res;
    }

    public void insertProfile(String get1, String get2, String get3)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(NAME,get1);
        value.put(COLLEGE_NAME,get2);
        value.put(SEMESTER,get3);
        db.insert(TABLE_NAME2,null,value);
    }

    public void updateProfile(String id, String get1, String get2, String get3)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(NAME,get1);
        value.put(COLLEGE_NAME,get2);
        value.put(SEMESTER,get3);
        db.update(TABLE_NAME2,value," ID = ?",new String []{id});
    }
}
