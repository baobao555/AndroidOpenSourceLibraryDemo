package com.baobao.glide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends Activity {

    private ImageView mIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIv = findViewById(R.id.iv);
    }

    public void loadImage(View view) {
        String url = "http://n.sinaimg.cn/sports/transform/662/w650h812/20190611/37a6-hyeztys7119691.jpg";
        Glide.with(this).load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(mIv);
    }
}
