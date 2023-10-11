package Fragmnet;

import static java.time.ZonedDateTime.*;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.duanmauuu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapter.PhieuMuonAdapter;
import Adapter.SachAdapter;
import Adapter.SachFragmnetAdapter;
import Adapter.ThanhVienAdapter;
import Adapter.ThanhVienFragmentADapter;
import DAO.PhieuMuon_DAO;
import DAO.Sach_DAO;
import DAO.ThanhVien_DAO;
import DTO.PhieuMuon_DTO;
import DTO.Sach_DTO;
import DTO.ThanhVien_DTO;


public class PhieuMuonFragmnet extends Fragment {
    ListView lvpm;
    List<PhieuMuon_DTO>list;
    FloatingActionButton fbtn;
    PhieuMuon_DAO phieuMuon_dao;
    EditText edmapm;
    Spinner spTv,spS;
    TextView tvngay,tvgia;
    CheckBox chktrangthai;
    Button btnsavePm,btnhuyPM;
    PhieuMuonAdapter adapterPm;
    PhieuMuon_DTO pm;

    ThanhVien_DAO tvDao;
    List<ThanhVien_DTO>listTV;
    ThanhVienFragmentADapter adapterTV;

    Sach_DAO sDao;
    List<Sach_DTO>lisstS;
    SachFragmnetAdapter adapterS;

    int maTv,MaS,tienthue;
    int PossionTV,PosionS;
    SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.phieumuonfragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        lvpm=view.findViewById(R.id.lvPm);
        fbtn=view.findViewById(R.id.floatbtn);

        phieuMuon_dao=new PhieuMuon_DAO(getContext());
        capnhat();
        lvpm.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                pm=list.get(i);
                opendilog(getContext(),1);
                return false;
            }
        });
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendilog(getContext(),0);
            }
        });
    }
    private void opendilog(final Context context,final int type){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.itemsua_themphieumuon, null);
        builder.setView(v);
        Dialog dialog=builder.create();
        dialog.show();

        edmapm=v.findViewById(R.id.edmaPM);
        spS=v.findViewById(R.id.spS);
        spTv=v.findViewById(R.id.spTV);
        tvngay=v.findViewById(R.id.tvngayyy);
        tvgia=v.findViewById(R.id.tvtienthueee);
        chktrangthai=v.findViewById(R.id.chktrangthai);
        btnsavePm=v.findViewById(R.id.btnLuuPm);
        btnhuyPM=v.findViewById(R.id.btnhuypm);


        tvDao=new ThanhVien_DAO(getContext());
        listTV=new ArrayList<>();
        listTV=tvDao.getAll();
        adapterTV=new ThanhVienFragmentADapter(getContext(),listTV);
        spTv.setAdapter(adapterTV);


        spTv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maTv=listTV.get(i).maTV;
//                Toast.makeText(context, "Chọn  "+listTV.get(i).hoTen, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sDao =new Sach_DAO(getContext());
        lisstS=new ArrayList<>();
        lisstS=sDao.getAll();
        adapterS=new SachFragmnetAdapter(getContext(),lisstS);
        spS.setAdapter(adapterS);
        spS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MaS=lisstS.get(i).maSach;
                tienthue=lisstS.get(i).giaThue;
//                Toast.makeText(context, "Chọn "+lisstS.get(i).tenSach , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        edmapm.setEnabled(false);
        if (type!=0) {
            edmapm.setText(pm.maPM + "");
            for (int i = 0; i < listTV.size(); i++) {
                if (pm.maTV == listTV.get(i).maTV) {
                    PossionTV = i;
                }
            }
            spTv.setSelection(PossionTV);
            for (int i = 0; i < lisstS.size(); i++) {
                if (pm.maSach == lisstS.get(i).maSach) {
                    PosionS = i;
                }
            }
            spS.setSelection(PosionS);

            tvngay.setText("Ngày Thuê: " + dateFormat.format(pm.ngay));
            tvgia.setText("Tiền Thuê: " + pm.tienThue);
            if (pm.traSach == 1) {
                chktrangthai.setChecked(true);

            } else {
                chktrangthai.setChecked(false);
            }
        }else{
            chktrangthai.setEnabled(false);
        }
        btnhuyPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnsavePm.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                pm=new PhieuMuon_DTO();
                pm.maSach=MaS;
                pm.maTV=maTv;

                Calendar calendar = Calendar.getInstance();
                java.sql.Date currentDate = new java.sql.Date(calendar.getTimeInMillis());

                pm.ngay= currentDate;
                pm.tienThue=tienthue;
                if (chktrangthai.isChecked()){
                    pm.traSach=1;
                }else{
                    pm.traSach=0;
                }
                if (vadidate()>0){
                    if (type==0){
                        if (phieuMuon_dao.intsertPHieuMuon(pm)>0){
                            Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        pm.maPM= Integer.parseInt(edmapm.getText().toString());
                        if (phieuMuon_dao.updatePHieuMuon(pm)>0){
                            Toast.makeText(context, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Câp nhật Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                capnhat();
                dialog.dismiss();
            }

        });
        dialog.show();


    }
    void capnhat(){
        list=phieuMuon_dao.getAll();
        adapterPm=new PhieuMuonAdapter(getContext(),list);
        lvpm.setAdapter(adapterPm);

    }
    public int vadidate(){
        int check=1;

        return  check;
    }


}
