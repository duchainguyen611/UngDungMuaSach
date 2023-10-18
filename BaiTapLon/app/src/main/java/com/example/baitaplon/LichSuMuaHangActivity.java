package com.example.baitaplon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LichSuMuaHangActivity extends AppCompatActivity {

    DbHelper2 dbHelper2 = new DbHelper2(LichSuMuaHangActivity.this);
    AdapterLichSuMuaHang adapterLichSuMuaHang;
    ArrayList<LichSuMuaHang> arrayList = new ArrayList<>();
    ListView lsvLichSuMuaHang;
    Button btnLSMHBack;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lichsumuahang);
        map();
        arrayList = (ArrayList<LichSuMuaHang>) dbHelper2.getSanPhamTheoUserSapXepTheoID(MainActivity.user);
        adapterLichSuMuaHang = new AdapterLichSuMuaHang(this,arrayList);
        lsvLichSuMuaHang.setAdapter(adapterLichSuMuaHang);
        adapterLichSuMuaHang.notifyDataSetChanged();

        btnLSMHBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LichSuMuaHangActivity.this,GioHangActivity.class);
                startActivity(intent);
            }
        });
    }
    private void map(){
        lsvLichSuMuaHang = findViewById(R.id.lsv_lichsumuahang);
        btnLSMHBack = findViewById(R.id.btn_LSMHBack);
    }
}
