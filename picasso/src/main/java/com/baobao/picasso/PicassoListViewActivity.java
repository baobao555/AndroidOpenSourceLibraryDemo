package com.baobao.picasso;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PicassoListViewActivity extends Activity {

    private static final String[] IMAGES = new String[]{
            // Heavy images
            "https://pics5.baidu.com/feed/562c11dfa9ec8a1328ec1da59a82628ba1ecc003.jpeg?token=a92ef7db62d875ef45bd02387ecde373&s=162A67A15463BEEC5A3D911D03008013",
            "https://pics5.baidu.com/feed/0df3d7ca7bcb0a46a489363919e205206a60af89.jpeg?token=1b216b3b1b73c42252d427a0255814de&s=7300C9A1540204E85C0168BE03005012",
            "https://pics0.baidu.com/feed/71cf3bc79f3df8dc644d17eea090818f461028e7.jpeg?token=0fa8ec1646fe24c7c6d10649ece93499&s=0DA0EC10138746EA8AD8E0D50300C023",
            "https://pics5.baidu.com/feed/dc54564e9258d1094645cc9cbcd93fbb6d814d84.jpeg?token=befde88ac636aae445d7527aaf9310c4&s=40B22D73CA9750CAD3B43CDE030090E2",
            "https://pics7.baidu.com/feed/500fd9f9d72a6059c639d91d45b5c79f033bba16.jpeg?token=1b6b39e9e59a6245129b162b3da4e288&s=A3A86BA1441217D64671D08F0300E0C0",
            "https://pics6.baidu.com/feed/8cb1cb134954092309582d07cdcc220db3de492b.jpeg?token=d491c1896b3f018aa1ed177c167cee4d&s=5859A944FA71BFC63C5902B903008096",
            "https://pics1.baidu.com/feed/5243fbf2b2119313bf3317ade38cffd390238df0.jpeg?token=18aa1aca43d4c3fb735618d91bb37d4e&s=CB42DF144E303D98841C15430300E0E0",
            "https://pics1.baidu.com/feed/fd039245d688d43ffae37ea0f8aa211f0ff43b57.jpeg?token=782f25745adfa9b771816977a884215f&s=BD9E37D85C0C344D533075000300E0D0"
    };

    private ListView mLv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_list_view);
        mLv = findViewById(R.id.lv_main);
        mLv.setAdapter(new MyAdapter());
        mLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    //停止滑动或手指在屏幕上缓慢滑动时继续加载图片
                    Picasso.get().resumeTag("baobao");
                } else {
                    //快速滑动时暂停加载图片
                    Picasso.get().pauseTag("baobao");
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int position) {
            return IMAGES[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null) {
                convertView = View.inflate(PicassoListViewActivity.this,R.layout.item,null);
                viewHolder = new ViewHolder();
                viewHolder.iv = convertView.findViewById(R.id.iv_item);
                viewHolder.tv = convertView.findViewById(R.id.tv_item);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv.setText(position + "");
            Picasso.get().load(IMAGES[position])
                    .tag("baobao") //设置标记，实现快速滑动时暂停加载的功能
                    .into(viewHolder.iv);
            return convertView;
        }
    }

    static class ViewHolder{
        private TextView tv;
        private ImageView iv;
    }
}
