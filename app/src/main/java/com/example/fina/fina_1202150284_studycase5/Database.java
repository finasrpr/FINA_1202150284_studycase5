package com.example.fina.fina_1202150284_studycase5;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    //deklarasi variabel yang digunakan
    Context context;
    SQLiteDatabase data;

    public static final String nama_db = "Modul5.db";
    public static final String nama_table = "Todo";
    public static final String kolom1 = "judul";
    public static final String kolom2 = "deskripsi";
    public static final String kolom3 = "priority";

    //konstruktor
    public Database(Context context) {
        super(context, nama_db, null, 1);
        this.context = context;
        this.data = this.getWritableDatabase();
        data.execSQL("create table if not exists "+nama_table+" (judul varchar(50) primary key, deskripsi varchar(50), priority varchar(50)) ");
    }
    //method saat database dibuat
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+nama_table+" (judul varchar(50) primary key, deskripsi varchar(50), priority varchar(50)) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("drop table if exists "+nama_table);
    onCreate(sqLiteDatabase);
    }
    public boolean inputdata (itemTodo item){
        //membuat content values baru
        ContentValues cv = new ContentValues();
        //menyamakan kolom dan nilainya
        cv.put(kolom1, item.getTodo());
        cv.put(kolom2, item.getDesc());
        cv.put(kolom3, item.getPrior());
        long hasil = data.insert(nama_table, null, cv);
        if (hasil==-1){
            return false;
        }else {
            return true;
        }
    }
    //method untuk menghapus data pada database
    public boolean hapusdata (String nama){
        return data.delete(nama_table, kolom1+"=\""+nama+"\"", null)>0;
    }
    //method untuk mengakses dan membaca data dari database
    public void getAllItem (ArrayList<itemTodo> list){
        Cursor cursor = this.getReadableDatabase().rawQuery("select judul, deskripsi, priority from "+nama_table, null);
        while (cursor.moveToNext()){
            list.add(new itemTodo(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
    public void clearTable() {
        data.execSQL("delete from "+nama_table);
    }
}
