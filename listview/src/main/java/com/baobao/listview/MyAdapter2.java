package com.baobao.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Time:2019/6/15
 * Author:baobao
 * Description: ListView适配器的封装写法
 */
public class MyAdapter2 extends MyBaseAdapter<String> {


    public MyAdapter2(List<String> data) {
        super(data);
    }

    @Override
    protected MyBaseViewHolder setData2View(int position, View convertView, ViewGroup parent) {
        MyBaseViewHolder viewHolder = MyBaseViewHolder.get(convertView,parent,R.layout.item);
        TextView textView = viewHolder.getView(R.id.tv_item);
        textView.setText(data.get(position));
        return viewHolder;
    }
}
