package com.baobao.listview;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Time:2019/6/15
 * Author:baobao
 * Description:ViewHolder抽取类
 */
public class MyBaseViewHolder {
    private View mConvertView;//保存关联的convertView
    private SparseArray<View> mViews;//保存convertView中的所有子控件

    /**
     * viewHolder构造方法
     * @param parent ListView对象
     * @param layoutID 需要加载的ListView的item的布局ID
     */
    private MyBaseViewHolder(ViewGroup parent, int layoutID){
        //加载布局
        mConvertView = LayoutInflater.from(parent.getContext()).inflate(layoutID,null);
        //将mConvertView与viewHolder关联
        mConvertView.setTag(this);
        //初始化保存convertView中所有子控件的容器
        mViews = new SparseArray<>();
    }

    /**
     * 得到viewHolder对象
     * @param convertView baseAdapter的getView方法传入的convertView对象
     * @param parent      baseAdapter的getView方法传入的ListView对象
     * @param layoutID    需要加载的ListView的item的布局ID
     * @return
     */
    public static MyBaseViewHolder get(View convertView, ViewGroup parent, int layoutID) {

        if(convertView == null) {
            //如果convertView为空，创建新的viewHolder实例
            return new MyBaseViewHolder(parent,layoutID);
        }else {
            //直接返回与convertView关联的viewHolder
            return (MyBaseViewHolder) convertView.getTag();
        }
    }

    /**
     * 得到viewHolder中的某个子控件
     * @param viewID  子控件的ID
     * @param <T>    子控件的类型，泛型表示
     * @return 子控件对象
     */
    public <T extends View> T getView(int viewID){
        //从容器中取出对应id的子控件
        View view = mViews.get(viewID);
        if(view == null) {
            //如果找不到，则将viewID作为key添加到容器中
            view = mConvertView.findViewById(viewID);
            mViews.put(viewID, view);
        }
        //返回子控件对象
        return (T) view;
    }

    /**
     * @return 返回convertView
     */
    public View getConvertView(){
        return mConvertView;
    }
}
