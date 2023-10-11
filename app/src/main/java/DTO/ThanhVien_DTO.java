package DTO;

public class ThanhVien_DTO {
    public int maTV;
    public   String hoTen;
    public String namSinh;
    public  String  cccd;


    public ThanhVien_DTO(int maTV, String hoTen, String namSinh, String cccd) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.cccd = cccd;
    }

    public ThanhVien_DTO(String hoTen, String namSinh) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public ThanhVien_DTO() {
    }

//    public int getMaTV() {
//        return maTV;
//    }
//
//    public void setMaTV(int maTV) {
//        this.maTV = maTV;
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
//    public String getNamSinh() {
//        return namSinh;
//    }
//
//    public void setNamSinh(String namSinh) {
//        this.namSinh = namSinh;
//    }
}

