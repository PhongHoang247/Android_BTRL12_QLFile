package com.phong.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.phong.baitaprenluyen12.R;
import com.phong.model.MyFile;

public class MyFileAdapter extends ArrayAdapter<MyFile> {
    Activity context;
    int resource;
    public MyFileAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = this.context.getLayoutInflater().inflate(this.resource,null);
        ImageView imgHinh = (ImageView) customView.findViewById(R.id.imgHinh);
        TextView txtTen = (TextView) customView.findViewById(R.id.txtTen);
        TextView txtMoTa = (TextView) customView.findViewById(R.id.txtMoTa);
        MyFile file = getItem(position);
        switch (file.getFileType())
        {
            case FOLDER:
                imgHinh.setImageResource(R.drawable.hinh4);
                break;
            case MP3:
                imgHinh.setImageResource(R.drawable.hinh1);
                break;
            case MP4:
                imgHinh.setImageResource(R.drawable.hinh2);
                break;
            case NOTSUPORT:
                imgHinh.setImageResource(R.drawable.hinh3);
                break;
                default:
                    imgHinh.setImageResource(R.drawable.hinh3);
                    break;
        }
        txtMoTa.setText(file.getDescripTion());//Nếu là Thư mục thì sẽ hiển thị có mấy tập tin con
        //Nếu là tập tin thì hiển thi dung lượng
        txtTen.setText(file.getDisPlay());
        return customView;
    }
}
