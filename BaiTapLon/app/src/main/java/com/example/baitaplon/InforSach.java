package com.example.baitaplon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InforSach extends AppCompatActivity {
    AdapterGioHang adapterGioHang;
    ArrayList<GioHang> arrayList = new ArrayList<>();
    DbHelper2 dbHelper2 = new DbHelper2(InforSach.this);
    TextView txtTenSPInfor,txtDonGiaSPInfor,txtThanhTien;
    ImageView imageViewAnhSPInfor;
    NumberPicker numberPicker;
    Button btnThemVaoGioHang,btnQuayLai;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor_item_lsv_sach);
        map();

        // Lấy dữ liệu
        Intent intent = getIntent();
        String TenAnhSP = intent.getStringExtra("KeyTenAnhSP");
        txtTenSPInfor.setText(intent.getStringExtra("KeyTenSP"));
        txtDonGiaSPInfor.setText("Đơn giá: "+intent.getStringExtra("KeyDonGia")+"đ");
        txtThanhTien.setText(intent.getStringExtra("KeyDonGia")+"đ");
        int imageResource = getIntent().getIntExtra("KeyAnhSP", 0);
        imageViewAnhSPInfor.setImageResource(imageResource);

        //numberPicker
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setValue(1);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int dongia = Integer.parseInt(intent.getStringExtra("KeyDonGia"));
                int thanhtien = dongia*newVal;
                txtThanhTien.setText(thanhtien+"đ");
            }
        });

        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = MainActivity.user;
                String anhSP = TenAnhSP;
                String tenSP = txtTenSPInfor.getText().toString();
                int dongiaSP = Integer.parseInt(intent.getStringExtra("KeyDonGia"));
                int soluongSP = numberPicker.getValue();
                GioHang gh = new GioHang(user,anhSP,tenSP,dongiaSP,soluongSP);
                if(dbHelper2.checkSP(user, tenSP)){
                    Toast.makeText(InforSach.this, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                }else{
                    dbHelper2.addSanPham(gh);
                    arrayList.clear();
                    arrayList = (ArrayList<GioHang>) dbHelper2.getSanPhamTheoUser(user);
                    adapterGioHang = new AdapterGioHang(InforSach.this,arrayList);
                    adapterGioHang.notifyDataSetChanged();
                    Toast.makeText(InforSach.this, "Đã thêm vào giỏ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Button quay lại
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void map(){
        txtTenSPInfor = findViewById(R.id.txt_TenSPInfor);
        txtDonGiaSPInfor = findViewById(R.id.txt_DonGiaSPInfor);
        imageViewAnhSPInfor = findViewById(R.id.imageView_AnhSPInfor);
        numberPicker = findViewById(R.id.numberPicker);
        btnThemVaoGioHang = findViewById(R.id.btn_ThemVaoGioHang);
        txtThanhTien = findViewById(R.id.txt_ThanhTien);
        btnQuayLai = findViewById(R.id.btn_Quaylai);
    }
}
