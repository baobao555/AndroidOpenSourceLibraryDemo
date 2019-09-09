package com.baobao.gson;

import com.google.gson.annotations.SerializedName;

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

    private String title;
    @SerializedName(value = "isbn-10",alternate = {"isbn10","isbn_10"})  //注解，将json的isbn-10,isbn10或isbn_10映射到java类的属性isbn10
    private String isbn10;
    @SerializedName("isbn-13")
    private String isbn13;

    public Book(String title, String isbn10, String isbn13, List<String> authors) {
        this.title = title;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.authors = authors;
    }

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
