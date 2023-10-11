package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.Sach_DTO;
import Database.Dbhepler;

public class Sach_DAO {
    Dbhepler dbhepler;
    SQLiteDatabase db;
    public Sach_DAO(Context context){
        dbhepler=new Dbhepler(context);
        db=dbhepler.getWritableDatabase();
    }


    public  long intsertSach(Sach_DTO obj){
        ContentValues values=new ContentValues();
        values.put("TenSach",obj.getTenSach());
        values.put("GiaThue",obj.getGiaThue());
        values.put("MaLoai",obj.getMaLoai());
        return db.insert("Sach",null,values);


    }
    public  int updateSach(Sach_DTO obj){
        ContentValues values=new ContentValues();
        values.put("TenSach",obj.getTenSach());
        values.put("GiaThue",obj.getGiaThue());
        values.put("MaLoai",obj.getMaLoai());
        String[]dk=new String[]{String.valueOf(obj.getMaSach())};
        return db.update("Sach",values,"MaSach=?",dk);
    }
    public  int DeleteSach(Sach_DTO obj){
        String[]dk=new String[]{String.valueOf(obj.getMaSach())};
        return db.delete("Sach","MaSach=?",dk);
    }
    public   List<Sach_DTO>getAll(){
        String sql="Select * From Sach  ";
        return getdata(sql);
    }

    public  Sach_DTO getheoId(String  id){
        String sql="Select * From Sach where MaSach=?";
        List<Sach_DTO> list= getdata(sql, id);
        return  list.get(0);
    }

    public List<Sach_DTO> getdata(String sql,String...dieukien){
        List<Sach_DTO>list = new ArrayList<>();
        Cursor c=db.rawQuery(sql,dieukien);
        if (c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                list.add(new Sach_DTO(c.getInt(0),c.getString(1),c.getInt(2),c.getInt(3)));
            }while (c.moveToNext());
        }

        return list;
    }
}
