package DTO;

public class LoaiSach_DTO {
    public int maLoai;
    public   String tenLoai;

    public LoaiSach_DTO(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public LoaiSach_DTO(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public LoaiSach_DTO() {
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
