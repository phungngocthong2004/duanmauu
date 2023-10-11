package Fragmnet;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.duanmauuu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Adapter.LoaiSachAdapter;
import Adapter.SachAdapter;
import DAO.LoaiSach_DAO;
import DAO.Sach_DAO;
import DTO.LoaiSach_DTO;
import DTO.Sach_DTO;

public class SachFragment extends Fragment {
    ListView lvs;
    FloatingActionButton fbtn;
    Sach_DAO sachDao;
    SachAdapter sachAdapter;
    Sach_DTO sach;
    List<Sach_DTO>list;
    EditText edmaS,edtenS,edGiathue;
    Spinner spLoai;
    Button btnsaveS,btnHuyS;

    LoaiSach_DAO lsDao;
    List<LoaiSach_DTO>listL=new ArrayList<>();
    LoaiSachAdapter lsAdapter;
    int maLoai,posion;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sachfragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvs=view.findViewById(R.id.lvsach);
        fbtn=view.findViewById(R.id.floatbtn);

        sachDao=new Sach_DAO(getContext());
        CapnhatSach();

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendilog(getContext(),0);
            }
        });
        lvs.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                sach = list.get(i);
                opendilog(getContext(),1);
                return false;
            }
        });
    }
    private void opendilog(final Context context, final  int type) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.itemsua_themsach, null);
        builder.setView(v);
        Dialog dialog=builder.create();
        dialog.show();
        edtenS = v.findViewById(R.id.edtenS);
        edmaS = v.findViewById(R.id.edmaS);
        edGiathue = v.findViewById(R.id.edgiathue);
        spLoai = v.findViewById(R.id.spS);
        btnsaveS = v.findViewById(R.id.btnSaveS);
        btnHuyS = v.findViewById(R.id.btnHuyS);

        lsDao = new LoaiSach_DAO(getContext());
        listL = lsDao.getAll();
        lsAdapter = new LoaiSachAdapter(getContext(), listL);
        spLoai.setAdapter(lsAdapter);

        spLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLoai = listL.get(i).maLoai;
                Toast.makeText(context, "chọn " + listL.get(i).tenLoai, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edmaS.setEnabled(false);
        if (type != 0) {
            edtenS.setText(sach.tenSach);
            edGiathue.setText(sach.giaThue + "");
            edmaS.setText(sach.maSach + "");
            for (int i = 0; i < listL.size(); i++) {
                if (sach.maLoai == listL.get(i).maLoai) {
                    posion=i;
                }
            }
            spLoai.setSelection(posion);


        }
        btnHuyS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnsaveS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sach=new Sach_DTO();
                sach.tenSach=edtenS.getText().toString();
                sach.giaThue= Integer.parseInt(edGiathue.getText().toString());
                sach.maLoai=maLoai;
                if (vadidate()>0) {
                    if (type==0) {
                        if (sachDao.intsertSach(sach)>0) {
                            Toast.makeText(context, "thêm Thành Công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        sach.maSach= Integer.parseInt(edmaS.getText().toString());
                        if (sachDao.updateSach(sach)>0) {
                            Toast.makeText(context, "sua Thành Công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "sua Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                CapnhatSach();
                dialog.dismiss();

            }
        });
    }

    void CapnhatSach(){
        list=sachDao.getAll();
        sachAdapter=new SachAdapter(getContext(),list);
        lvs.setAdapter(sachAdapter);

    }
    public  int vadidate(){
        int check=1;
        String tenS=edtenS.getText().toString();

        if (tenS.isEmpty()||edGiathue.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải Nhâpp đầy đủ", Toast.LENGTH_SHORT).show();
            check =-1;
        }
        int giathue= Integer.parseInt(edGiathue.getText().toString());
        if (giathue<0) {
            Toast.makeText(getContext(), "giá thuê phải Lớn Hơn 0", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check=1;
    }
    }


