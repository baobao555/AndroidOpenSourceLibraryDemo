package com.baobao.volley;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private ImageView mResultIv;
    private NetworkImageView mResultNiv;
    private TextView mResultTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mResultIv = findViewById(R.id.iv_result);
        mResultNiv = findViewById(R.id.niv_result);
        mResultTv = findViewById(R.id.tv_result);
    }


    // get请求
    public void get(View view) {
        // 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // 2 创建一个请求
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        url = "https://www.baidu.com/";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            // 正确接收数据回调
            @Override
            public void onResponse(String s) {
                mResultTv.setText(s);
            }
        }, new Response.ErrorListener() { // 发生异常后的监听回调
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mResultTv.setText("请求出错" + volleyError);
            }
        });
        // 3 将创建的请求添加到请求队列中
        requestQueue.add(stringRequest);
    }

    // post请求
    public void post(View view) {// 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // 2 创建一个请求
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        url = "https://www.baidu.com/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            // 正确接收数据回调
            @Override
            public void onResponse(String s) {
                mResultTv.setText(s);
            }
        }, new Response.ErrorListener() { // 发生异常后的监听回调
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mResultTv.setText("请求出错" + volleyError);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //请求参数
                Map<String, String> map = new HashMap<String, String>();
//              map.put("value1","param1");
                return map;
            }
        };
        // 3 将创建的请求添加到请求队列中
        requestQueue.add(stringRequest);
    }

    // json请求
    public void json(View view) {
        // 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // 2 创建一个请求
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        //url = "https://www.baidu.com/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                mResultTv.setText(jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mResultTv.setText("请求出错" + volleyError);
            }
        });
        // 3 将创建的请求添加到请求队列中
        requestQueue.add(jsonObjectRequest);
    }

    // imagerequest加载图片
    public void imageRequest(View view) {
        // 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // 2 创建一个图片的请求
        String url = "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3126401414,4293306338&fm=173&app=49&f=JPEG?w=218&h=146&s=A89061954A731592168514A00300E0D0";
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                mResultIv.setVisibility(View.VISIBLE);
                mResultIv.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        // 3 将请求添加到请求队列中
        requestQueue.add(imageRequest);

    }

    // imageloader加载图片
    public void imageLoader(View view) {
        // 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // 2 创建一个imageloader
        String url = "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3126401414,4293306338&fm=173&app=49&f=JPEG?w=218&h=146&s=A89061954A731592168514A00300E0D0";
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return null;
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {

            }
        };
        ImageLoader imageLoader =  new ImageLoader(requestQueue,imageCache);
        // 3 加载图片
        mResultIv.setVisibility(View.VISIBLE);
        ImageLoader.ImageListener imageListener = imageLoader.getImageListener(mResultIv,0,0);
        imageLoader.get(url, imageListener);
    }

    // networkimageview加载图片
    public void networkImageView(View view) {
        // 让控件显示
        mResultNiv.setVisibility(View.VISIBLE);
        // 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
        // 创建一个Imageloader
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return null;
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {

            }
        });
        // 加载图片
        mResultNiv.setImageUrl(url,imageLoader);
    }
}
