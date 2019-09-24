package com.example.filedirectorydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView1);
        String path1 = Environment.getDataDirectory().getAbsolutePath(); //  /data
        String path2 = getFilesDir().getAbsolutePath(); // /data/user/0/com.example.filedirectorydemo/files
        String path3 = Environment.getExternalStorageDirectory().getAbsolutePath(); // /storage/emulated/0
        String path4 = getExternalFilesDir(null).getAbsolutePath(); // /storage/emulated/0/Android/data/com.example.filedirectorydemo/files
        String path5 = Environment.getRootDirectory().getAbsolutePath(); // /system

    }
}
