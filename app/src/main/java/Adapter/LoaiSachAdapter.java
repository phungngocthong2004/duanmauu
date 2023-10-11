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
import DTO.LoaiSach_DTO;

public class LoaiSachAdapter extends BaseAdapter {
    private Context context;
    private List<LoaiSach_DTO> list;
    LoaiSach_DAO sachDao;
    LoaiSach_DTO loaiSachDto;

    public LoaiSachAdapter(Context context, List<LoaiSach_DTO> list) {
        this.context = context;
        this.list = list;
        sachDao=new LoaiSach_DAO(context);
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
        View v=inflater.inflate(R.layout.dong_loai_sach,null);
         loaiSachDto=list.get(i);
        TextView tvidls=v.findViewById(R.id.tvls);
        TextView tvtenloai=v.findViewById(R.id.tvtenloai);
        ImageView imgxoa=v.findViewById(R.id.imgxoals);

        tvidls.setText("Mã Loại: "+list.get(i).getMaLoai());
        tvtenloai.setText("Tên loại Sách: "+list.get(i).getTenLoai());

        imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSach_DTO loaiSach_dto=list.get(i);

                xoa(loaiSach_dto);
            }
        });
        return v;
    }
    public  void xoa(LoaiSach_DTO loaiSach_dto){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Xóa");
        builder.setMessage("Bạn có muốn xóa Không");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int id=sachDao.DeleteLoaiS(loaiSachDto);
                if (id>0){

                    Toast.makeText(context, "xoa Thành Công Loại Sách", Toast.LENGTH_SHORT).show();
                    list.clear();;
                    list.addAll(sachDao.getAll());
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
