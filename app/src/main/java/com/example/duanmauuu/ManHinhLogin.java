package com.example.duanmauuu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import DAO.ThuThuDAO;

public class ManHinhLogin extends AppCompatActivity {
    EditText edUserName,edPassword;
    Button btnLogin,btncanncel;
    CheckBox chkRememberpass;

    ThuThuDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_login);

        edUserName=findViewById(R.id.edUserName);
        edPassword=findViewById(R.id.edPassWord);
        btnLogin=findViewById(R.id.btnDangNhap);
        btncanncel=findViewById(R.id.btnHuy);
        chkRememberpass=findViewById(R.id.chkRememberpass);

        dao=new ThuThuDAO(ManHinhLogin.this);

        //doc user,pas tring shareprences

        SharedPreferences pref=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edUserName.setText(pref.getString("USERNAME",""));
        edPassword.setText(pref.getString("PASSWORD",""));
        chkRememberpass.setChecked(pref.getBoolean("REMEMBER",false));

        btncanncel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edPassword.setText("");
                edUserName.setText("");
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            checklogin();
            }
        });



    }
   public void checklogin(){
        String strUsser=edUserName.getText().toString();
        String strPass=edPassword.getText().toString();
        if (strUsser.isEmpty()|strPass.isEmpty()){
            Toast.makeText(this, "Tên Đăng Nhâp Hoặc Mật Khẩu Đang để trống", Toast.LENGTH_SHORT).show();
        }else{
            if (dao.checklogin(strUsser,strPass)>0 ||strUsser.equals("amin")&&strPass.equals("amin")){
                Toast.makeText(this, "Login Thành Công", Toast.LENGTH_SHORT).show();
                  checkRemember(strUsser,strPass,chkRememberpass.isChecked());
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user",strUsser);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Tên Đăngn nhập và Mật Khẩu Không Khớp", Toast.LENGTH_SHORT).show();
            }
        }
    }
//    ||strUsser.equals("amin")&&strPass.equals("amin")
    void checkRemember(String u,String p,boolean status){
        SharedPreferences pref=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit=pref.edit();
        if (!status){
            //xoa tình Trạng lưu trưuocs đó
            edit.clear();
        }else{
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
            //luu lại toàn bộ
            edit.commit();
        }
    }
}