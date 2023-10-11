package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.ThanhVien_DTO;
import Database.Dbhepler;

public class ThanhVien_DAO {
    Dbhepler dbhepler;
    SQLiteDatabase db;
    public ThanhVien_DAO(Context context){
        dbhepler=new Dbhepler(context);
        db=dbhepler.getWritableDatabase();
    }


    public  long intsertTV(ThanhVien_DTO thanhVien_dto){
        ContentValues values=new ContentValues();
        values.put("Hoten",thanhVien_dto.hoTen);
        values.put("NamSinh",thanhVien_dto.namSinh);
        values.put("cccd",thanhVien_dto.cccd);
        return db.insert("ThanhVien",null,values);


    }
    public  int updateTV(ThanhVien_DTO thanhVien_dto){
        ContentValues values=new ContentValues();
        values.put("Hoten",thanhVien_dto.hoTen);
        values.put("NamSinh",thanhVien_dto.namSinh);
        values.put("cccd",thanhVien_dto.cccd);
        String[]dk=new String[]{String.valueOf(thanhVien_dto.maTV)};
        return db.update("ThanhVien",values,"MaTV=?",dk);
    }
    public  int DeleteTV(ThanhVien_DTO thanhVien_dto){
        String[]dk=new String[]{String.valueOf(thanhVien_dto.maTV)};
        return db.delete("ThanhVien","MaTV=?",dk);
    }

    public List<ThanhVien_DTO> getAll(){
        String sql="SELECT * FROM ThanhVien ";
        return getdata(sql);
    }
    public ThanhVien_DTO getID(String id){
        String sql="SELECT * FROM ThanhVien where MaTV=? ";
         List<ThanhVien_DTO>listtV=getdata(sql,id);

        return  listtV.get(0);
    }
//    public ThanhVien_DTO DeleteID(String id){
//        String sql="delete FROM ThanhVien where MaTV=? ";
//        List<ThanhVien_DTO>listtV=getdata(sql,id);
//
//        return (ThanhVien_DTO) listtV;
//
//    }
    public List<ThanhVien_DTO> getdata(String sql,String...dieuKien){
        List<ThanhVien_DTO>list = new ArrayList<>();
        Cursor c=db.rawQuery(sql,dieuKien);
        if (c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                list.add(new ThanhVien_DTO(c.getInt(0),c.getString(1),c.getString(2),c.getString(3)));
            }while (c.moveToNext());
        }

        return list;
    }
}
