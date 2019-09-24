package com.baobao.recyclerview;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Time:2019/6/9
 * Author:baobao
 * Description:RecyclerView的适配器封装写法
 */
public class MyAdapter3 extends MyBaseAdapter<String> {

    public MyAdapter3(List<String> data) {
        super(data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //根据viewType加载相应的布局，并创建viewHolder对象
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item1,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //从viewHolder中找到子控件并设置数据
        TextView textView = viewHolder.getView(R.id.tv_item1);
        textView.setText(mData.get(position));
    }

    /**
     * 指定位置增加数据
     * @param position 位置
     * @param data  数据
     */
    public void add(int position, String data) {
        //源数据上增加数据
        mData.add(position,data);
        //通知指定位置数据已增加
        notifyItemInserted(position);
    }

    /**
     * 指定位置删除数据
     * @param position  位置
     */
    public void remove(int position) {
        //源数据上删除
        mData.remove(position);
        //通知指定位置数据已删除
        notifyItemRemoved(position);
    }



}
