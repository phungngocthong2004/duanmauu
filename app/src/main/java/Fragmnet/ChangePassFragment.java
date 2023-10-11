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

public class ChangePassFragment extends Fragment {
    EditText edpassold,edpassNew,ednhaplaiPass;
    Button btnSave,btnHuy;
    ThuThuDAO daoTT;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.changepassfragment,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edpassold=view.findViewById(R.id.edPassOld);
        edpassNew=view.findViewById(R.id.edPassNew);
        ednhaplaiPass=view.findViewById(R.id.edRePassNew);
        btnSave=view.findViewById(R.id.btnSave);
        btnHuy=view.findViewById(R.id.btnHuy);

        daoTT=new ThuThuDAO(getActivity());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ednhaplaiPass.setText("");
                edpassold.setText("");
                edpassNew.setText("");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                 String user=pref.getString("USERNAME","");
           if (vadidate()>0){
               ThuThu_DTO tt=daoTT.getheoId(user);
               tt.matKhau=edpassNew.getText().toString();
               if (daoTT.updateTT(tt)>0){
                   Toast.makeText(getContext(), "Thay đồi Mật khẩu Thành công", Toast.LENGTH_SHORT).show();
                   ednhaplaiPass.setText("");
                   edpassold.setText("");
                   edpassNew.setText("");
               }else{
                   Toast.makeText(getContext(), "thay đồi Mật khẩu Thất bai", Toast.LENGTH_SHORT).show();
               }
           }
            }
        });

    }

    int  vadidate(){
        int check=1;
        String mkcu=edpassold.getText().toString();
        String mkmoi=edpassNew.getText().toString();
        String nhaplaimkmoi=ednhaplaiPass.getText().toString();
        if(mkcu.isEmpty()|mkmoi.isEmpty()|nhaplaimkmoi.isEmpty()){
            Toast.makeText(getContext(), "bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }else{
           //doc user ,pas trong share...
           SharedPreferences pref=getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE) ;
            String passOld=pref.getString("PASSWORD","");

            String pass=edpassNew.getText().toString();
            String repass=ednhaplaiPass.getText().toString();
            if (!passOld.equals(mkcu)){
                Toast.makeText(getContext(), "Mật Khẩu Cũ sai", Toast.LENGTH_SHORT).show();
                check=-1;
            }
            if (!pass.equals(repass)) {
                Toast.makeText(getContext(), "Mật Khẩu Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }
}
