package com.example.sv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

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

        adapter_sv = new CustomAdapter(this,R.layout.listview_item,datasv);
        lvDanhSach.setAdapter(adapter_sv);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinhVien sinhVien = new SinhVien();
                sinhVien.setHoTen(txtHoTen.getText().toString());
                sinhVien.setGioiTinh(radNam.isChecked());
                sinhVien.setKhoa(spKhoa.getSelectedItem().toString());
                datasv.add(sinhVien);
                adapter_sv.notifyDataSetChanged();
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
}