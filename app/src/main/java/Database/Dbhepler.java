package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhepler extends SQLiteOpenHelper {
    static  final  String  dbName="PLNIB";
    static  final  int  version=2;
    public Dbhepler(Context  context){
        super(context,dbName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String createTbThuThu="CREATE TABLE Thuthu (   MaTT      TEXT PRIMARY KEY , TenThuThu TEXT    NOT NULL, MatKhau   TEXT    NOT NULL);";
            sqLiteDatabase.execSQL(createTbThuThu);
            String createTbThanhVien="CREATE TABLE ThanhVien ( MaTV    INTEGER PRIMARY KEY AUTOINCREMENT,   Hoten   TEXT    NOT NULL,  NamSinh TEXT    NOT NULL,cccd TEXT NOT NULL);";
            sqLiteDatabase.execSQL(createTbThanhVien);
            String createTbLoaiSach="CREATE TABLE LoaiSach (    MaLoaiSach  INTEGER PRIMARY KEY AUTOINCREMENT,TenLoaiSach TEXT    NOT NULL);";
            sqLiteDatabase.execSQL(createTbLoaiSach);
            String createTbSach="CREATE TABLE Sach ( MaSach  INTEGER PRIMARY KEY AUTOINCREMENT, TenSach TEXT    NOT NULL, GiaThue INTEGER NOT NULL, MaLoai  INTEGER REFERENCES LoaiSach (MaLoaiSach) );";
            sqLiteDatabase.execSQL(createTbSach);
            String createTbPhieuMuon="CREATE TABLE PhieuMuon (  MaPM     INTEGER PRIMARY KEY AUTOINCREMENT,   MaTT      TEXT  REFERENCES Thuthu (MaTT),  MaTV     INTEGER REFERENCES ThanhVien (MaTV),  Masach   INTEGER REFERENCES Sach (MaSach),TienThue INTEGER NOT NULL, Ngay     DATE    NOT NULL, TraSach  INTEGER NOT NULL);";
            sqLiteDatabase.execSQL(createTbPhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i1!=i){
            String DroptableThuTHu="drop table if exists Thuthu";
            sqLiteDatabase.execSQL(DroptableThuTHu);
            String DroptableThanhVien="drop table if exists ThanhVien";
            sqLiteDatabase.execSQL(DroptableThanhVien);
            String DroptableLoaiSach="drop table if exists LoaiSach";
            sqLiteDatabase.execSQL(DroptableLoaiSach);
            String Droptablesach="drop table if exists Sach";
            sqLiteDatabase.execSQL(Droptablesach);
            String DroptablePhieuMuon="drop table if exists PhieuMuon";
            sqLiteDatabase.execSQL(DroptablePhieuMuon);
            onCreate(sqLiteDatabase);
        }

    }
}
