package com.example.baitaplon;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Signup extends AppCompatActivity {
    ArrayList<User> arrayList = new ArrayList<>();
    DbHelper dbHelper = new DbHelper(Signup.this);
    Button btnSignup,btnBack;
    EditText edtSignupUN,edtSignupPW,edtXacNhanSignupPW;

    ImageView showPW,showXacThucPW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        map();

        showPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isPasswordVisible = edtSignupPW.getTransformationMethod() instanceof HideReturnsTransformationMethod;
                edtSignupPW.setTransformationMethod(isPasswordVisible ? PasswordTransformationMethod.getInstance() : HideReturnsTransformationMethod.getInstance());
                showPW.setBackgroundResource(isPasswordVisible ? R.drawable.img_1 : R.drawable.img);
            }
        });

        showXacThucPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isPasswordVisible = edtXacNhanSignupPW.getTransformationMethod() instanceof HideReturnsTransformationMethod;
                edtXacNhanSignupPW.setTransformationMethod(isPasswordVisible ? PasswordTransformationMethod.getInstance() : HideReturnsTransformationMethod.getInstance());
                showXacThucPW.setBackgroundResource(isPasswordVisible ? R.drawable.img_1 : R.drawable.img);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtSignupUN.length() != 0 && edtSignupPW.length() != 0){
                    String user = edtSignupUN.getText().toString();
                    String pass = edtSignupPW.getText().toString();
                    String XacNhanpass = edtXacNhanSignupPW.getText().toString();
                    if(dbHelper.checkUser(user) == true){
                        Toast.makeText(Signup.this, "Tên đăng nhập đã có người sử dụng", Toast.LENGTH_SHORT).show();
                    }else if(pass.equals(XacNhanpass) == false) {
                        Toast.makeText(Signup.this, "Mật khẩu và xác nhận mật khẩu không giống nhau", Toast.LENGTH_SHORT).show();
                    }else {
                        dbHelper.addUser(new User(user,pass));
                        arrayList = (ArrayList<User>) dbHelper.getAllUser();
                        Toast.makeText(Signup.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Signup.this, "Phải điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
//                finish();
            }
        });
    }

    public void map(){
        btnBack = findViewById(R.id.btn_Back);
        btnSignup = findViewById(R.id.btn_SignUp);
        edtSignupUN = findViewById(R.id.edt_SignupUN);
        edtSignupPW = findViewById(R.id.edt_SignupPW);
        edtXacNhanSignupPW = findViewById(R.id.edt_SignupXacNhanPW);
        showPW = findViewById(R.id.show_passwordSU_icon1);
        showXacThucPW = findViewById(R.id.show_passwordSU_icon2);
    }
}
