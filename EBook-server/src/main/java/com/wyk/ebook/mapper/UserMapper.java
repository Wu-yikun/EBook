package com.wyk.ebook.mapper;

import com.wyk.ebook.entity.User;
import com.wyk.ebook.entity.UserState;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User getUser(String account);

    User getLoginState(String account, String password);

    int changeUser(Boolean allowed, String account);

    void createUser(String account, String password, String name, Boolean allowed, Boolean isManager, String mail);

    List<UserState> getUserState();

    User getUserWithMail(String mail);
}
