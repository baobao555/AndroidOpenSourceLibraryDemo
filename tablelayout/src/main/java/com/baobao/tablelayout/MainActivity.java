package com.baobao.tablelayout;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager mVp;
    private TabLayout mTbl;
    private List<MyFragment> mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建fragment集合
        mFragments = new ArrayList<>();
        for (int i = 0;i < 15;i++){
            //创建fragment实例，并传递页面标题和内容
            MyFragment fragment = MyFragment.newInstance("标题" + i,"页面内容" + i);
            mFragments.add(fragment);
        }

        mVp = findViewById(R.id.vp);
        //给viewPager设置适配器
        mVp.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),mFragments));
        mTbl = findViewById(R.id.tbl);
        //将viewPager与tabLayout关联
        mTbl.setupWithViewPager(mVp);

    }
}
