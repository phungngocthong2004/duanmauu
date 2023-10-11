package com.example.duanmauuu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import DAO.ThuThuDAO;
import DTO.ThuThu_DTO;
import Fragmnet.AddUserFragment;
import Fragmnet.ChangePassFragment;
import Fragmnet.DoanhThuFragmnet;
import Fragmnet.LoaiSachFragmnet;
import Fragmnet.PhieuMuonFragmnet;
import Fragmnet.SachFragment;
import Fragmnet.ThanhVienFragmennt;
import Fragmnet.Top10Fragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mydrawer;
    View myheader;
    Toolbar toolbar;
    TextView tvuseer;
    NavigationView naviView;

    ThuThuDAO ttDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quản Lý Phiếu Mượn");

        mydrawer=findViewById(R.id.MyDrawer);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,mydrawer,toolbar,R.string.open,R.string.close);
        mydrawer.addDrawerListener(toggle);
        toggle.syncState();
        PhieuMuonFragmnet phieuMuonFragmnet=new PhieuMuonFragmnet();
        ThanhVienFragmennt thanhVienFragmennt=new ThanhVienFragmennt();
        LoaiSachFragmnet loaiSachFragmnet=new LoaiSachFragmnet();
        SachFragment sachFragment=new SachFragment();
        AddUserFragment addUserFragment=new AddUserFragment();
        ChangePassFragment changePassFragment=new ChangePassFragment();
        Top10Fragment top10Fragment=new Top10Fragment();
        DoanhThuFragmnet doanhThuFragmnet=new DoanhThuFragmnet();
        getSupportFragmentManager().beginTransaction().add(R.id.content_fream,phieuMuonFragmnet).commit();

        naviView=findViewById(R.id.nvView);
        //show user header;
         myheader=naviView.getHeaderView(0);
         tvuseer=myheader.findViewById(R.id.tv_user);
         Intent intent=getIntent();
         String user=intent.getStringExtra("user");
//         ttDao=new ThuThuDAO(this);
//          ThuThu_DTO thuThu_dto=ttDao.getheoId(user);
//          String username=thuThu_dto.hoTen;
         tvuseer.setText("Wellcome "+user+"!");

         if (user.equalsIgnoreCase("amin")){
             naviView.getMenu().findItem(R.id.nav_addUser).setVisible(true);
         }

        //xu lí trnag thaiis kick
        naviView.getMenu().findItem(R.id.nav_phieumuon).setChecked(true);

         naviView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 int id=item.getItemId();
                 if (id==R.id.nav_phieumuon){
                     getSupportFragmentManager().beginTransaction().replace(R.id.content_fream,phieuMuonFragmnet).commit();
                     getSupportActionBar().setTitle("Quản Lý Phiếu Mượn");
                 }else if(id==R.id.nav_thanhVien){
                     getSupportFragmentManager().beginTransaction().replace(R.id.content_fream, thanhVienFragmennt).commit();
                     getSupportActionBar().setTitle("Quản Lý Thành Viên");
                 }else if(id==R.id.nav_Loaisach){
                     getSupportFragmentManager().beginTransaction().replace(R.id.content_fream, loaiSachFragmnet).commit();
                     getSupportActionBar().setTitle("Quản Lý Loại Sách");
                 }else if(id==R.id.nav_Sach){
                     getSupportFragmentManager().beginTransaction().replace(R.id.content_fream, sachFragment).commit();
                     getSupportActionBar().setTitle("Quản Lý Sách");
                 }else if(id==R.id.nav_addUser){
                     getSupportFragmentManager().beginTransaction().replace(R.id.content_fream, addUserFragment).commit();
                     getSupportActionBar().setTitle(" Thêm Thủ Thư");
                 }else if(id==R.id.nav_changePass){
                     getSupportFragmentManager().beginTransaction().replace(R.id.content_fream, changePassFragment).commit();
                     getSupportActionBar().setTitle("Đổi Mật Khẩu");
                 } else if (id==R.id.nav_icontop) {
                     getSupportFragmentManager().beginTransaction().replace(R.id.content_fream,top10Fragment ).commit();
                     getSupportActionBar().setTitle("Top Sách Đươc mượn Nhiều");
                 }else if (id==R.id.nav_doanhThu){
                     getSupportFragmentManager().beginTransaction().replace(R.id.content_fream,doanhThuFragmnet ).commit();
                     getSupportActionBar().setTitle("Doanh Thu");
                 }else{
                     AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                     builder.setTitle("Singout");
                     builder.setMessage("Bạn có muốn đăng xuất không??");
                     builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             startActivity(new Intent(MainActivity.this, ManHinhLogin.class));
                             finish();
                         }
                     });
                     builder.setNegativeButton("No",null);
                     builder.show();
                 }


                 mydrawer.close();
                 return true;
             }
         });

    }

    @Override
    public void onBackPressed() {
        if (mydrawer.isOpen()){
            mydrawer.close();
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

      if (id==R.id.nav_phieumuon){
          mydrawer.openDrawer(GravityCompat.START);
      }
        return super.onOptionsItemSelected(item);
    }
    void dangxuat(){

    }
}