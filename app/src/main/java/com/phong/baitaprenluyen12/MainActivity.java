package com.phong.baitaprenluyen12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.phong.adapter.MyFileAdapter;
import com.phong.model.FileSupport;
import com.phong.model.MyFile;
import com.phong.util.Converter;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ListView lvMyFile;
    MyFileAdapter myFileAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

        readFolderFromSDCard();
    }

    private void readFolderFromSDCard() {
        //Cấp quyền để đọc SD Card và lấy đường dẫn SD Card:
        //Lấy thư mục gốc của SD Card:
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        //Intent: Đệ quy
        Intent intentParent = getIntent();
        if (intentParent != null)//Đang muốn mở thư mục con
        {
            if (intentParent.hasExtra("PARENT"))//Lưu đường dẫn của Cha để lấy Con
            {
                root = intentParent.getStringExtra("PARENT");
            }
        }
        //Lấy tập tin của Android:
        File f = new File(root);//Trả về tập các thư mục và tập tin con của nó
        //Lấy toàn bộ tập tin và thư mục con:
        File dsFileOrFolder[] = f.listFiles();
        myFileAdapter.clear();//Xoá màn hình cũ đi
        //Kiểm tra xem là thư mục hay tệp tin:
        for (File fileOrFolder : dsFileOrFolder)
        {
            MyFile mf = new MyFile();
            mf.setPath(fileOrFolder.getAbsolutePath());
            mf.setDisPlay(fileOrFolder.getName());
            if (fileOrFolder.isDirectory())//Kiểm tra thư mục:
            {
                mf.setFileType(FileSupport.FOLDER);
                mf.setDescripTion("Có " + fileOrFolder.list().length + " tập tin con");//.list là trả về toàn bộ thư mục con và tập tin con, length là số lượng
                mf.setHasChild(true);
            }
            else //Kiểm tra tập tin:
            {
                if (fileOrFolder.getPath().toLowerCase().endsWith(".mp3"))
                {
                    mf.setFileType(FileSupport.MP3);
                }
                else if (fileOrFolder.getPath().toLowerCase().endsWith(".mp4"))
                {
                    mf.setFileType(FileSupport.MP4);
                }
                else
                {
                    mf.setFileType(FileSupport.NOTSUPORT);
                }
                //Mô tả:
                String s = Converter.humanReadableByteCount(fileOrFolder.length(),true);//length trả về dung lượng dạng byte
                mf.setDescripTion(s);
            }
            myFileAdapter.add(mf);
        }
    }

    private void addEvents() {
        lvMyFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyFile mf = myFileAdapter.getItem(i);
                XuLyMoFile(mf);
            }
        });
    }

    private void XuLyMoFile(MyFile mf) {
        if (mf.getFileType() == FileSupport.NOTSUPORT)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Thông báo!");
            builder.setMessage("File chưa hỗ trợ");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.show();
        }
        else
        {
            if (mf.getFileType() == FileSupport.FOLDER)
            {
                if (mf.isHasChild())//Kiểm tra xem nó có thư mục con hay tập tin con
                {
                    //GỌi đệ quy:
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent .putExtra("PARENT",mf.getPath());
                    startActivity(intent);
                }
            }
            else if (mf.getFileType() != FileSupport.NOTSUPORT)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(mf.getPath());
                intent.setData(uri);
                startActivity(intent);
            }
        }
    }

    private void addControls() {
        lvMyFile = (ListView) findViewById(R.id.lvMyFile);
        myFileAdapter = new MyFileAdapter(MainActivity.this, R.layout.item);
        lvMyFile.setAdapter(myFileAdapter);
    }
}
