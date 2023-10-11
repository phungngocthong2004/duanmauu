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

import DAO.Sach_DAO;
import DTO.Sach_DTO;

public class SachFragmnetAdapter extends BaseAdapter {
    private Context context;
    private List<Sach_DTO> list;

    Sach_DTO sach_dto;


    public SachFragmnetAdapter(Context context, List<Sach_DTO> list) {
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dongfragmnetsach, null);
        TextView tvid = v.findViewById(R.id.tvidsach);
        TextView tvtenS = v.findViewById(R.id.tvtennnnnS);
        sach_dto = list.get(i);
        tvid.setText( sach_dto.maSach +"" );
        tvtenS.setText( sach_dto.tenSach);


        return v;
    }
}
