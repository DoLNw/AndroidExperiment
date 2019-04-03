package com.jiacheng.firstapplication;

import android.app.Application;

import com.jiacheng.firstapplication.model.User;

public class App extends Application {
    /** 当前登录用户 */
    public User user;

    @Override
    public void onCreate() {
        super.onCreate();
        // 模拟一个登录用户。后续章节完成登录功能后修改此处。
        user = new User();
        user.id = 1;
        user.name = "test";
        user.mobileToken = "test";
    }
}