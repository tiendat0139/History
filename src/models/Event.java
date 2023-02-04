package models;

import java.util.List;

public class Event {
    private String tenSuKien;
    private String thoiGian;
    private String dienBien;
    private String tenDiaDiem;
    private List<String> tenNhanVat;
    private List<Person> nhanVat;
    private Place diaDiem;

    public Event(String tenSuKien, String thoiGian, String tenDiaDiem, String dienBien, List<String> tenNhanVat) {
        this.tenSuKien = tenSuKien;
        this.thoiGian = thoiGian;
        this.tenDiaDiem = tenDiaDiem;
        this.dienBien = dienBien;
        this.tenNhanVat = tenNhanVat;
    }

    public String getTenSuKien() {
        return tenSuKien;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public String getDienBien() {
        return dienBien;
    }

    public List<String> getTenNhanVat() {
        return tenNhanVat;
    }

    public List<Person> getNhanVat() {
        return nhanVat;
    }

    public Place getDiaDiem() {
        return diaDiem;
    }

    public void setTenSuKien(String tenSuKien) {
        this.tenSuKien = tenSuKien;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public void setDienBien(String dienBien) {
        this.dienBien = dienBien;
    }

    public void setTenNhanVat(List<String> tenNhanVat) {
        this.tenNhanVat = tenNhanVat;
    }

    public void setNhanVat(List<Person> nhanVat) {
        this.nhanVat = nhanVat;
    }

    public void setDiaDiem(Place diaDiem) {
        this.diaDiem = diaDiem;
    }
}
