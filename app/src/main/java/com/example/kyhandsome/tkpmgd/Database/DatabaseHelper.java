package com.example.kyhandsome.tkpmgd.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DB.sqlite";
    public static final String TABLE_NAME = "worddo";
    public static final String COL_1 = "id";
    public static final String COL_2 = "name";
    public static final String COL_3 = "sid";
    public static final String COL_4 = "star";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("abc", "KY KY KY KY KY");
        db.execSQL("create table " + TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, sid INTEGER,star INTEGER)");
        Log.d("abc", "KY KY KY KY KY");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String sid,String star) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,sid);
        contentValues.put(COL_4,star);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public int getIdSound(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select sid from " + TABLE_NAME + " where "+ TABLE_NAME+".sid =" + id,null);
        Log.d("abc", "lenghtttttttttt " + res.getCount());
        if (res.getCount() == 0) {
            return 0;
        }
        return 1;
    }

    public int getId(int sid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select id from " + TABLE_NAME + " where "+ TABLE_NAME+".sid =" + sid,null);
        Log.d("abc", "id id id id " + res.getCount());


        res.moveToFirst();
        int a = res.getInt(0);
        return a;
    }

    public Cursor getAllDataWord() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String sid,String star) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,sid);
        contentValues.put(COL_4,star);
        db.update(TABLE_NAME, contentValues, "id = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?",new String[] {id});
    }
}
