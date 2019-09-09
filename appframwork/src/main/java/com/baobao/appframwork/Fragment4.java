package com.baobao.appframwork;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Time:2019/6/15
 * Author:baobao
 * Description:我的ragment
 */
public class Fragment4 extends BaseFragment {
    private TextView textView;
    @Override
    protected View initView() {
        textView = new TextView(context);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    protected void initData() {
        textView.setText("我的内容");
    }
}
