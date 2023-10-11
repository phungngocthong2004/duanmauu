package Fragmnet;

import android.app.Dialog;
import android.content.Context;
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
import androidx.fragment.app.Fragment;

import com.example.duanmauuu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import Adapter.LoaiSachAdapter;
import DAO.LoaiSach_DAO;
import DTO.LoaiSach_DTO;
import DTO.ThanhVien_DTO;

public class LoaiSachFragmnet extends Fragment {
    ListView lvls;
    FloatingActionButton fbtn;
    LoaiSach_DAO sachDao;
    LoaiSachAdapter loaiSachAdapter;
    List<LoaiSach_DTO>list;
    LoaiSach_DTO  sach_dto;
    Dialog dialog;

    EditText edmaS,edtenS;
    Button btnsave,btnhuy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loaisachfragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvls=view.findViewById(R.id.lvloaisach);
        fbtn=view.findViewById(R.id.floatbtn);

        sachDao=new LoaiSach_DAO(getContext());

        capNhapLs();

        lvls.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                sach_dto=list.get(i);
        opendialog(getContext(),1);

                return false;
            }
        });
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            opendialog(getContext(),0);
            }
        });

    }
    private  void opendialog(final Context context,final  int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.itemthem_sua_ls);
        edmaS=dialog.findViewById(R.id.edidLs);
        edtenS=dialog.findViewById(R.id.edtenLs);

        btnsave=dialog.findViewById(R.id.btnSaveLs);
        btnhuy=dialog.findViewById(R.id.btnHuyLs);

        edmaS.setEnabled(false);
        //0  là  thêm còn 1 là sua
        if (type!=0){
            edmaS.setText(sach_dto.getMaLoai()+"");
            edtenS.setText(sach_dto.getTenLoai());

        }
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sach_dto =new LoaiSach_DTO();
                sach_dto.tenLoai=edtenS.getText().toString();

                if (validate()>0){
                    if (type==0){
                        if (sachDao.intsertLoaiS(sach_dto)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        sach_dto.maLoai= Integer.parseInt(edmaS.getText().toString());
                        if (sachDao.updateLoaiS(sach_dto)>0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Sửa  Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                capNhapLs();
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
    void capNhapLs(){
        list=sachDao.getAll();
        loaiSachAdapter=new LoaiSachAdapter(getContext(),list);
        lvls.setAdapter(loaiSachAdapter);
    }
    public  int validate(){
        int check=1;

        String ten=edtenS.getText().toString();
        if (ten.isEmpty()){
            Toast.makeText(getContext(), "Bạn phải Nhâpp đầy đủ", Toast.LENGTH_SHORT).show();
            return check =-1;
        }
        return check=1;
    }
}
