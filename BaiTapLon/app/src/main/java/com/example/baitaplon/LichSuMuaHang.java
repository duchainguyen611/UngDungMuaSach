package com.example.baitaplon;

public class LichSuMuaHang {
    private int id;
    private String User;
    private String anhSPLSMH;
    private String tenSPLSMH;
    private int dongiaSPLSMH;
    private int soluongSPLSMH;

    public LichSuMuaHang(int id, String user, String anhSPLSMH, String tenSPLSMH, int dongiaSPLSMH, int soluongSPLSMH) {
        this.id = id;
        User = user;
        this.anhSPLSMH = anhSPLSMH;
        this.tenSPLSMH = tenSPLSMH;
        this.dongiaSPLSMH = dongiaSPLSMH;
        this.soluongSPLSMH = soluongSPLSMH;
    }

    public LichSuMuaHang(String user, String anhSPLSMH, String tenSPLSMH, int dongiaSPLSMH, int soluongSPLSMH) {
        User = user;
        this.anhSPLSMH = anhSPLSMH;
        this.tenSPLSMH = tenSPLSMH;
        this.dongiaSPLSMH = dongiaSPLSMH;
        this.soluongSPLSMH = soluongSPLSMH;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getAnhSPLSMH() {
        return anhSPLSMH;
    }

    public void setAnhSPLSMH(String anhSPLSMH) {
        this.anhSPLSMH = anhSPLSMH;
    }

    public String getTenSPLSMH() {
        return tenSPLSMH;
    }

    public void setTenSPLSMH(String tenSPLSMH) {
        this.tenSPLSMH = tenSPLSMH;
    }

    public int getDongiaSPLSMH() {
        return dongiaSPLSMH;
    }

    public void setDongiaSPLSMH(int dongiaSPLSMH) {
        this.dongiaSPLSMH = dongiaSPLSMH;
    }

    public int getSoluongSPLSMH() {
        return soluongSPLSMH;
    }

    public void setSoluongSPLSMH(int soluongSPLSMH) {
        this.soluongSPLSMH = soluongSPLSMH;
    }

    public int thanhtienSPLSMH(){
        return  this.dongiaSPLSMH*this.soluongSPLSMH;
    }
}
