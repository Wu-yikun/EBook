package com.wyk.ebook.controller;

import com.wyk.ebook.entity.LoginState;
import com.wyk.ebook.entity.User;
import com.wyk.ebook.entity.UserState;
import com.wyk.ebook.service.UserService;
import com.wyk.ebook.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

// 数据库中关于user表的Restful api
@RestController
@RequestMapping(value = "/user")
public class UserController {

    // 创建连接数据库的接口实例
    @Autowired
    private UserService userService;

    // 监听'/user/signup', 接收json参数 并将用户信息写入数据库中
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public String signUp(@RequestBody User user, HttpSession session) {
        String code = (String) session.getAttribute("code");
        String phoneNumber = (String) session.getAttribute("phoneNumber");
        System.out.println(user);
        System.out.println(user.getMail());

        if (code != null && code.equals(user.getCode()) && phoneNumber.equals(user.getAccount())) {
            if (userService.ifExist(user.getAccount(), user.getMail())) {
                return "邮箱已被注册";
            } else {
                userService.create(user.getAccount(), user.getPassword(), user.getName(), user.getMail());
                return "注册成功";
            }
        } else {
            return "验证码错误";
        }
    }

    // 监听'/user/login', Spring Security下因认证不通过进入的页面
    @GetMapping(value = "/login")
    @ResponseBody
    public String login() {
        return "请先登录";
    }

    // 监听'/user/switch', 接收用户json参数 改变用户禁用态
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    @ResponseBody
    public User change(@RequestBody User user) {
        String account = user.getAccount();
        User userToChange = userService.getUser(account);
        if (userToChange.getAllowed()) {
            userService.banUser(account);
        } else {
            userService.allowUser(account);
        }

        userToChange = userService.getUser(account);
        return userToChange;
    }

    // 监听'/user/states', 获取所有用户的权限
    @RequestMapping(value = "/states", method = RequestMethod.GET)
    @ResponseBody
    public List<UserState> getUserStates() {
        List<UserState> userStates = userService.getUserStates();
        return userStates;
    }

    // 监听'/user/init', 判断用户是否最近登录
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    @ResponseBody
    public LoginState ifUserLogin(HttpSession session) {
        LoginState loginState;
        loginState = (LoginState) session.getAttribute("loginState");
        if (loginState == null)
            loginState = new LoginState();
        return loginState;
    }

    // 监听'/user/code', 发送短信验证码到手机
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    @ResponseBody
    public String code(@RequestParam("phoneNumber") String phoneNumber, HttpSession session) {
        if (userService.ifExist(phoneNumber, null))
            return "手机号已被注册";
        try {
            String code = Message.sendSMS(phoneNumber);
            if (code == "发送失败") {
                return "发送失败";
            } else {
                session.setAttribute("code", code);
                session.setAttribute("phoneNumber", phoneNumber);
                return "发送成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }
    }
}
