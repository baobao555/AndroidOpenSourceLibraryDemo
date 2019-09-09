package com.baobao.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class MainActivity extends Activity {

    private TextView mTvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvResult = findViewById(R.id.tv_result);


    }

    //GET请求
    public void get(View view) {
        //1.创建retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com")  //指定baseurl
                .build();

        //2.创建请求接口的代理对象，封装了请求的各种参数
        IRequest1 getRequest = retrofit.create(IRequest1.class);

        //3.调用请求接口的方法获得Call对象(如果得到的结果只需要字符串，泛型不能传String，默认要传ResponseBody)
        Call<ResponseBody> call = getRequest.getResult();

        //4.异步执行请求
        call.enqueue(new Callback<ResponseBody>() {

            //请求成功回调
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    mTvResult.setText(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //请求失败回调
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mTvResult.setText(t.toString());
            }
        });
    }

    //POST请求
    public void post(View view) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.baidu.com/").build();
        IRequest2 postRequest = retrofit.create(IRequest2.class);
        Call<ResponseBody> call = postRequest.getResult();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    mTvResult.setText(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mTvResult.setText(t.toString());
            }
        });
    }

    /**
     * GET请求对应的接口
     */
    public interface IRequest1{
        @GET("/")
        Call<ResponseBody> getResult();
    }


    /**
     * POST请求对应的接口
     */
    public interface IRequest2{
        @POST("/")
        Call<ResponseBody> getResult();
    }
}
