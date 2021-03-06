package com.wyk.ebook.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

// Order实体类
@Data
public class Order {
    private Integer id;
    private String ISBN;
    private String account;
    private String userName;
    private String bookName;
    private String author;
    private String cover;
    private Float price;
    private Integer count;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp date;
}
