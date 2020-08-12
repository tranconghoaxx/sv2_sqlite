package com.example.sv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtHoTen;
    RadioButton radNam,radNu;
    Spinner spKhoa;
    Button btnThem,btnXoa,btnSua,btnClear;
    ListView lvDanhSach;
    ArrayList<String>dataKhoa = new ArrayList<>();
    ArrayList<SinhVien>datasv = new ArrayList<>();

    ArrayAdapter adapter_khoa;
    ArrayAdapter adapter_sv;

    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        adapter_khoa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dataKhoa);
        spKhoa.setAdapter(adapter_khoa);
//        moi vao phai hien thi du lieu cho trong sql
//        adapter_sv = new CustomAdapter(this,R.layout.listview_item,datasv);
//        lvDanhSach.setAdapter(adapter_sv);
        //sql
        final DBSinhVien dbSinhVien = new DBSinhVien(this);
        datasv = dbSinhVien.LayDL();
        adapter_sv = new CustomAdapter(this,R.layout.listview_item,datasv);
        lvDanhSach.setAdapter(adapter_sv);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                get sinh vien
                SinhVien sinhVien = new SinhVien();
                sinhVien.setHoTen(txtHoTen.getText().toString());
                sinhVien.setGioiTinh(radNam.isChecked());
                sinhVien.setKhoa(spKhoa.getSelectedItem().toString());

//        sqlite
                DBSinhVien dbSinhVien = new DBSinhVien(getApplicationContext());
                dbSinhVien.Them(sinhVien);
//no sqlite
//                datasv.add(sinhVien);
//                adapter_sv.notifyDataSetChanged();

//                hien thi dulieu
                datasv = dbSinhVien.LayDL();
                adapter_sv = new CustomAdapter(getApplicationContext(),R.layout.listview_item,datasv);
                lvDanhSach.setAdapter(adapter_sv);

            }
        });
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SinhVien sinhVien = datasv.get(i);
                txtHoTen.setText(sinhVien.getHoTen());
                if(sinhVien.isGioiTinh()){
                    radNam.setChecked(true);
                }else {
                    radNu.setChecked(true);
                }
                spKhoa.setSelection(dataKhoa.indexOf(sinhVien.getKhoa()));
                index = i;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                datasv.remove(index);
//                sql
                //                get sinh vien
                SinhVien sinhVien = new SinhVien();
                sinhVien.setHoTen(txtHoTen.getText().toString());
                sinhVien.setGioiTinh(radNam.isChecked());
                sinhVien.setKhoa(spKhoa.getSelectedItem().toString());

                DBSinhVien dbSinhVien = new DBSinhVien(getApplicationContext());
                dbSinhVien.Xoa(sinhVien);
                datasv.remove(index);

                adapter_sv.notifyDataSetChanged();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinhVien sinhVien = datasv.get(index);
                sinhVien.setHoTen(txtHoTen.getText().toString());
                sinhVien.setGioiTinh(radNam.isChecked());
                sinhVien.setKhoa(spKhoa.getSelectedItem().toString());
                adapter_sv.notifyDataSetChanged();
                dbSinhVien.Sua(sinhVien);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtHoTen.setText("");
                radNam.setChecked(true);
                spKhoa.setSelection(0);
            }
        });
    }
    private void KhoiTao(){
        dataKhoa.add("CNTT");
        dataKhoa.add("Ke Toan");
        dataKhoa.add("Du Lich");
    }

    private void setControl() {
        txtHoTen = findViewById(R.id.txtHoten);
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
        spKhoa = findViewById(R.id.spKhoa);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnClear = findViewById(R.id.btnClear);
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnDoc:
                Toast.makeText(getApplication(),"Doc",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, MainActivityDanhSach.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}