package Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.duanmauuu.R;

import java.util.ArrayList;
import java.util.List;

import DAO.ThanhVien_DAO;
import DTO.ThanhVien_DTO;
import Fragmnet.ThanhVienFragmennt;

public class ThanhVienAdapter extends BaseAdapter {
   private Context context;
    ThanhVienFragmennt tvfm;
   List<ThanhVien_DTO> list;

   ThanhVien_DTO thanhVienDto;
    ThanhVien_DAO dao;

    public ThanhVienAdapter(Context context, List<ThanhVien_DTO> list) {
        this.context = context;

        this.list = list;
        dao=new ThanhVien_DAO(context);
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
        View v= inflater.inflate(R.layout.dongthanhvien,null);
        TextView tvid=v.findViewById(R.id.tvtv);
        TextView tvten=v.findViewById(R.id.tvten);
        TextView tvtuoi=v.findViewById(R.id.tvnamsinh);
        TextView tvcccd=v.findViewById(R.id.tvcccd);
        ImageView imgxoa=v.findViewById(R.id.imgxoatv);
        thanhVienDto=list.get(i);
        tvid.setText("id: "+thanhVienDto.maTV);
        tvten.setText("Họ Tên: "+thanhVienDto.hoTen);
        tvtuoi.setText("Năm Sinh: "+thanhVienDto.namSinh);
        tvcccd.setText("cccd: "+thanhVienDto.cccd);

        imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                tvfm.xoa(String.valueOf(thanhVienDto.maTV));
                ThanhVien_DTO objVienDto=list.get(i);
                xoa(objVienDto);
            }
        });
        return v;
    }
    public  void xoa(ThanhVien_DTO obVienDto){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Xóa");
        builder.setMessage("Bạn có muốn xóa Không");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int id=dao.DeleteTV(obVienDto);
                if (id>0){
                    Toast.makeText(context, "xoa Thành Công Thành Viên", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(dao.getAll());
                    notifyDataSetChanged();
                    dialogInterface.cancel();

                }else{
                    Toast.makeText(context, "xoa Thất bại Thành Viên", Toast.LENGTH_SHORT).show();
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
