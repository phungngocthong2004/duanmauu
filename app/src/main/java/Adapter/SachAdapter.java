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

import java.util.List;

import DAO.LoaiSach_DAO;
import DAO.Sach_DAO;
import DTO.Sach_DTO;

public class SachAdapter extends BaseAdapter {
    private Context context;
    private List<Sach_DTO> list;
    private Sach_DAO sachDao;
    Sach_DTO sach_dto;
    private LoaiSach_DAO loaiSachDao;

    public SachAdapter(Context context, List<Sach_DTO> list) {
        this.context = context;
        this.list = list;
        sachDao = new Sach_DAO(context);
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dongsach, null);
        TextView tvid = v.findViewById(R.id.tvtvS);
        TextView tvtenS = v.findViewById(R.id.tvtenS);
        TextView tvGiaThue = v.findViewById(R.id.tvgiathue);
        TextView tvLoaiS = v.findViewById(R.id.tvLoaiS);
        ImageView imgxoa = v.findViewById(R.id.imgxoas);


        sach_dto = list.get(i);
        tvid.setText("id: " + sach_dto.maSach + "");
        tvtenS.setText("Tên Sách: " + sach_dto.tenSach);
        tvGiaThue.setText("Giá Thuê: " + sach_dto.giaThue + "");

        loaiSachDao = new LoaiSach_DAO(context);
        tvLoaiS.setText("Tên Loại: " + loaiSachDao.getTenLoaiSach(sach_dto.maLoai));

        imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sach_DTO sachDto=list.get(i);
                xoa(sachDto);
            }
        });
        return v;
    }

    public void xoa(Sach_DTO sachDto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xóa");
        builder.setMessage("Bạn có muốn xóa Không");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int id = sachDao.DeleteSach(sachDto);
                if (id > 0) {
                    Toast.makeText(context, "xoa Thành Công!", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(sachDao.getAll());
                    notifyDataSetChanged();
                    dialogInterface.cancel();

                } else {
                    Toast.makeText(context, "xoa Thất bại", Toast.LENGTH_SHORT).show();
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
