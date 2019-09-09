package com.baobao.okgo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {

    private Button mBtnGet;
    private Button mBtnPost;
    private TextView mTvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mBtnGet = findViewById(R.id.btn_get);
        mBtnPost = findViewById(R.id.btn_post);
        mTvResult = findViewById(R.id.tv_result);
    }

    private void initListener() {
        final String url = "https://www.baidu.com";
        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkGo.<String>get(url)  //GET请求
                        .tag(this)     //设置标记，后续取消请求时用到
                        .execute(new StringCallback() { //执行请求
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        mTvResult.setText(response.body());
                    }
                });
            }
        });

        mBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkGo.<String>post(url)  //POST请求
                        .tag(this)
                        .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        mTvResult.setText(response.body());
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消请求
        OkGo.getInstance().cancelTag(this);
    }
}
