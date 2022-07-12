package com.wyk.ebook.entity;

import lombok.Data;

@Data
public class UserState {
    private String account;
    private String name;
    private Boolean allowed;
}
