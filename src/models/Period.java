package models;

import java.util.List;

public class Period {
    private String tenTrieuDai;
    private String thoiGianTonTai;
    private List<Person> nhanVat;

    public Period(String tenTrieuDai, String thoiGianTonTai) {
        this.tenTrieuDai = tenTrieuDai;
        this.thoiGianTonTai = thoiGianTonTai;
    }

    public String getTenTrieuDai() {
        return tenTrieuDai;
    }

    public String getThoiGianTonTai() {
        return thoiGianTonTai;
    }

    public List<Person> getNhanVat() {
        return nhanVat;
    }

    public void setTenTrieuDai(String tenTrieuDai) {
        this.tenTrieuDai = tenTrieuDai;
    }

    public void setThoiGianTonTai(String thoiGianTonTai) {
        this.thoiGianTonTai = thoiGianTonTai;
    }

    public void setNhanVat(List<Person> nhanVat) {
        this.nhanVat = nhanVat;
    }


}
