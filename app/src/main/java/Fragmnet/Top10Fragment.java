package Fragmnet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmauuu.R;

import java.util.List;

import Adapter.ThongKeTopAdapter;
import DAO.ThongKe_DAO;
import DTO.Top10_DTO;

public class Top10Fragment extends Fragment {
    ListView lvtop;
    List<Top10_DTO>list;
    ThongKeTopAdapter adapter;
    ThongKe_DAO thongKeDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.top10fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvtop=view.findViewById(R.id.lvtop);

        thongKeDao=new ThongKe_DAO(getContext());
        list=thongKeDao.getTop();
        adapter=new ThongKeTopAdapter(getContext(),list);
        lvtop.setAdapter(adapter);
    }
}
