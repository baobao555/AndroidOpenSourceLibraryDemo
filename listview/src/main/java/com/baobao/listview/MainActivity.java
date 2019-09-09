package com.baobao.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView mLvMain;
    private List<String> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLvMain = findViewById(R.id.lv_main);
        mData = new ArrayList<>();
        for (int i=0;i<100;i++){
            mData.add("baobao" + i);
        }
        mLvMain.setAdapter(new MyAdapter2(mData));
    }
}
