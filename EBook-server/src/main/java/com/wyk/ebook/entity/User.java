package com.wyk.ebook.entity;

import lombok.Data;

// User实体类
@Data
public class User {
    private String account;
    private String password;
    private String name;
    private String code;
    private Boolean allowed;
    private Boolean isManager;
    private String mail;
}
