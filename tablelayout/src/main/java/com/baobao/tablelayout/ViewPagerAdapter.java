package com.baobao.tablelayout;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Time:2019/6/11
 * Author:baobao
 * Description:viewPager的适配器
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    //保存fragment的集合
    private List<MyFragment> mFragments;
    public ViewPagerAdapter(FragmentManager fm, List<MyFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    /**
     * @param i 位置
     * @return  根据位置返回fragment对象
     */
    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    /**
     * @return 返回viewPager的总条数
     */
    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    /**
     * @param position  位置
     * @return  根据位置返回viewPager中fragment页面的标题，tabLayout会将标题显示
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //返回对应位置fragment的标题（创建fragment时已作为参数传递）
        return (CharSequence) mFragments.get(position).getArguments().get("title");
    }
}
