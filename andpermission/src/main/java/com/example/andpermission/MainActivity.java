package com.example.andpermission;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

/*
 * 部分中国产手机，由于系统原因，不会调用rationale()的回调方法，
 * 如果用户多次拒绝某一权限，那么该权限可能每次申请都会直接调用onDenied()的回调方法，
 * 因此建议直接在onDenied()的回调方法中提示用户没有该权限，让用户直接去设置中开启权限，
 * 然后走设置的逻辑即可。
 * */
public class MainActivity extends AppCompatActivity {

    private Button mBtn;
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = findViewById(R.id.button);
        mTv = findViewById(R.id.result);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AndPermission.hasPermissions(MainActivity.this, Permission.READ_SMS)) {
                    getSMS();//如果有权限
                }else {//如果没有权限
                    AndPermission.with(MainActivity.this)
                            .runtime().permission(Permission.READ_SMS)//申请权限
                            .rationale(new Rationale<List<String>>() {//用户拒绝后再次申请权限，需要给用户提提示
                                @Override
                                public void showRationale(Context context, List<String> data, final RequestExecutor executor) {
                                    // 这里使用一个Dialog提示用户权限的作用，并询问是否授权
                                    new AlertDialog.Builder(MainActivity.this)
                                            .setTitle("请设置读短信权限")
                                            .setMessage("您所使用的功能需要读短信权限，麻烦授权下哦")
                                            .setPositiveButton("授权", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // 如果用户点击授权
                                                    executor.execute();
                                                }
                                            }).setNegativeButton("残忍拒绝", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // 如果用户点击拒绝
                                            executor.cancel();
                                        }
                                    }).show();
                                }
                            })
                            .onGranted(new Action<List<String>>() {//如果用户同意授权
                                @Override
                                public void onAction(List<String> data) {
                                    getSMS();
                                }
                            }).onDenied(new Action<List<String>>() {//如果用户拒绝授权
                        @Override
                        public void onAction(List<String> data) {
                            //如果用户选择拒绝授权并且选中了下次不再提示
                            if(AndPermission.hasAlwaysDeniedPermission(MainActivity.this,data)) {
                                //启动设置项activity让用户手动授权
                                AndPermission.with(MainActivity.this)
                                        .runtime().setting().start(1);
                            }
                        }
                    }).start();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1) {//启动设置页面activity的请求码
           if(AndPermission.hasPermissions(this,Permission.READ_SMS)) {
               //用户在设置页面手动授权了
               getSMS();
           } else {
               Toast.makeText(MainActivity.this, "您拒绝开启权限，将无法使用功能", Toast.LENGTH_SHORT).show();
           }
        }
    }

    private void getSMS(){
        Uri uri = Uri.parse("content://sms/inbox");
        String[] projection = new String[]{"address","date"};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        mTv.setText("有" + cursor.getCount() + "条短信");
    }
}
