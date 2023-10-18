package com.example.baitaplon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterSach extends BaseAdapter {
    private List<Sach> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapterSach(Context context, List<Sach> listData) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_lsv_sach, null);
            holder = new ViewHolder();
            holder.anhsp = (ImageView) convertView.findViewById(R.id.imageView_Sach);
            holder.tensp = (TextView) convertView.findViewById(R.id.txt_tenSach);
            holder.dongia = (TextView) convertView.findViewById(R.id.txt_dongia);
            holder.btnDatMua = (Button) convertView.findViewById(R.id.btn_DatMua);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Sach sach = this.listData.get(position);
        holder.tensp.setText(sach.getTen());
        holder.dongia.setText("Đơn giá: " + sach.getDongia() +"đ");
        int imageId = this.getMipmapResIdByName(sach.getAnhsp());
        holder.anhsp.setImageResource(imageId);

        holder.btnDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InforSach.class);
                intent.putExtra("KeyTenSP",sach.getTen());
                intent.putExtra("KeyDonGia",String.valueOf(sach.getDongia()));
                intent.putExtra("KeyAnhSP",imageId);
                intent.putExtra("KeyTenAnhSP",sach.getAnhsp());
                context.startActivity(intent);
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
        ImageView anhsp;
        TextView tensp;
        TextView dongia;
        Button btnDatMua;
    }



}
