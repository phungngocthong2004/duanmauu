package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.LoaiSach_DTO;
import Database.Dbhepler;

public class LoaiSach_DAO {
    Dbhepler dbhepler;
    SQLiteDatabase db;

    public LoaiSach_DAO(Context context) {
        dbhepler = new Dbhepler(context);
        db = dbhepler.getWritableDatabase();
    }


    public long intsertLoaiS(LoaiSach_DTO obj) {
        ContentValues values = new ContentValues();
        values.put("TenLoaiSach", obj.getTenLoai());
        return db.insert("LoaiSach", null, values);


    }

    public int updateLoaiS(LoaiSach_DTO obj) {
        ContentValues values = new ContentValues();
        values.put("TenLoaiSach", obj.getTenLoai());
        String[] dk = new String[]{String.valueOf(obj.getMaLoai())};
        return db.update("LoaiSach", values, "MaLoaiSach=?", dk);
    }

    public int DeleteLoaiS(LoaiSach_DTO obj) {
        String[] dk = new String[]{String.valueOf(obj.getMaLoai())};
        return db.delete("LoaiSach", "MaLoaiSach=?", dk);
    }

    public List<LoaiSach_DTO> getAll() {
        String sql = "Select * From LoaiSach";
        return getdata(sql);
    }

    public LoaiSach_DTO getheoId(String id) {
        String sql = "Select * From MaLoaiSach where MaLoaiSach=?";
        List<LoaiSach_DTO> list = getdata(sql, id);
        return list.get(0);
    }

    public List<LoaiSach_DTO> getdata(String sql, String... dieukien) {
        List<LoaiSach_DTO> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, dieukien);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                list.add(new LoaiSach_DTO(c.getInt(0), c.getString(1)));
            } while (c.moveToNext());
        }

        return list;
    }

    //get tên bằng id
    public String getTenLoaiSach(int id) {
        String tenLoaiSach = "";
//        String sql = "SELECT TenLoaiSach FROM LoaiSach WHERE MaLoaiSach = '" + id + "'";
        Cursor c = db.rawQuery("SELECT TenLoaiSach FROM LoaiSach WHERE MaLoaiSach =?", new String[]{String.valueOf(id)});
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            tenLoaiSach = c.getString(0);
        }
        return tenLoaiSach;
    }
}
