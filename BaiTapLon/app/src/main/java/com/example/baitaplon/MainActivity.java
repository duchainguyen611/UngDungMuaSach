package com.example.baitaplon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static String user;
    ImageView vaoGioHang;
    EditText edtTimKiem;
    TextView txtNameUser;
    Spinner spinner;
    ListView lsvSach;
    Button btnDangXuat,btnTimKiem;
    AdapterSach adapterSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map();

        //Spinner và hiển thị trên listview
        List<Sach> image_details = getListData();
        String[] values = {"TẤT CẢ","TIỂU THUYẾT","LIGHT NOVEL","MANGA - COMIC","KỸ NĂNG SỐNG","TÂM LÝ","KINH TẾ"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = findViewById(R.id.spinner_LoaiSP);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    adapterSach = new AdapterSach(MainActivity.this,image_details);
                    lsvSach.setAdapter(adapterSach);
                }else{
                    ArrayList<Sach> typeOneProducts = new ArrayList<>();
                    for (Sach s : image_details) {
                        if (Integer.valueOf(s.getLoaisp()) == position) {
                            typeOneProducts.add(s);
                        }
                    }
                    adapterSach = new AdapterSach(MainActivity.this,typeOneProducts);
                    lsvSach.setAdapter(adapterSach);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        //Hiển thị tên người dùng
        txtNameUser.setText(Login.User);
        user = txtNameUser.getText().toString();

        //Button Tìm kiếm
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = edtTimKiem.getText().toString();
                ArrayList<Sach> searchProduct = new ArrayList<>();
                for (Sach s : image_details) {
                    if (s.getTen().toLowerCase().contains(search.toLowerCase())) {
                        searchProduct.add(s);
                    }
                }
                adapterSach = new AdapterSach(MainActivity.this,searchProduct);
                lsvSach.setAdapter(adapterSach);
            }
        });

        //Button đăng xuất
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
//                finish();
            }
        });

        //Image vào giỏ hàng
        vaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,GioHangActivity.class);
                startActivity(intent1);
            }
        });
    }
    public void map(){
        lsvSach = findViewById(R.id.lsv_sach);
        btnDangXuat = findViewById(R.id.btn_DangXuat);
        txtNameUser = findViewById(R.id.txt_nameUser);
        btnTimKiem = findViewById(R.id.btn_Search);
        edtTimKiem = findViewById(R.id.txt_Search);
        vaoGioHang = findViewById(R.id.img_vaoGioHang);
    }

    private List<Sach> getListData() {
        List<Sach> list = new ArrayList<Sach>();
        Sach s1 = new Sach("1","tt1","Cây Cam Ngọt Của Tôi",108000);
        Sach s2 = new Sach("1","tt2","Tôi Thấy Hoa Vàng Trên Cỏ Xanh (Bản In Mới - 2018)",125000);
        Sach s3 = new Sach("1","tt3","Nhà Giả Kim (Tái Bản 2020)",79000);
        Sach s4 = new Sach("1","tt4","Cho Tôi Xin Một Vé Đi Tuổi Thơ (Bìa Mềm) (Tái Bản 2018)",80000);
        Sach s5 = new Sach("2","ln1","Vợ Trong Game Của Tôi Là Idol Nổi Tiếng Ngoài Đời - Tập 2",126000);
        Sach s6 = new Sach("2","ln2","Thiên Sứ Nhà Bên - Tập 5 - Tặng Kèm Bookmark",95000);
        Sach s7 = new Sach("2","ln3","Vợ Trong Game Của Tôi Là Idol Nổi Tiếng Ngoài Đời - Tập 1",126000);
        Sach s8 = new Sach("2","ln4","Nhắn Gửi Tất Cả Các Em, Những Người Tôi Đã Yêu",128000);
        Sach s9 = new Sach("3","mg1","Fire Force - Tập 2 ",43000);
        Sach s10 = new Sach("3","mg2","Kaguya-Sama: Cuộc Chiến Tỏ Tình - Tập 14 ",40000);
        Sach s11 = new Sach("3","mg3","Chainsaw Man - Tập 9 - Tặng Kèm Lót Ly",45000);
        Sach s12 = new Sach("3","mg4","Komi - Nữ Thần Sợ Giao Tiếp - Tập 20",22500);
        Sach s13 = new Sach("4","kns1","Thay Đổi Cuộc Sống Với Nhân Số Học",248000);
        Sach s14 = new Sach("4","kns2","Hiểu Về Trái Tim (Tái Bản 2023)",158000);
        Sach s15 = new Sach("4","kns3","Hành Tinh Của Một Kẻ Nghĩ Nhiều",86000);
        Sach s16 = new Sach("4","kns4","Cùng Bạn Trưởng Thành",89000);
        Sach s17 = new Sach("5","tl1","Tâm Lý Học Tội Phạm - Phác Họa Chân Dung Kẻ Phạm Tội",145000);
        Sach s18 = new Sach("5","tl2","Thiên Tài Bên Trái, Kẻ Điên Bên Phải (Tái Bản 2021)",179000);
        Sach s19 = new Sach("5","tl3","Thuật Thao Túng - Góc Tối Ẩn Sau Mỗi Câu Nói",139000);
        Sach s20 = new Sach("5","tl4","Định Luật Murphy - Mọi Bí Mật Tâm Lý Thao Túng Cuộc Đời Bạn",119000);
        Sach s21 = new Sach("6","kt1","Từ Quên Đến Ám Ảnh",75000);
        Sach s22 = new Sach("6","kt2","Chiến Lược Gọi Vốn Cộng Đồng",99000);
        Sach s23 = new Sach("6","kt3","Bí Quyết Kinh Doanh Của Người Do Thái (Tái Bản 2022)",98000);
        Sach s24 = new Sach("6","kt4","9 Bí Quyết Thành Công Của Triệu Phú",45000);

        list.add(s1);list.add(s2);list.add(s3);list.add(s4);
        list.add(s5);list.add(s6);list.add(s7);list.add(s8);
        list.add(s9);list.add(s10);list.add(s11);list.add(s12);
        list.add(s13);list.add(s14);list.add(s15);list.add(s16);
        list.add(s17);list.add(s18);list.add(s19);list.add(s20);
        list.add(s21);list.add(s22);list.add(s23);list.add(s24);
        return list;
    }
}