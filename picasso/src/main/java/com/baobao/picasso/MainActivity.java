package com.baobao.picasso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mSimpleBtn;
    private Button mListViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        mSimpleBtn.setOnClickListener(this);
        mListViewBtn.setOnClickListener(this);
    }

    private void initView() {
        mSimpleBtn = findViewById(R.id.btn_main_simple);
        mListViewBtn = findViewById(R.id.btn_main_listview);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case  R.id.btn_main_simple:
                intent = new Intent(this,PicassoSimpleActivity.class);
                startActivity(intent);
                break;
            case  R.id.btn_main_listview:
                intent = new Intent(this,PicassoListViewActivity.class);
                startActivity(intent);
                break;
        }
    }
}
