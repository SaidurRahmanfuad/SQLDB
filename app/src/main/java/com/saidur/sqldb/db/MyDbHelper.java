package com.saidur.sqldb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.saidur.sqldb.model.Farmer;

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String DatabaseName = "farmar.db";
    private static final String TableFarmer = "farmar_table";
    private static final String TableFarmerDistrict = "district_table";
    private static final int VersionNumber = 2;
    //farmer table column name
    private static final String FID = "_id";
    private static final String NAME = "_name";
    private static final String FATHERS_NAME = "_fathers_name";
    private static final String STATUS = "_status";
    // private static final File Image=;
    //District table column
    private static final String DID = "_id";
    private static final String DNAME = "_name";

    //farmer table queriy
    private static final String create_table_farmer = "Create table " + TableFarmer
            + "(" + FID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME
            + " VARCHAR(255)," + FATHERS_NAME + "  VARCHAR(255)," + STATUS
            + "  VARCHAR(255)" + ")";
    private static final String create_table_dis = "Create table " + TableFarmerDistrict
            + "(" + DID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DNAME
            + " VARCHAR(255)" + ")";
    //drop table
    private static final String droptable = "Drop table if Exists " + TableFarmer;
    private static final String droptable2 = "Drop table if Exists " + TableFarmerDistrict;
//private static final String droptable = "Drop table if exist"+TableFarmerDistrict;

    //show all data query
    public static final String alldistrict_dataquery = "Select * From " + TableFarmerDistrict;
    private static final String allfarmer_dataquery = "Select * from " + TableFarmer+" order by "+FID+" desc";
    Context context;

    public MyDbHelper(@Nullable Context context) {
        super(context, DatabaseName, null, VersionNumber);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(create_table_farmer);
            sqLiteDatabase.execSQL(create_table_dis);
            Toast.makeText(context, "Table created", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Table created error : "+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //if update anything in database then drop previous table first the call onCreate Method


     /*   try {
            sqLiteDatabase.execSQL(droptable);
            sqLiteDatabase.execSQL(droptable2);
            onCreate(sqLiteDatabase);
            Toast.makeText(context, "On Upgrade", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Upgrade error : "+e, Toast.LENGTH_SHORT).show();
        }*/
    }

    public long insertFarmer(String name, String fname, String status) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(FATHERS_NAME, fname);
        cv.put(STATUS, status);
        long rowId = sqLiteDatabase.insert(TableFarmer, null, cv);
        return rowId;
    }
/*    public long insertFarmerUsingClass(String name, String fname, String status) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Farmer fm=new Farmer();
        fm.
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(FATHERS_NAME, fname);
        cv.put(STATUS, status);
        long rowId = sqLiteDatabase.insert(TableFarmer, null, cv);
        return rowId;
    }*/
//show all district data
    public Cursor show_AllDistrict_Data() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(alldistrict_dataquery, null);
    return cursor;
    }
//show all farmer data
    public Cursor show_AllFarmer_Data() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
      Cursor cursor = sqLiteDatabase.rawQuery(allfarmer_dataquery, null);
      return cursor;
    }
  /*  public boolean upateData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.rawQuery("Select * from "+TableFarmer+" Where "+NAME+" =?", new String[] {NAME});
    }*/

  //delete data
    public Integer deleteFarmer(String id)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
       return sqLiteDatabase.delete(TableFarmer,FID+" = ?",new String[] {id});

    }

}
