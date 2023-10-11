package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmauuu.R;

import java.util.List;

import DTO.Top10_DTO;

public class ThongKeTopAdapter extends BaseAdapter {
    private Context context;
    private List<Top10_DTO>list;

    public ThongKeTopAdapter(Context context, List<Top10_DTO> list) {
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
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.dongtop10,null);
        TextView tvtens=v.findViewById(R.id.tvtenSachTop10);
        TextView tvsoloung=v.findViewById(R.id.tvsoluongtop10);
         tvtens.setText("Tên Sách: "+list.get(i).tenSach);
         tvsoloung.setText("Số Lượng: "+list.get(i).soluong+"");
        return v;
    }
}
