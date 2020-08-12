package com.example.sv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBSinhVien {
    DBHelper dbHelper;

    public DBSinhVien(Context context) {
        this.dbHelper = new DBHelper(context);
    }
    public void Them(SinhVien sinhVien){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten",sinhVien.getHoTen());
        values.put("khoa",sinhVien.getKhoa());
        if(sinhVien.isGioiTinh()){
            values.put("gioitinh","nam");
        }else {
            values.put("gioitinh","nu");
        }
        db.insert("SinhVien",null,values);
    }
    public void Sua(SinhVien sinhVien){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten",sinhVien.getHoTen());
        values.put("khoa",sinhVien.getKhoa());
        if(sinhVien.isGioiTinh()){
            values.put("gioitinh","nam");
        }else {
            values.put("gioitinh","nu");
        }
        db.update("SinhVien",values,"hoten = '" + sinhVien.getHoTen() +"'" , null);
    }
    public  void Xoa(SinhVien sinhVien){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "delete from SinhVien where hoten = '" + sinhVien.getHoTen() + "'";
        db.execSQL(sql);
    }
    public ArrayList<SinhVien> LayDL(){
        ArrayList<SinhVien> data = new ArrayList<>();
        String sql = "select * from SinhVien";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        try{
            do{
                SinhVien sinhVien = new SinhVien();
                sinhVien.setHoTen(cursor.getString(0));
                if(cursor.getString(1).equals("nam")){
                    sinhVien.setGioiTinh(true);
                }else {
                    sinhVien.setGioiTinh(false);
                }
                sinhVien.setKhoa(cursor.getString(2));
                data.add(sinhVien);
            }while (cursor.moveToNext());
        }catch (Exception ex){
            Log.i("loi tag", "LayDL: loi lay dl db ");
        }
        return  data;
    }
}
