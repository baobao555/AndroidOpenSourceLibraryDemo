package com.baobao.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Time:2019/6/16
 * Author:baobao
 * Description:recyclerView的适配器封装类
 */
public abstract class MyBaseAdapter<T> extends RecyclerView.Adapter<MyBaseAdapter.ViewHolder> {
    protected List<T> mData;//数据

    public MyBaseAdapter(List<T> data){
        //设置数据
        mData = data;
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * ViewHolder的封装
     */
    static class ViewHolder extends RecyclerView.ViewHolder{
        private SparseArray<View> views;//保存itemView所有子控件的容器
        private View itemView;//recyclerView的item的View对象
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            //初始化容器
            views = new SparseArray<>();
        }

        /**
         * 得到某个子控件的对象
         * @param viewID  子控件ID
         * @param <V>  子控件类型，泛型表示
         * @return  子控件对象
         */
        public<V extends View> V getView(int viewID){
            //从容器中得到对应的子控件对象
            View view = views.get(viewID);
            if(view == null) {
                //如果找不到子控件，从itemView中得到子控件并存到容器中
                view = itemView.findViewById(viewID);
                views.put(viewID,view);
            }
            return (V) view;
        }
    }
}
