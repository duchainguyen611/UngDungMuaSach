package com.example.baitaplon;

public class GioHang {
    private int id;
    private String User;
    private  String anhSP;
    private String tenSP;
    private int dongia;
    private int soluong;

    public GioHang(int id, String user, String anhSP, String tenSP, int dongia, int soluong) {
        this.id = id;
        this.User = user;
        this.anhSP = anhSP;
        this.tenSP = tenSP;
        this.dongia = dongia;
        this.soluong = soluong;
    }

    public GioHang(String user, String anhSP, String tenSP, int dongia, int soluong) {
        this.User = user;
        this.anhSP = anhSP;
        this.tenSP = tenSP;
        this.dongia = dongia;
        this.soluong = soluong;
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

    public String getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(String anhSP) {
        this.anhSP = anhSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int thanhtien(){
        return this.dongia*this.soluong;
    }
}
