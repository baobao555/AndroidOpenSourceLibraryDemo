package com.baobao.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends Activity {

    private TextView mTvNormalResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvNormalResult = findViewById(R.id.tv_normal_result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册eventbus，成为事件订阅者
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //解注册eventbus
        EventBus.getDefault().unregister(this);
    }

    //接收到事件的回调方法（必须为public）
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(Event event){
        //设置接收到的数据
        mTvNormalResult.setText(event.getData());
    }

    public void normalEvent(View view) {
        //创建事件对象，携带数据
        Event event = new Event("我是eventBus普通事件");
        //发送事件
        EventBus.getDefault().post(event);
    }

    public void stickyEvent(View view) {
        Event event = new Event("我是eventBus粘性事件");
        //发送粘性事件
        EventBus.getDefault().postSticky(event);
        //启动另一个activity
        startActivity(new Intent(this,SecondActivity.class));
    }
}
