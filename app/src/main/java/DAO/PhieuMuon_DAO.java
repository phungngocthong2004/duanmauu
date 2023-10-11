package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DTO.PhieuMuon_DTO;
import Database.Dbhepler;

public class PhieuMuon_DAO {
    Dbhepler dbhepler;
    SQLiteDatabase db;
    public PhieuMuon_DAO(Context context){
        dbhepler=new Dbhepler(context);
        db=dbhepler.getWritableDatabase();
    }


    public  long intsertPHieuMuon(PhieuMuon_DTO obj){
        ContentValues values=new ContentValues();
        values.put("MaTT",obj.getMaTT());
        values.put("MaTV",obj.getMaTV());
        values.put("Masach",obj.getMaSach());
        values.put("TienThue",obj.getTienThue());
        values.put("Ngay", (obj.getNgay()).getTime());
        values.put("TraSach",obj.getTraSach());
        return db.insert("PhieuMuon",null,values);


    }
    public  int updatePHieuMuon(PhieuMuon_DTO obj){
        ContentValues values=new ContentValues();
        values.put("MaTT",obj.getMaTT());
        values.put("MaTV",obj.getMaTV());
        values.put("Masach",obj.getMaSach());
        values.put("TienThue",obj.getTienThue());
        values.put("Ngay", (obj.getNgay()).getTime());
        values.put("TraSach",obj.getTraSach());
        String[]dk=new String[]{String.valueOf(obj.getMaPM())};
        return db.update("PhieuMuon",values,"MaPM=?",dk);
    }
    public  int DeletePHieuMuon(PhieuMuon_DTO obj){
        String[]dk=new String[]{String.valueOf(obj.getMaPM())};
        return db.delete("PhieuMuon","MaPM=?",dk);

    }

    public List<PhieuMuon_DTO> getAll(){
        String sql="Select * From PhieuMuon";
        return getdata(sql);
    }

    public  PhieuMuon_DTO getheoId(String id){
        String sql="Select * From PhieuMuon where MaPM=?";
        List<PhieuMuon_DTO> list= getdata(sql,id);

        return list.get(0);
    }

    private List<PhieuMuon_DTO>getdata(String sql, String...dieukien){
        List<PhieuMuon_DTO>list = new ArrayList<>();
        Cursor c=db.rawQuery(sql,dieukien);

        if (c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                long datemili =c.getLong(5);
                Date date=new Date(datemili);
                list.add(new PhieuMuon_DTO(c.getInt(0),c.getString(1),c.getInt(2),c.getInt(3),c.getInt(4),c.getInt(6),date));
            }while (c.moveToNext());
        }

        return list;
    }
}
