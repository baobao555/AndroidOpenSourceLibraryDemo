package com.example.permissiondemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mBtn;
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = findViewById(R.id.button);
        mTv = findViewById(R.id.result);
        mBtn.setOnClickListener((v) -> {
            //检查是否有权限
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS) ==
                    PackageManager.PERMISSION_GRANTED) { //已经有权限
                getSMS();
            }else { //如果没权限就申请
                //如果之前已经被用户拒绝过权限，弹出对话框解释说明权限的作用，试图说服用户申请
                if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_SMS)) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("请设置读短信权限")
                            .setMessage("您所使用的功能需要读短信权限，麻烦授权下哦")
                            .setPositiveButton("授权", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_SMS},1);
                                }
                            }).setNegativeButton("残忍拒绝", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
                }else {//首次申请权限
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_SMS},1);
                }

            }

        });
    }

    /**
     * @param requestCode 请求码
     * @param permissions 申请的权限
     * @param grantResults 用户授权的结果
     */
    //用户处理权限后的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户接受权限
                getSMS();
            }else {//用户拒绝权限
                Toast.makeText(MainActivity.this, "权限被拒绝", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void getSMS(){
        Uri uri = Uri.parse("content://sms/inbox");
        String[] projection = new String[]{"address","date"};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        mTv.setText("有" + cursor.getCount() + "条短信");
    }
}
