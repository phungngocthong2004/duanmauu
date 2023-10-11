package DTO;

public class ThuThu_DTO {
   public String maTT;
    public String hoTen;
   public String matKhau;

    public ThuThu_DTO(String maTT, String hoTen, String matKhau) {
        this.maTT = maTT;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public ThuThu_DTO(String hoTen, String matKhau) {
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public ThuThu_DTO() {
    }

//    public String getMaTT() {
//        return maTT;
//    }
//
//    public void setMaTT(String maTT) {
//        this.maTT = maTT;
//    }
//
//    public String getHoTen() {
//        return hoTen;
//    }
//
//    public void setHoTen(String hoTen) {
//        this.hoTen = hoTen;
//    }
//
//    public String getMatKhau() {
//        return matKhau;
//    }
//
//    public void setMatKhau(String matKhau) {
//        this.matKhau = matKhau;
//    }
}

