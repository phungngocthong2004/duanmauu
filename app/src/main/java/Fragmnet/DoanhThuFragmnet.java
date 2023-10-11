package Fragmnet;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmauuu.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import DAO.ThongKe_DAO;

public class DoanhThuFragmnet extends Fragment {
    Button btntungay,btndenngay,btntinhdoanhthu;
    EditText edtungay,eddenngay;
    TextView tvdoanhthu;
    int mDay,mMoth,mYear;
    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.doanhthufragmnet,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btntungay=view.findViewById(R.id.btntungay);
        btndenngay=view.findViewById(R.id.btndenngay);
        btntinhdoanhthu=view.findViewById(R.id.btntinhdoanhthu);
        edtungay=view.findViewById(R.id.edtungayyy);
        eddenngay=view.findViewById(R.id.eddenngay);

        tvdoanhthu=view.findViewById(R.id.tvdoanhthu);


        btntungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                mDay=calendar.get(Calendar.DAY_OF_MONTH);
                mMoth=calendar.get(Calendar.MONTH);
                mYear=calendar.get(Calendar.YEAR);
                DatePickerDialog d= new DatePickerDialog(getContext(),0,mDateTungay,mYear,mMoth,mDay );
                d.show();
            }
        });
        btndenngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                mDay=calendar.get(Calendar.DAY_OF_MONTH);
                mMoth=calendar.get(Calendar.MONTH);
                mYear=calendar.get(Calendar.YEAR);
                DatePickerDialog d= new DatePickerDialog(getContext(),0,mDatedenngay,mYear,mMoth,mDay );
                d.show();
            }
        });
        btntinhdoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String star = edtungay.getText().toString();
                String end = eddenngay.getText().toString() ;
                try {
                    Date ds = dateFormat.parse(star);
                    Date de = dateFormat.parse(end );
                    long startDate = ds.getTime();
                    long endDate = de.getTime();
                    ThongKe_DAO thongKeDao= new ThongKe_DAO(getContext());
                    tvdoanhthu.setText("Doanh Thu: "+thongKeDao.getDoanhthu(String.valueOf(startDate), String.valueOf(endDate))+" VND");
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
    DatePickerDialog.OnDateSetListener mDateTungay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
         mYear=i;
         mMoth=i1;
         mDay=i2;
         GregorianCalendar c=new GregorianCalendar(mYear,mMoth,mDay);
         edtungay.setText(dateFormat.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDatedenngay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear=i;
            mMoth=i1;
            mDay=i2;
            GregorianCalendar c=new GregorianCalendar(mYear,mMoth,mDay);
            eddenngay.setText(dateFormat.format(c.getTime()));
        }
    };

}
