package com.example.baitaplon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HoaDonActivity extends AppCompatActivity {
    EditText edtHoTen,edtSDT,edtDiaChi;
    ListView lsvHoaDon;
    TextView tvTongTienHang,tvTienShip,tvTongThanhToan;
    Button btnQuayvegiohang,btnDatHang;
    AdapterHoaDon adapterHoaDon;
    ArrayList<GioHang> arrayList = new ArrayList<>();
    DbHelper2 dbHelper2 = new DbHelper2(HoaDonActivity.this);
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoadon);
        map();
        arrayList = (ArrayList<GioHang>) dbHelper2.getSanPhamTheoUser(MainActivity.user);
        adapterHoaDon = new AdapterHoaDon(this,arrayList);
        lsvHoaDon.setAdapter(adapterHoaDon);
        adapterHoaDon.notifyDataSetChanged();

        btnQuayvegiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        int tongtienhang = Integer.parseInt(intent.getStringExtra("TongTien"));
        int tienship;
        tvTongTienHang.setText(intent.getStringExtra("TongTien")+"đ");
        if(tongtienhang > 300000) tienship = 0;
        else if(tongtienhang > 200000) tienship = 10000;
        else if(tongtienhang > 100000) tienship = 20000;
        else tienship = 30000;
        tvTienShip.setText(tienship+"đ");
        int tongthanhtoan = tongtienhang + tienship;
        tvTongThanhToan.setText(tongthanhtoan+"đ");

        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtHoTen.length() != 0 && edtSDT.length() != 0 && edtDiaChi.length() != 0){
                    Dialog dialogFragment = new Dialog(HoaDonActivity.this,dbHelper2);
                    dialogFragment.show(getFragmentManager(), "");
                }else{
                    Toast.makeText(HoaDonActivity.this, "Thông tin người mua không đủ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void map(){
        edtHoTen = findViewById(R.id.edt_HoTen);
        edtSDT = findViewById(R.id.edt_SDT);
        edtDiaChi = findViewById(R.id.edt_DiaChi);
        lsvHoaDon = findViewById(R.id.lsvHoaDon);
        tvTongTienHang = findViewById(R.id.tv_tongtienhang);
        tvTienShip = findViewById(R.id.tv_tienship);
        tvTongThanhToan = findViewById(R.id.tv_tongthanhtoan);
        btnQuayvegiohang = findViewById(R.id.btn_Quayvegiohang);
        btnDatHang = findViewById(R.id.btn_DatHang);
    }
}
