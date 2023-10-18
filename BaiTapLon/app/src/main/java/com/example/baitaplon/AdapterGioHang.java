package com.example.baitaplon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdapterGioHang extends BaseAdapter {
    private SQLiteDatabase db;
    private DbHelper2 dbHelper2;
    private List<GioHang> listData;
    private Context context;
    public AdapterGioHang(Context context, List<GioHang> listData) {
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
        AdapterGioHang.ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lsv_giohang, parent, false);
            holder = new AdapterGioHang.ViewHolder();
            holder.anhSPGioHang = (ImageView) convertView.findViewById(R.id.anhSP_GioHang);
            holder.XoaSPGioHang = (ImageView) convertView.findViewById(R.id.XoaSP_GioHang);
            holder.tenSPGioHang = (TextView) convertView.findViewById(R.id.tenSP_GioHang);
            holder.dongiaSPGioHang = (TextView) convertView.findViewById(R.id.dongia_GioHang);
            holder.thanhtienSPGioHang = (TextView) convertView.findViewById(R.id.thanhtien_GioHang);
            holder.numberPickerGioHang = (NumberPicker) convertView.findViewById(R.id.number_picker_GioHang);
            convertView.setTag(holder);
        } else {
            holder = (AdapterGioHang.ViewHolder) convertView.getTag();
        }

        GioHang gioHang = this.listData.get(position);
        holder.tenSPGioHang.setText(gioHang.getTenSP());
        holder.dongiaSPGioHang.setText(gioHang.getDongia() +"đ");
        holder.thanhtienSPGioHang.setText(gioHang.thanhtien()+"đ");
        int imageId = this.getMipmapResIdByName(gioHang.getAnhSP());
        holder.anhSPGioHang.setImageResource(imageId);

        holder.numberPickerGioHang.setMinValue(1);
        holder.numberPickerGioHang.setMaxValue(10);
        holder.numberPickerGioHang.setValue(gioHang.getSoluong());

        holder.numberPickerGioHang.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                String user = MainActivity.user;
                String tenAnhSP = gioHang.getAnhSP();
                String tenSP = gioHang.getTenSP();
                int dongia = gioHang.getDongia();
                dbHelper2.updateSoluongSP(new GioHang(user,tenAnhSP,tenSP,dongia,oldVal),newVal);
                listData.set(position,new GioHang(user,tenAnhSP,tenSP,dongia,newVal));
                notifyDataSetChanged();
            }
        });

        holder.XoaSPGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int SPId = gioHang.getId();
                dbHelper2.deleteSanPham(SPId);
                listData.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
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
        ImageView anhSPGioHang,XoaSPGioHang;
        TextView tenSPGioHang,dongiaSPGioHang,thanhtienSPGioHang;
        NumberPicker numberPickerGioHang;
    }

}
