package com.example.baitaplon;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DbHelper2 extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "app.db";
    private static final String TABLE_NAME_GIOHANG = "giohang";
    private static final String TABLE_NAME_LSMH = "lsmh";
    private static final String KEY_ID = "id";
    private static final String KEY_USER = "user";
    private static final String KEY_ANHSP= "anhsp";
    private static final String KEY_TENSP = "tensp";
    private static final String KEY_DONGIA = "dongia";
    private static final String KEY_SOLUONG = "soluong";
    private static final String KEY_THANHTIEN= "thanhtien";

    private static final String CREATE_GIOHANG_TABLE = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT,%s TEXT, %s INTEGER, %s INTEGER, %s INTEGER)", TABLE_NAME_GIOHANG, KEY_ID, KEY_USER, KEY_ANHSP, KEY_TENSP, KEY_DONGIA, KEY_SOLUONG, KEY_THANHTIEN);

    private static final String CREATE_LSMH_TABLE = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT,%s TEXT, %s INTEGER, %s INTEGER, %s INTEGER)", TABLE_NAME_LSMH, KEY_ID, KEY_USER, KEY_ANHSP, KEY_TENSP, KEY_DONGIA, KEY_SOLUONG, KEY_THANHTIEN);

    public DbHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GIOHANG_TABLE);
        db.execSQL(CREATE_LSMH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_GIOHANG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LSMH);
        onCreate(db);
    }

    @SuppressLint("SetTextI18n")
    public void addSanPham(GioHang gioHang) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER, gioHang.getUser());
        values.put(KEY_ANHSP, gioHang.getAnhSP());
        values.put(KEY_TENSP, gioHang.getTenSP());
        values.put(KEY_DONGIA, gioHang.getDongia());
        values.put(KEY_SOLUONG, gioHang.getSoluong());
        values.put(KEY_THANHTIEN, gioHang.thanhtien());

        db.insert(TABLE_NAME_GIOHANG, null, values);
        int tongTien = tongTienGioHang(MainActivity.user);
        db.close();
    }


    public void dropTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_GIOHANG;
        db.execSQL(DROP_USER_TABLE);
    }

    public void deleteAllSanPham(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_GIOHANG, null, null);
        db.close();
    }

    public int getUserCountGioHang() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME_GIOHANG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public void createDefaultGioHangIfNeed()  {
        int count = this.getUserCountGioHang();
        if(count ==0 ) {
            GioHang gh1 = new GioHang("Haidz","bachibomy","Ba chỉ bò Mỹ - 0,5KG",119000,2);
            GioHang gh2 = new GioHang("Haidz","bachibomy","Ba chỉ bò Mỹ - 0,5KG",119000,2);
            this.addSanPham(gh1);
            this.addSanPham(gh2);
        }
    }
    public List<GioHang> getSanPhamTheoUser(String user) {
        List<GioHang> SPList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_GIOHANG + " WHERE " + KEY_USER + " = '" + user + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            GioHang gioHang = new GioHang(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5));
            SPList.add(gioHang);
            cursor.moveToNext();
        }
        return SPList;
    }

    public void CopyDuLieu(String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_GIOHANG + " WHERE " + KEY_USER + " = '" + userName + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            @SuppressLint("Range") String user = cursor.getString(cursor.getColumnIndex(KEY_USER));
            @SuppressLint("Range") String anhSP = cursor.getString(cursor.getColumnIndex(KEY_ANHSP));
            @SuppressLint("Range") String tenSP = cursor.getString(cursor.getColumnIndex(KEY_TENSP));
            @SuppressLint("Range") int dongia = cursor.getInt(cursor.getColumnIndex(KEY_DONGIA));
            @SuppressLint("Range") int soluong = cursor.getInt(cursor.getColumnIndex(KEY_SOLUONG));
            @SuppressLint("Range") int thanhtien = cursor.getInt(cursor.getColumnIndex(KEY_THANHTIEN));

            values.put(KEY_USER, user);
            values.put(KEY_ANHSP, anhSP);
            values.put(KEY_TENSP, tenSP);
            values.put(KEY_DONGIA, dongia);
            values.put(KEY_SOLUONG, soluong);
            values.put(KEY_THANHTIEN, thanhtien);
            db.insert(TABLE_NAME_LSMH, null, values);
        }
        cursor.close();
        db.close();
    }


    @SuppressLint("SetTextI18n")
    public void deleteSanPham(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_GIOHANG, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
        int tongTien = tongTienGioHang(MainActivity.user);
        GioHangActivity.tongTien.setText("Tổng tiền thanh toán: "+tongTien+"đ");
    }

    public void deleteSanPhamTheoUser(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_GIOHANG, KEY_USER + " = ?", new String[] { user });
        db.close();
    }

    public boolean checkSP(String userValue, String tenSPValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {KEY_USER, KEY_TENSP};
        String selection = KEY_USER +" = ? AND " +KEY_TENSP+ " = ?";
        String[] selectionArgs = {userValue, tenSPValue};
        Cursor cursor = db.query(TABLE_NAME_GIOHANG, columns, selection, selectionArgs, null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    @SuppressLint("SetTextI18n")
    public void updateSoluongSP(GioHang gioHang, int soluongMoi) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Tìm kiếm sản phẩm theo tên sản phẩm và tên người dùng
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_GIOHANG + " WHERE " + KEY_TENSP + " = ? AND " + KEY_USER + " = ?",
                new String[] { gioHang.getTenSP(), gioHang.getUser() });

        if (cursor.moveToFirst()) {
            do {
                // Lấy thông tin sản phẩm từ Cursor
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                @SuppressLint("Range") String user = cursor.getString(cursor.getColumnIndex(KEY_USER));
                @SuppressLint("Range") String anhSP = cursor.getString(cursor.getColumnIndex(KEY_ANHSP));
                @SuppressLint("Range") String tenSP = cursor.getString(cursor.getColumnIndex(KEY_TENSP));
                @SuppressLint("Range") int dongia = cursor.getInt(cursor.getColumnIndex(KEY_DONGIA));
                @SuppressLint("Range") int soluong = cursor.getInt(cursor.getColumnIndex(KEY_SOLUONG));
                @SuppressLint("Range") int thanhtien = cursor.getInt(cursor.getColumnIndex(KEY_THANHTIEN));

                // Cập nhật số lượng mới và tính lại thành tiền
                if (gioHang.getTenSP().equals(tenSP) && gioHang.getUser().equals(user)) {
                    soluong = soluongMoi;
                    thanhtien = soluong * dongia;
                }

                // Cập nhật sản phẩm trong SQLite
                ContentValues values = new ContentValues();
                values.put(KEY_USER, user);
                values.put(KEY_ANHSP, anhSP);
                values.put(KEY_TENSP, tenSP);
                values.put(KEY_DONGIA, dongia);
                values.put(KEY_SOLUONG, soluong);
                values.put(KEY_THANHTIEN, thanhtien);
                db.update(TABLE_NAME_GIOHANG, values, KEY_ID + " = ?", new String[] { String.valueOf(id) });
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        int tongTien = tongTienGioHang(MainActivity.user);
        GioHangActivity.tongTien.setText("Tổng tiền thanh toán: "+tongTien+"đ");
    }


    public int tongTienGioHang(String user) {
        int tongTien = 0;
        String selectQuery = "SELECT SUM(" + KEY_THANHTIEN + ") FROM " + TABLE_NAME_GIOHANG +" WHERE " + KEY_USER + " = '" + user + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            tongTien = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return tongTien;
    }

    public void addSanPhamLSMH(LichSuMuaHang lichSuMuaHang) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER, lichSuMuaHang.getUser());
        values.put(KEY_ANHSP, lichSuMuaHang.getAnhSPLSMH());
        values.put(KEY_TENSP, lichSuMuaHang.getTenSPLSMH());
        values.put(KEY_DONGIA, lichSuMuaHang.getDongiaSPLSMH());
        values.put(KEY_SOLUONG, lichSuMuaHang.getSoluongSPLSMH());
        values.put(KEY_THANHTIEN, lichSuMuaHang.thanhtienSPLSMH());

        db.insert(TABLE_NAME_LSMH, null, values);
        db.close();
    }


    public void deleteAllSanPhamLSMH(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_LSMH, null, null);
        db.close();
    }

    public int getUserCountLSMH() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME_LSMH;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public void createDefaultGioHangLSMHIfNeed()  {
        int count = this.getUserCountLSMH();
        if(count ==0 ) {
            LichSuMuaHang ls1 = new LichSuMuaHang("Haidz","bachibomy","Ba chỉ bò Mỹ - 0,5KG",119000,2);
            LichSuMuaHang ls2 = new LichSuMuaHang("Haidz","bachiheo","Ba Chỉ Heo - 1KG",126000,1);
            this.addSanPhamLSMH(ls1);
            this.addSanPhamLSMH(ls2);
        }
    }
    public List<LichSuMuaHang> getSanPhamTheoUserSapXepTheoID(String user) {
        List<LichSuMuaHang> SPList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_LSMH + " WHERE " + KEY_USER + " = '" + user + "' ORDER BY " + KEY_ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            LichSuMuaHang lichSuMuaHang = new LichSuMuaHang(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5));
            SPList.add(lichSuMuaHang);
            cursor.moveToNext();
        }
        return SPList;
    }
}
