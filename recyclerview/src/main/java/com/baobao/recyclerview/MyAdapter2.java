package com.baobao.recyclerview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Time:2019/6/9
 * Author:baobao
 * Description:RecyclerView的适配器，使用多种布局样式
 */
public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

    private List<String> mData;
    private Context mContext;

    //定义一个ViewHolder，继承RecyclerView.ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            //将item的布局传入构造器
            super(itemView);
        }
    }

    public MyAdapter2(Context context , List<String> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //根据不同的布局类型加载布局
        View view;
        MyViewHolder viewHolder;
        if(viewType == 1) {
            view = View.inflate(mContext,R.layout.rv_item1,null);
            viewHolder = new MyViewHolder(view);
            viewHolder.tv = view.findViewById(R.id.tv_item1);
        }else {
            view = View.inflate(mContext,R.layout.rv_item2,null);
            viewHolder = new MyViewHolder(view);
            viewHolder.tv = view.findViewById(R.id.tv_item2);
        }
        //返回viewHolder对象
        return viewHolder;
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
     * 根据指定位置返回不同布局类型
     * @param position 位置
     * @return 布局的类型
     */
    @Override
    public int getItemViewType(int position) {
        //偶数返回类型1，奇数返回类型2
        if(position % 2 == 0) {
            return 1;
        }else {
            return 2;
        }
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
