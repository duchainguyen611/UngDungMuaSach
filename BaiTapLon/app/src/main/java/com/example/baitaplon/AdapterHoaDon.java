package com.example.baitaplon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class AdapterHoaDon extends BaseAdapter {
    private SQLiteDatabase db;
    private DbHelper2 dbHelper2;
    private List<GioHang> listData;

    private Context context;

    public AdapterHoaDon(Context context, List<GioHang> listData) {
        this.context = context;
        this.listData = listData;
        dbHelper2 = new DbHelper2(context);
        db = dbHelper2.getWritableDatabase();
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterHoaDon.ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lsv_hoadon, parent, false);
            holder = new AdapterHoaDon.ViewHolder();
            holder.anhSPHoaDon = (ImageView) convertView.findViewById(R.id.imageView_anhSPHoaDon);
            holder.tenSPHoaDon = (TextView) convertView.findViewById(R.id.txt_tenSPHoaDon);
            holder.dongiaSPHoaDon = (TextView) convertView.findViewById(R.id.txt_DonGiaSPHoaDon);
            holder.soluongSPHoaDon = (TextView) convertView.findViewById(R.id.txt_SoLuongSPHoaDon);
            holder.thanhtienSPHoaDon = (TextView) convertView.findViewById(R.id.txt_ThanhTienSPHoaDon);
            convertView.setTag(holder);
        } else {
            holder = (AdapterHoaDon.ViewHolder) convertView.getTag();
        }

        GioHang gioHang = this.listData.get(position);
        holder.tenSPHoaDon.setText(gioHang.getTenSP());
        holder.dongiaSPHoaDon.setText("Đơn giá: "+gioHang.getDongia() +"đ");
        holder.soluongSPHoaDon.setText("Số lượng: "+gioHang.getSoluong()+"");
        holder.thanhtienSPHoaDon.setText("Thành tiền: "+gioHang.thanhtien()+"đ");
        int imageId = this.getMipmapResIdByName(gioHang.getAnhSP());
        holder.anhSPHoaDon.setImageResource(imageId);

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView anhSPHoaDon;
        TextView tenSPHoaDon,dongiaSPHoaDon,thanhtienSPHoaDon,soluongSPHoaDon;
    }
}
