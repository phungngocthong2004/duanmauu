package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmauuu.R;

import java.util.List;

import DAO.ThanhVien_DAO;
import DTO.ThanhVien_DTO;
import Fragmnet.ThanhVienFragmennt;

public class ThanhVienFragmentADapter extends BaseAdapter {
    private Context context;

    List<ThanhVien_DTO> list;

    ThanhVien_DTO thanhVienDto;



    public ThanhVienFragmentADapter(Context context, List<ThanhVien_DTO> list) {
        this.context = context;
        this.list = list;
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
        View v= inflater.inflate(R.layout.dongfragmnettv,null);
        TextView tvid=v.findViewById(R.id.tvidtv);
        TextView tvten=v.findViewById(R.id.tvtentv);

        thanhVienDto=list.get(i);
        tvid.setText(thanhVienDto.maTV+"");
        tvten.setText(thanhVienDto.hoTen);


        return v;
    }
}
