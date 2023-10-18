package com.example.baitaplon;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    static String User;
    DbHelper dbHelper = new DbHelper(Login.this);
    EditText txt_User,txt_Pass;
    ArrayList<User> arrayList = new ArrayList<>();
    Button btnLogin,btnLayoutSignup;
    ImageView showPasswordIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        map();
//        dbHelper.deleteAllUser();
        dbHelper.createDefaultUserIfNeed();
        arrayList = (ArrayList<User>) dbHelper.getAllUser();

        showPasswordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isPasswordVisible = txt_Pass.getTransformationMethod() instanceof HideReturnsTransformationMethod;
                txt_Pass.setTransformationMethod(isPasswordVisible ? PasswordTransformationMethod.getInstance() : HideReturnsTransformationMethod.getInstance());
                showPasswordIcon.setBackgroundResource(isPasswordVisible ? R.drawable.img_1 : R.drawable.img);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = txt_User.getText().toString();
                String pass = txt_Pass.getText().toString();
                if(dbHelper.checkAccountLogin(user,pass)){
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    User = user;
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this, "Không tìm thấy tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLayoutSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });
    }
    public void map(){
        txt_User = findViewById(R.id.txt_User);
        txt_Pass = findViewById(R.id.txt_Pass);
        showPasswordIcon = findViewById(R.id.show_password_icon);
        btnLogin = findViewById(R.id.btn_Login);
        btnLayoutSignup = findViewById(R.id.btn_LayoutSignUp);
    }
}
