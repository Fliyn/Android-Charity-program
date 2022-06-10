package com.example.FliynProjesi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
        public static final String DBNAME ="Login.db";
        public static final String TABLE_NAME = "users";
    public DBHelper(Context context) {
        super(context, "Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("Create Table users(username TEXT primary key, password TEXT,para int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
    }
    public Boolean insertdata(String username,String password,int para){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("para",para);
        long result=MyDB.insert("users",null,contentValues);
        if (result==-1) return false;
        else return true;
    }

    public boolean chechusername(String username){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("select * From users Where username=?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checketki(String username){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("select * From users Where username=?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean chechkusernamepassword(String username,String password){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("select * from users where username = ? and password = ?", new String []{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public boolean updateData(String username,int para){//String password eklenebilir
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("para",para);//statement değişme   "para"+para
        long result =db.update("users", cv,"username=?",new String[]{username});//WHERE CLAUSE VE DİĞERİNİ YAPABİLMELİYİM
        if (result==-1) return false;
        else return true;
    }

    public Cursor bombaparahesap(String username){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor canna = db.rawQuery("select * from users where username=? ",new String []{username});//SWEEET JSZ
            return canna;
    }
    public Cursor datataking(){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor canna = db.rawQuery("select * from users where username LIKE '__%'  AND para>10 AND NOT username='Anonim Bağışçı'",null);//SWEEET JSZ STİLL WE K US THT    AND NOT username='Fliyn'
            return canna;
    }


}
