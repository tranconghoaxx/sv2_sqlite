package com.example.sv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<SinhVien>data;
    public CustomAdapter( Context context, int resource, ArrayList<SinhVien>data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource,null);
        ImageView imgGT = view.findViewById(R.id.imgGT);
        TextView tvHoTen = view.findViewById(R.id.tvHoTen);
        TextView tvKhoa = view.findViewById(R.id.tvKhoa);

        SinhVien sinhVien = data.get(position);
        if(sinhVien.isGioiTinh()){
            imgGT.setImageResource(R.drawable.ic_baseline_accessibility_new_24);
        }else {
            imgGT.setImageResource(R.drawable.ic_baseline_pregnant_woman_24);
        }
        tvHoTen.setText(sinhVien.getHoTen());
        tvKhoa.setText(sinhVien.getKhoa());
        return  view;
    }
}
