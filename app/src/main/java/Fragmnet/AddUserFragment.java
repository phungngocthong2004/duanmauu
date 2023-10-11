package Fragmnet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmauuu.R;

import DAO.ThuThuDAO;
import DTO.ThuThu_DTO;

public class AddUserFragment  extends Fragment {
    EditText edTenDangNhap,edhoten,edpass,edRepass;
    Button btnluu,btnhuy;
    ThuThuDAO daott;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.adduserfragmnet,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         edTenDangNhap=view.findViewById(R.id.edtendangnhap);
        edhoten=view.findViewById(R.id.edhoten);
        edpass=view.findViewById(R.id.edpass);
        edRepass=view.findViewById(R.id.edRepass);
        btnhuy=view.findViewById(R.id.btnHuy);
        btnluu=view.findViewById(R.id.btnSave);

        daott=new ThuThuDAO(getContext());

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edTenDangNhap.setText("");
                edhoten.setText("");
                edpass.setText("");
                edRepass.setText("");
            }
        });

        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user=pref.getString("USERNAME","");

                    ThuThu_DTO tt=new ThuThu_DTO();
                    tt.maTT=edTenDangNhap.getText().toString();
                    tt.hoTen=edhoten.getText().toString();
                    tt.matKhau=edpass.getText().toString();
                    if (vadidate()>0){
                        if (daott.intsertTT(tt)>0) {
                            Toast.makeText(getContext(), "Thên thành công", Toast.LENGTH_SHORT).show();
                            edpass.setText("");
                            edhoten.setText("");
                            edRepass.setText("");
                            edTenDangNhap.setText("");
                        }else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }

            }
        });

    }

    int  vadidate(){
        int check=1;
        String user=edTenDangNhap.getText().toString();
        String hten=edhoten.getText().toString();
        String pas=edpass.getText().toString();
        String repass=edRepass.getText().toString();
        if(user.isEmpty()|hten.isEmpty()|pas.isEmpty()|repass.isEmpty()){
            Toast.makeText(getContext(), "bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        check=-1;
        }else{
            if (!pas.equals(repass)) {
                Toast.makeText(getContext(), "Mật Khẩu Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }

    }

