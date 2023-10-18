package com.example.baitaplon;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


@SuppressLint("ValidFragment")
public class Dialog extends DialogFragment {
    DbHelper2 dbHelper2;
    Context context;
    @SuppressLint("ValidFragment")
    public Dialog(Context context,DbHelper2 dbHelper2) {
        this.dbHelper2 = dbHelper2;
        this.context = context;
    }

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Bạn có muốn đặt hàng không?")
                .setMessage("Hãy chọn Đặt hàng để tiếp tục.");
        builder.setPositiveButton("Đặt hàng", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Thực hiện hành động khi khách hàng chọn Đặt hàng
                        dbHelper2.CopyDuLieu(MainActivity.user);
                        dbHelper2.deleteSanPhamTheoUser(MainActivity.user);
                        Toast.makeText(getActivity(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,LichSuMuaHangActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Suy nghĩ lại", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Thực hiện hành động khi khách hàng chọn Hủy
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

}

