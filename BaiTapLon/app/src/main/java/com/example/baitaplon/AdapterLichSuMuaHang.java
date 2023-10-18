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

public class AdapterLichSuMuaHang extends BaseAdapter {
    private List<LichSuMuaHang> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapterLichSuMuaHang(Context context, List<LichSuMuaHang> listData) {
        this.context = context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
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
        AdapterLichSuMuaHang.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_lsv_lichsumuahang, null);
            holder = new AdapterLichSuMuaHang.ViewHolder();
            holder.anhspLSMH = (ImageView) convertView.findViewById(R.id.imageView_anhSPLSMH);
            holder.tenspLSMH = (TextView) convertView.findViewById(R.id.tv_tenSPLSMH);
            holder.dongiaspLSMH = (TextView) convertView.findViewById(R.id.tv_dongiaSPLSMH);
            holder.soluongspLSMH = (TextView) convertView.findViewById(R.id.tv_soluongSPLSMH);
            holder.thanhtienspLSMH = (TextView) convertView.findViewById(R.id.tv_thanhtienSPLSMH);
            convertView.setTag(holder);
        } else {
            holder = (AdapterLichSuMuaHang.ViewHolder) convertView.getTag();
        }

        LichSuMuaHang lichSuMuaHang = this.listData.get(position);
        holder.tenspLSMH.setText(lichSuMuaHang.getTenSPLSMH());
        holder.soluongspLSMH.setText("Số lượng: "+lichSuMuaHang.getSoluongSPLSMH());
        holder.dongiaspLSMH.setText("Đơn giá: "+lichSuMuaHang.getDongiaSPLSMH() + "đ");
        int imageId = this.getMipmapResIdByName(lichSuMuaHang.getAnhSPLSMH());
        holder.anhspLSMH.setImageResource(imageId);
        holder.thanhtienspLSMH.setText("Thành tiền: "+lichSuMuaHang.thanhtienSPLSMH()+"đ");
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
        ImageView anhspLSMH;
        TextView tenspLSMH,dongiaspLSMH,soluongspLSMH,thanhtienspLSMH;
    }
}
