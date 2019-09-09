package com.baobao.picasso;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoSimpleActivity extends Activity {

    private Button mLoadBtn;
    private ImageView mIv1;
    private ImageView mIv2;
    private ImageView mIv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_simple);
        initView();
    }

    private void initView() {
        mLoadBtn = findViewById(R.id.btn_load);
        mIv1 = findViewById(R.id.iv1);
        mIv2 = findViewById(R.id.iv2);
        mIv3 = findViewById(R.id.iv3);

        mLoadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImageByPicasso();
            }
        });
    }

    private void loadImageByPicasso() {
        String url = "https://pics5.baidu.com/feed/562c11dfa9ec8a1328ec1da59a82628ba1ecc003.jpeg?token=a92ef7db62d875ef45bd02387ecde373&s=162A67A15463BEEC5A3D911D03008013";
        Picasso.get() //得到Picasso实例
                .load(url) //加载网络图片
                .into(mIv1); //将图片显示到imageView

        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_launcher_background) //加载过程中的占位图片
                .error(R.drawable.ic_launcher_background)  //加载出错显示的图片
                .resize(100,100)  //设置图片尺寸
                .into(mIv2);

        Picasso.get()
                .load(url)
                .rotate(180) //旋转图片
                .into(mIv3);
    }
}
