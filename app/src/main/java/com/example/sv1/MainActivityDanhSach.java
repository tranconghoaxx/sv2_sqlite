package com.example.sv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivityDanhSach extends AppCompatActivity {
    ListView lvDanhSach;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danh_sach);
        lvDanhSach = findViewById(R.id.lvDanhSach2);
        DBSinhVien dbSinhVien = new DBSinhVien(this);
        customAdapter = new CustomAdapter(this,R.layout.listview_item,dbSinhVien.LayDL());
        lvDanhSach.setAdapter(customAdapter);
    }
}