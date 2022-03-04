package com.example.sample5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper  extends SQLiteOpenHelper{
    public static final String COL_1 = "licenseNumber";
    public static final String COL_2 = "vehicleType";
    public static final String COL_3 = "vehicleNumber";
    public static final String COL_4 = "owner";
    public static final String COL_5 = "email";
    public static final String COLn_1= "licenseNumber";
    public static final String COLn_2= "date_re_license";
    public static final String COLn_3= "date_re_insuarance";
    public static final String COLn_4= "eco_test";
    public static final String COLn_5= "carbon_test";
    public static final String COLn_6= "fitness_test";


    public DatabaseHelper(Context context){
        super(context,"dt2.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table usernew2(licenseNumber text primary key,vehicleType text,vehicleNumber text,owner text,email text)");
        db.execSQL("Create table secntable(licenseNumber text primary key,date_re_license date,date_re_insuarance date,eco_test text,carbon_test text,fitness_test text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usernew2");
        db.execSQL("drop table if exists secntable");
    }
    public boolean insert(String licenseNumber,String vehicleType,String vehicleNumber,String owner,String email){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentvalues=new ContentValues();
        contentvalues.put(COL_1,licenseNumber);
        contentvalues.put(COL_2,vehicleType);
        contentvalues.put(COL_3,vehicleNumber);
        contentvalues.put(COL_4,owner);
        contentvalues.put(COL_5,email);
        long ins=db.insert("usernew2",null,contentvalues);
        if(ins==-1) return false;
        else return true;
    }

    public boolean insertsec(String licenseNumber,String date_re_license,String date_re_insuarance,String eco_test,String carbon_test,String fitness_test){
        SQLiteDatabase db=this.getWritableDatabase();


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues contentvalues=new ContentValues();
        contentvalues.put(COLn_1, licenseNumber);
        contentvalues.put(COLn_2, date_re_license);
        contentvalues.put(COLn_3, date_re_insuarance);
        contentvalues.put(COLn_4,eco_test);
        contentvalues.put(COLn_5,carbon_test);
        contentvalues.put(COLn_6,fitness_test);

        long ism=db.insert("secntable",null,contentvalues);
        if(ism==-1) return false;
        else return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+"usernew2",null);
        return res;
    }

    public Cursor getAllData2() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+"secntable",null);
        return res;
    }
    public Cursor getAllData3(String licenseNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from usernew2 where licenseNumber=?",new String[]{licenseNumber});
        return res;
    }

    public Cursor getAllData4(String licenseNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from secntable where licenseNumber=?",new String[]{licenseNumber});
        return res;
    }



    public Boolean chklicenseNumber(String licenseNumber){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from usernew2 where licenseNumber=?",new String[]{licenseNumber});
        if(cursor.getCount()>0) return true;
        else return false;
    }
    public boolean updateData(String id,String date1,String date2,String test1,String test2,String test3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLn_1,id);
        contentValues.put(COLn_2,date1);
        contentValues.put(COLn_3,date2);
        contentValues.put(COLn_4,test1);
        contentValues.put(COLn_5,test2);
        contentValues.put(COLn_6,test3);
        db.update("secntable", contentValues, "licenseNumber = ?",new String[] { id });
        return true;
    }


}
