package com.example.baitaplon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    ListView lsvGioHang;
    @SuppressLint("StaticFieldLeak")
    public static TextView tongTien;
    Button btnTiepTucMuaHang,btnThanhToan,btnVaoLichSuMuaHang;

    DbHelper2 dbHelper2 = new DbHelper2(GioHangActivity.this);

    AdapterGioHang adapterGioHang;
    ArrayList<GioHang> arrayList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang);
//        dbHelper2.deleteAllSanPham();
        map();
        arrayList = (ArrayList<GioHang>) dbHelper2.getSanPhamTheoUser(MainActivity.user);
        adapterGioHang = new AdapterGioHang(this,arrayList);
        lsvGioHang.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();

        btnTiepTucMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GioHangActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        tongTien.setText("Tổng tiền thanh toán: "+dbHelper2.tongTienGioHang(MainActivity.user)+"đ");

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper2.getUserCountGioHang()!=0){
                    Intent intent = new Intent(GioHangActivity.this,HoaDonActivity.class);
                    intent.putExtra("TongTien",String.valueOf(dbHelper2.tongTienGioHang(MainActivity.user)));
                    startActivity(intent);
                }else{
                    Toast.makeText(GioHangActivity.this, "Phải có ít nhất 1 sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVaoLichSuMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GioHangActivity.this,LichSuMuaHangActivity.class);
                startActivity(intent);
            }
        });
    }


    public void map(){
        lsvGioHang = findViewById(R.id.lsv_giohang);
        tongTien = findViewById(R.id.txt_TongTien);
        btnThanhToan = findViewById(R.id.btn_TienHanhThanhToan);
        btnTiepTucMuaHang = findViewById(R.id.btn_TiepTucMuaHang);
        btnVaoLichSuMuaHang = findViewById(R.id.btn_VaoLichSuMuaHang);
    }
}
