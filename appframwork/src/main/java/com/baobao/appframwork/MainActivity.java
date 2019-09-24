package com.baobao.appframwork;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private RadioGroup mRgBottom;  //底部的radioGroup
    private List<BaseFragment> mFragments; //fragment集合
    private int mPreFragment = -1; //上一次选择的fragment的索引
    private int mCurFragment = -1; //当前选择的fragment的索引
    private FragmentManager mFragmentManager; //FragmentManager
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }

    private void initData() {
        mFragmentManager = getSupportFragmentManager();
        mFragments = new ArrayList<>();
        mFragments.add(new Fragment1());
        mFragments.add(new Fragment2());
        mFragments.add(new Fragment3());
        mFragments.add(new Fragment4());
        mRgBottom.check(R.id.rb1);
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        //设置radioGroup选择状态改变监听
        mRgBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //根据不同的选择设置当前fragment的索引
                switch (checkedId) {
                    case  R.id.rb1:
                        mCurFragment = 0;
                        break;
                    case  R.id.rb2:
                        mCurFragment = 1;
                        break;
                    case  R.id.rb3:
                        mCurFragment = 2;
                        break;
                    case  R.id.rb4:
                        mCurFragment = 3;
                        break;
                }
                //切换显示fragment
                switchFragment(mPreFragment,mCurFragment);
                //更新上次选择fragment的索引值
                mPreFragment = mCurFragment;
            }
        });
    }

    /**
     * 切换fragment
     * @param preFragmentIndex  上一次选择的fragment的索引
     * @param curFragmentIndex  当前选择的fragment的索引
     */
    private void switchFragment(int preFragmentIndex, int curFragmentIndex) {
        //开启fragment事务
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        //刚进入界面，第一次选择fragment
        if(preFragmentIndex < 0) {
            //将默认显示的fragment1添加并显示
            fragmentTransaction.add(R.id.fl_main,mFragments.get(0)).commit();
            return;
        }
        //如果当前选择的fragment与上一次相同，直接返回
        if(preFragmentIndex == curFragmentIndex) {
            return;
        }

        //得到上次选择的fragment对象
        BaseFragment preFragment = mFragments.get(preFragmentIndex);
        //得到当前选择的fragment对象
        BaseFragment curFragment = mFragments.get(curFragmentIndex);

        //隐藏上次选择的fragment对象
        fragmentTransaction.hide(preFragment);


        if(!curFragment.isAdded()) {
            //如果当前选择的fragment对象还未添加，则添加
            fragmentTransaction.add(R.id.fl_main,curFragment);
        }else {
            //如果当前选的fragment已添加，则显示
            fragmentTransaction.show(curFragment);
        }
        //提交事务
        fragmentTransaction.commit();
    }

    /**
     * 初始化视图控件
     */
    private void initView() {
        mRgBottom = findViewById(R.id.rg_bottom);
    }
}
