package com.jiacheng.firstapplication;

import android.app.Application;

import com.jiacheng.firstapplication.model.User;

public class App extends Application {
    /** 当前登录用户 */
    public User user;


    //注：继承Application类，主要重写里面的onCreate（）方法（android.app.Application包的onCreate（）
    //才是真正的Android程序的入口点），就是创建的时候，初始化变量的值。然后在整个应用中的各个文件中就可以对该变量进行操作了。
    //然后要在AndroidManifest里面写Android:name="$继承Application的类名"
    //然后获取时App app = (App) getApplication();

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