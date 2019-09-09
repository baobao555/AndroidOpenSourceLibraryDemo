package com.baobao.gson;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private TextView mTvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvResult = findViewById(R.id.tv_result);
    }

    public void jsonToClass(View view) {
        String json = "{\n" +
                "  \"title\": \"Java Puzzlers: Traps, Pitfalls, and Corner Cases\",\n" +
                "  \"isbn-10\": \"032133678X\",\n" +
                "  \"isbn-13\": \"978-0321336781\",\n" +
                "  \"authors\": [\n" +
                "    \"Joshua Bloch\",\n" +
                "    \"Neal Gafter\"\n" +
                "  ]\n" +
                "}";
        Gson gson = new Gson();
        //将json数据转换为java类
        Book book = gson.fromJson(json, Book.class);
        mTvResult.setText(book.toString());
    }

    public void classToJson(View view) {
        List<String> authors = new ArrayList<>();
        authors.add("baobao1");
        authors.add("baobao2");
        authors.add("baobao3");
        Book book = new Book("java编程思想","111","222",authors);
        Gson gson = new Gson();
        String json = gson.toJson(book);
        mTvResult.setText(json);
    }

    public void JsonArrayToJavaList(View view) {
        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        Gson gson = new Gson();
        //将json数组转换为List
        List<String> javaArray = gson.fromJson(jsonArray, new TypeToken<List<String>>(){}.getType());
        mTvResult.setText(javaArray.toString());
    }
}
