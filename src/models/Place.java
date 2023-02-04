package models;

import java.util.List;

public class Place {
    private String tenDiaDiem;

    private String dienBien;

    private List<String> suKien;

    public Place() {

    }

    public Place(String tenSuKien, String dienBien, List<String> suKien) {
        this.tenDiaDiem = tenSuKien;
        this.dienBien = dienBien;
        this.suKien = suKien;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public String getDienBien() {
        return dienBien;
    }

    public List<String> getSuKien() {
        return suKien;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public void setDienBien(String dienBien) {
        this.dienBien = dienBien;
    }

    public void setSuKien(List<String> suKien) {
        this.suKien = suKien;
    }
}
