package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.ThuThu_DTO;
import Database.Dbhepler;

public class ThuThuDAO {
    Dbhepler dbhepler;
    SQLiteDatabase db;
    public ThuThuDAO(Context context){
        dbhepler=new Dbhepler(context);
        db=dbhepler.getWritableDatabase();
    }


    public  long intsertTT(ThuThu_DTO thuThu_dto){
        ContentValues values=new ContentValues();
        values.put("MaTT",thuThu_dto.maTT);
        values.put("TenThuThu",thuThu_dto.hoTen);
        values.put("MatKhau",thuThu_dto.matKhau);
        return db.insert("Thuthu",null,values);


    }
    public  int updateTT(ThuThu_DTO thuThu_dto){
        ContentValues values=new ContentValues();
        values.put("TenThuThu",thuThu_dto.hoTen);
        values.put("MatKhau",thuThu_dto.matKhau);
        String[]dk=new String[]{thuThu_dto.maTT};
        return db.update("Thuthu",values,"MaTT=?",dk);
    }
    public  int DeleteTT(ThuThu_DTO thuThu_dto){
        String[]dk=new String[]{thuThu_dto.maTT};
        return db.delete("Thuthu","MaTT=?",dk);

    }

    public   List<ThuThu_DTO>getAll(){
        String sql="SELECT * From Thuthu";
        return getdata(sql);
    }

    public   ThuThu_DTO getheoId(String id){
        String sql="SELECT * From Thuthu where MaTT=?";
        List<ThuThu_DTO> list= getdata(sql,id);

        return list.get(0);
    }

    private List<ThuThu_DTO>getdata(String sql, String...dieukien){
        List<ThuThu_DTO>list = new ArrayList<>();
        Cursor c=db.rawQuery(sql,dieukien);
        if (c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                list.add(new ThuThu_DTO(c.getString(0),c.getString(1),c.getString(2)));
            }while (c.moveToNext());
        }

        return list;
    }
    public  int checklogin(String id,String password){
        String sql="SELECT * FROM Thuthu WHERE MaTT=? and MatKhau=?";
        List<ThuThu_DTO>list=getdata(sql,id,password);
        if (list.size()==0) {
            return -1;
        }
        return 1;


    }


}
