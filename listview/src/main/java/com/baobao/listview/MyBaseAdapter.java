package com.baobao.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Time:2019/6/15
 * Author:baobao
 * Description:ListView适配器的封装类
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    protected List<T> data;//数据

    public MyBaseAdapter(List<T> data){
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //得到viewHolder，并将设置数据
        MyBaseViewHolder viewHolder = setData2View(position,convertView,parent);
        //返回view对象
        return viewHolder.getConvertView();
    }

    //抽象方法，子类实现
    protected abstract MyBaseViewHolder setData2View(int position,View convertView, ViewGroup parent);
}
