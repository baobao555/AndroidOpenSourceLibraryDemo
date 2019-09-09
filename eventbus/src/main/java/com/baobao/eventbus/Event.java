package com.baobao.eventbus;

/**
 * Time:2019/6/12
 * Author:baobao
 * Description:事件的封装类，保存要传递的数据
 */
public class Event {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Event(String data) {
        this.data = data;
    }
}
