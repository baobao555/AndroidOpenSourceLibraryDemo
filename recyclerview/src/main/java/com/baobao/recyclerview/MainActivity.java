package com.baobao.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mBtnList;
    private Button mBtnGrid;
    private Button mBtnAdd;
    private Button mBtnRemove;
    private Button mBtnStyle;
    private RecyclerView mRv;
    private List<String> data;
    private MyAdapter3 mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mBtnList.setOnClickListener(this);
        mBtnGrid.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
        mBtnRemove.setOnClickListener(this);
        mBtnStyle.setOnClickListener(this);
    }

    private void initData() {
        //初始化数据
        data = new ArrayList<>();
        for (int i = 0;i < 100;i++){
            data.add("baobao" + i);
        }
        //设置适配器
        mAdapter = new MyAdapter3(data);
        mRv.setAdapter(mAdapter);
        //设置布局管理器
        mRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    private void initView() {
        mBtnList = findViewById(R.id.btn_list);
        mBtnGrid = findViewById(R.id.btn_grid);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnRemove = findViewById(R.id.btn_remove);
        mBtnStyle = findViewById(R.id.btn_style);
        mRv = findViewById(R.id.rv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.btn_list:
                //设置ListView样式的布局管理器
                mRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                break;
            case  R.id.btn_grid:
                //设置GridView样式的布局管理器
                mRv.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.HORIZONTAL,true));
                break;
            case  R.id.btn_add:
                //增加一条数据
                mAdapter.add(0,"baobao");
                break;
            case  R.id.btn_remove:
                //删除数据
                mAdapter.remove(0);
                break;
            case  R.id.btn_style:
                mRv.setAdapter(new MyAdapter2(this,data));
                break;
        }
    }
}
