package models;

import java.util.List;

public class Event<T, U> {
    private String tenSuKien;
    private String thoiGian;
    private String dienBien;
    private List<T> nhanVat;
    private U diaDiem;

    public Event(String tenSuKien, String thoiGian, U diaDiem, String dienBien, List<T> nhanVat) {
        this.tenSuKien = tenSuKien;
        this.thoiGian = thoiGian;
        this.diaDiem = diaDiem;
        this.dienBien = dienBien;
        this.nhanVat = nhanVat;
    }

    public String getTenSuKien() {
        return tenSuKien;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public String getDienBien() {
        return dienBien;
    }

    public List<T> getNhanVat() {
        return nhanVat;
    }

    public U getDiaDiem() {
        return diaDiem;
    }

    public void setTenSuKien(String tenSuKien) {
        this.tenSuKien = tenSuKien;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public void setDienBien(String dienBien) {
        this.dienBien = dienBien;
    }

    public void setNhanVat(List<T> nhanVat) {
        this.nhanVat = nhanVat;
    }

    public void setDiaDiem(U diaDiem) {
        this.diaDiem = diaDiem;
    }
}
