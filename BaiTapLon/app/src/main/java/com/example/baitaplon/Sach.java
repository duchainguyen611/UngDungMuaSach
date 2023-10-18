package com.example.baitaplon;

public class Sach {
    private String loaisp;
    private String anhsp;
    private String ten;
    private int dongia;


    public Sach(String loaisp, String anhsp, String ten, int dongia) {
        this.loaisp = loaisp;
        this.anhsp = anhsp;
        this.ten = ten;
        this.dongia = dongia;
    }

    public String getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }

    public String getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(String anhsp) {
        this.anhsp = anhsp;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }
}
