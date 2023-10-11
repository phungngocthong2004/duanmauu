package DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import DTO.Sach_DTO;
import DTO.Top10_DTO;
import Database.Dbhepler;

public class ThongKe_DAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat spf = new SimpleDateFormat("dd-MM-yyyy");

    public ThongKe_DAO(Context context) {
        this.context = context;
        Dbhepler dbhepler = new Dbhepler(context);
        db = dbhepler.getWritableDatabase();
    }

    //top 10
    @SuppressLint("Range")
    public List<Top10_DTO> getTop() {
        List<Top10_DTO> list = new ArrayList<>();
        String sql = "SELECT   Masach,count(Masach) as soluong From PhieuMuon GROUP BY Masach ORDER BY soluong DESC  LIMIT 10";
        Sach_DAO sachDao = new Sach_DAO(context);
        Cursor c = db.rawQuery(sql, null);

        while (c.moveToNext()) {
            Top10_DTO top = new Top10_DTO();
            Sach_DTO sach = sachDao.getheoId(c.getString(c.getColumnIndex("Masach")));
            top.tenSach = sach.getTenSach();
            top.soluong = Integer.parseInt(c.getString(c.getColumnIndex("soluong")));
            list.add(top);
        }
        return list;
    }

    //thong ke doanh thu

    @SuppressLint("Range")
    public int getDoanhthu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(TienThue) FROM PhieuMuon Where Ngay BETWEEN ? AND ? ";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});

        while (c.moveToNext()) {
            list.add(c.getInt(0));
        }

        return list.get(0);
    }

}
