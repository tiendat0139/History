package models;

import java.util.List;

public class Festival<T> {
    private String tenLeHoi;
    private String ngayBatDau;
    private String lanDauToChuc;
    private String diaDiem;
    private List<T> nhanVat;

    public Festival(String tenLeHoi, String ngayBatDau, String lanDauToChuc, String diaDiem, List<T> nhanVat) {
        this.tenLeHoi = tenLeHoi;
        this.ngayBatDau = ngayBatDau;
        this.lanDauToChuc = lanDauToChuc;
        this.diaDiem = diaDiem;
        this.nhanVat = nhanVat;
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

    public List<T> getNhanVat() {
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

    public void setNhanVat(List<T> nhanVat) {
        this.nhanVat = nhanVat;
    }
}
