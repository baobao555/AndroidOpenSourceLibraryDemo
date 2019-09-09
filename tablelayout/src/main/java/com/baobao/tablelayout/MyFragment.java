package com.baobao.tablelayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Time:2019/6/11
 * Author:baobao
 * Description:自定义fragment
 */
public class MyFragment extends Fragment {

    private Context mContext;//上下文
    private TextView mTv;//fragment的view


    /**
     * 创建fragment实例，并传递参数
     * @param title  页面标题
     * @param pageContent 页面内容
     * @return fragment实例
     */
    public static MyFragment newInstance(String title,String pageContent){
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        bundle.putString("pageContent",pageContent);
        //将参数传递给fragment
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //保存上下文
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //创建view
        mTv = new TextView(mContext);
        mTv.setGravity(Gravity.CENTER);
        mTv.setTextSize(30);
        return mTv;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置view的数据
        mTv.setText(getArguments().getString("pageContent"));
    }
}
