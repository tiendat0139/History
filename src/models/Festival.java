package models;

import java.util.List;

public class Festival {
    private String tenLeHoi;
    private String ngayBatDau;
    private String lanDauToChuc;
    private String diaDiem;
    private List<String> tenNhanVat;
    private List<Person> nhanVat;

    public Festival(String tenLeHoi, String ngayBatDau, String lanDauToChuc, String diaDiem, List<String> tenNhanVat) {
        this.tenLeHoi = tenLeHoi;
        this.ngayBatDau = ngayBatDau;
        this.lanDauToChuc = lanDauToChuc;
        this.diaDiem = diaDiem;
        this.tenNhanVat = tenNhanVat;
    }

    public String getTenLeHoi() {
        return tenLeHoi;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public String getLanDauToChuc() {
        return lanDauToChuc;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public List<String> getTenNhanVat() {
        return tenNhanVat;
    }

    public List<Person> getNhanVat() {
        return nhanVat;
    }

    public void setTenLeHoi(String tenLeHoi) {
        this.tenLeHoi = tenLeHoi;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public void setLanDauToChuc(String lanDauToChuc) {
        this.lanDauToChuc = lanDauToChuc;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public void setTenNhanVat(List<String> tenNhanVat) {
        this.tenNhanVat = tenNhanVat;
    }

    public void setNhanVat(List<Person> nhanVat) {
        this.nhanVat = nhanVat;
    }
}
