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
 * Description: ListView适配器的一般写法
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;
    public MyAdapter(Context context, List<String> data){
        this.context = context;
        if(data == null) {
            this.data = new ArrayList<>();
            for (int i=0;i<100;i++){
                this.data.add("baobao" + i);
            }
        }else {
            this.data = data;
        }

    }
    @Override
    public int getCount() {
        return data.size();
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
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = View.inflate(context,R.layout.item,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.tv_item);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(data.get(position));
        return convertView;
    }

    static class ViewHolder{
        TextView textView;
    }
}
