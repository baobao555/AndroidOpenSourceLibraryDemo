package com.baobao.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends Activity {

    private TextView mTvStickyResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mTvStickyResult = findViewById(R.id.tv_sticky_result);
    }

    //接收到粘性事件的回调方法（必须为public）
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void handleEvent(Event event){
        mTvStickyResult.setText(event.getData());
    }

    public void receiveStickyEvent(View view) {
        //注册eventbus后，仍然能接收注册前发送的粘性事件
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //判断是否已注册
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
