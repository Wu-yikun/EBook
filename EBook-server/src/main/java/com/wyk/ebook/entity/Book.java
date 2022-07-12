package com.wyk.ebook.entity;

import lombok.Data;

// Book实体类
@Data
public class Book {
    private String ISBN;
    private String name;
    private String author;
    private String outline;
    private Integer stock;
    private Float price;
    private String press;
    private String year;
    private Integer pages;
    private String cover;

}
