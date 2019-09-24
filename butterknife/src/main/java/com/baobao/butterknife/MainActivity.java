package com.baobao.butterknife;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    //绑定控件(控件不能声明为private或static)
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.cb)
    CheckBox mCb;
    @BindView(R.id.lv_main)
    ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定activity
        ButterKnife.bind(this);
        mTv.setText("baobao");
        mLv.setAdapter(new MyAdapter());
    }

    //批量绑定控件的点击事件
    @OnClick({R.id.btn, R.id.cb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                Toast.makeText(MainActivity.this, "button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cb:
                Toast.makeText(MainActivity.this, "check box", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null) {
                convertView = View.inflate(MainActivity.this,R.layout.item,null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv.setText("baobao" + position);
            viewHolder.btn.setText("baobao" + position);
            viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "baobao" + position, Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }
    }

    static class ViewHolder{
        @BindView(R.id.tv_item)
        TextView tv;
        @BindView(R.id.btn_item)
        Button btn;
        public ViewHolder(View view){
            ButterKnife.bind(this,view); //将butterknife绑定viewholder的视图
        }
    }
}
