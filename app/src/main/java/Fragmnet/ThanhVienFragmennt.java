package Fragmnet;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.duanmauuu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import Adapter.ThanhVienAdapter;
import DAO.ThanhVien_DAO;
import DTO.ThanhVien_DTO;

public class ThanhVienFragmennt extends Fragment {
    ListView lvtv;
    List<ThanhVien_DTO>list;
    FloatingActionButton Fb;
    ThanhVienAdapter adaptertv;
    ThanhVien_DAO thanhVienDao;
    Dialog dialog;
    EditText edidtv,edten, ednamsinh,edcccd;
    Button btnSaveTv, btnhuy;

    ThanhVien_DTO thanhVienDto;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.thanhvienfragmnet,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvtv=view.findViewById(R.id.lvthanhVien);
        Fb=view.findViewById(R.id.floatbtn);
        thanhVienDao=new ThanhVien_DAO(getContext());
        capnhatTV();

        lvtv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                thanhVienDto=list.get(i);
                openDialog(getContext(),1);
                return false;
            }
        });
        Fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openDialog(getContext(),0);
            }
        });

    }
    protected  void openDialog(final Context context,final  int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.iteamthemsuathanhvien);
           edidtv=dialog.findViewById(R.id.edidthanhvien);
           edten=dialog.findViewById(R.id.edtenThanhVien);
             ednamsinh=dialog.findViewById(R.id.ednamsinh);
          edcccd=dialog.findViewById(R.id.edcccd);
          btnSaveTv=dialog.findViewById(R.id.btnSaveTV);
         btnhuy=dialog.findViewById(R.id.btnHuyTV);

        edidtv.setEnabled(false);
        //0  là  thêm còn !=0 là sua
        if (type!=0){
            edidtv.setText(thanhVienDto.maTV+"");
            edten.setText(thanhVienDto.hoTen);
            ednamsinh.setText(thanhVienDto.namSinh);
            edcccd.setText(thanhVienDto.cccd);
        }
        btnSaveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thanhVienDto=new ThanhVien_DTO();
                thanhVienDto.hoTen=edten.getText().toString();
                thanhVienDto.namSinh=ednamsinh.getText().toString();
                thanhVienDto.cccd=edcccd.getText().toString();
                if (validate()>0){
                    if (type==0){
                        if (thanhVienDao.intsertTV(thanhVienDto)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        thanhVienDto.maTV= Integer.parseInt(edidtv.getText().toString());
                        if (thanhVienDao.updateTV(thanhVienDto)>0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Sửa  Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                capnhatTV();
                dialog.dismiss();
            }
        });
        dialog.show();
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
//    public  void xoa(final String  Id){
//        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
//        builder.setTitle("Xóa");
//        builder.setMessage("Bạn có muốn xóa Không");
//        builder.setCancelable(true);
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                      thanhVienDao.DeleteID(Id);
//                    Toast.makeText(getContext(), "xoa Thành Công Thành Viên", Toast.LENGTH_SHORT).show();
//                    capnhatTV();
//                    dialogInterface.cancel();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialog.cancel();
//            }
//        });
//        builder.create();
//        builder.show();
//    }
    void capnhatTV(){

        list=thanhVienDao.getAll();
        adaptertv=new ThanhVienAdapter(getContext(),list);
        lvtv.setAdapter(adaptertv);
    }
    public  int validate(){
        int check=1;
        String ten=edten.getText().toString();
        String namsinh=ednamsinh.getText().toString();
        if (ten.isEmpty()||namsinh.isEmpty()||edcccd.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Bạn phải Nhâpp đầy đủ", Toast.LENGTH_SHORT).show();
             check =-1;
        }
        return check;
    }
}
