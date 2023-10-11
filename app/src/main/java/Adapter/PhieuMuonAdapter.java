package Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.duanmauuu.R;

import java.text.SimpleDateFormat;
import java.util.List;

import DAO.PhieuMuon_DAO;
import DAO.Sach_DAO;
import DAO.ThanhVien_DAO;
import DTO.PhieuMuon_DTO;
import DTO.Sach_DTO;
import DTO.ThanhVien_DTO;

public class PhieuMuonAdapter extends BaseAdapter {
    private Context context;
    private List<PhieuMuon_DTO>list;
     private PhieuMuon_DAO  phieuMuon_dao;
     PhieuMuon_DTO phieuMuon_dto;


     SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");


    public PhieuMuonAdapter(Context context, List<PhieuMuon_DTO> list) {
        this.context = context;
        this.list = list;
        phieuMuon_dao=new PhieuMuon_DAO(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v= inflater.inflate(R.layout.dongphieumuon,null);
        TextView tvid=v.findViewById(R.id.tvPm);
        TextView tvtenTV=v.findViewById(R.id.tvtentV);
        TextView tvTenS=v.findViewById(R.id.tvTensach);
        TextView tvtienthue=v.findViewById(R.id.tvgiathue);
        TextView tvtrangthai=v.findViewById(R.id.tvtrangthai);
        TextView tvngaythue=v.findViewById(R.id.tvngaythue);
        ImageView img=v.findViewById(R.id.imgxoasPM);
         phieuMuon_dto =list.get(i);

        Sach_DAO sachDAo=new Sach_DAO(context);

        Sach_DTO  sach_dto=sachDAo.getheoId(((String.valueOf(phieuMuon_dto.maSach))));
        ThanhVien_DAO thahVienDao=new ThanhVien_DAO(context);
        ThanhVien_DTO thanhVienDto=thahVienDao.getID(String.valueOf(phieuMuon_dto.maTV));
        tvid.setText("mã Phiếu: "+phieuMuon_dto.maPM);
        tvtenTV.setText(thanhVienDto.hoTen);
        tvTenS.setText(sach_dto.tenSach);
        tvtienthue.setText("Tiền Thuê: "+phieuMuon_dto.tienThue);
        tvngaythue.setText("Ngày Thuê: "+dateFormat.format(phieuMuon_dto.ngay));
        if (phieuMuon_dto.traSach==1){
            tvtrangthai.setTextColor(Color.BLUE);
            tvtrangthai.setText("Đã Trả Sách");
        }else{
            tvtrangthai.setTextColor(Color.RED);
            tvtrangthai.setText("Chưa trả Sách");
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                tvfm.xoa(String.valueOf(thanhVienDto.maTV));
                PhieuMuon_DTO phieuMuonDto=list.get(i);
                xoa(phieuMuonDto);
            }


        });
        return v;

    }public void xoa(PhieuMuon_DTO phieuMuonDto) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Xóa");
        builder.setMessage("Bạn có muốn xóa Không");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int id=phieuMuon_dao.DeletePHieuMuon(phieuMuonDto);
                if (id>0){
                    Toast.makeText(context, "xoa Thành Công ", Toast.LENGTH_SHORT).show();
                    list.clear();;
                    list.addAll(phieuMuon_dao.getAll());
                    notifyDataSetChanged();
                    dialogInterface.cancel();

                }else{
                    Toast.makeText(context, "xoa Thất bại ", Toast.LENGTH_SHORT).show();
                }


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create();
        builder.show();
    }
}
