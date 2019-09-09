package com.baobao.fastjson;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private TextView mTVResult;
    //json字符串-简单对象型
    private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";

    //json字符串-数组类型
    private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

    //复杂格式json字符串
    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTVResult = findViewById(R.id.tv_result);
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
        Book book = JSON.parseObject(json, Book.class);//必须提供Book的无参构造，否则反射失效
        mTVResult.setText(book.toString());
    }

    public void JsonArrayToJavaList(View view) {
        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
       /* List<String> list = JSON.parseObject(jsonArray,List.class);
        mTVResult.setText(list.toString());*/

        List<String> list1 = JSON.parseArray(jsonArray, String.class);
        mTVResult.setText(list1.toString());
    }

    public void classToJson(View view) {
        List<String> authors = new ArrayList<>();
        authors.add("baobao1");
        authors.add("baobao2");
        authors.add("baobao3");
        Book book = new Book("java编程思想","111","222",authors);
        String jsonString = JSON.toJSONString(book);
        mTVResult.setText(jsonString);
    }
}
