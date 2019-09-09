package com.baobao.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Time:2019/6/9
 * Author:baobao
 * Description:RecyclerView的适配器
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<String> mData;
    private Context mContext;

    //定义一个ViewHolder，继承RecyclerView.ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            //将item的布局传入构造器
            super(itemView);
            //绑定viewHolder与view控件
            tv = itemView.findViewById(R.id.tv_item1);
        }
    }

    public MyAdapter(Context context ,List<String> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //加载布局
        View view = View.inflate(mContext,R.layout.rv_item1,null);
        //创建viewHolder对象
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        //给viewHolder绑定的控件设置数据
        myViewHolder.tv.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        //返回数据长度
        return mData == null ? 0 : mData.size();
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
