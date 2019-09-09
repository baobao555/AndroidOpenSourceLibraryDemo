package com.baobao.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Time:2019/5/19
 * <p>
 * Author:baobao
 * <p>
 * Description:
 */
public class Book {

    /**
     * title : Java Puzzlers: Traps, Pitfalls, and Corner Cases
     * isbn-10 : 032133678X
     * isbn-13 : 978-0321336781
     * authors : ["Joshua Bloch","Neal Gafter"]
     */
    @JSONField(ordinal = 0) //设置java对象序列化为json字符串时的字段排列顺序
    private String title;
    @JSONField(name = "isbn-10",ordinal = 1)  //注解，将json的isbn-10映射到java类的属性isbn10
    private String isbn10;
    @JSONField(name = "isbn-13",ordinal = 2)
    private String isbn13;

    public Book() {
    }

    public Book(String title, String isbn10, String isbn13, List<String> authors) {
        this.title = title;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.authors = authors;
    }

    @JSONField(ordinal = 3)
    private List<String> authors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", authors=" + authors +
                '}';
    }
}
