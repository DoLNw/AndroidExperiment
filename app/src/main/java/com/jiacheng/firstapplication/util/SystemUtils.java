package com.jiacheng.firstapplication.util;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 一些系统工具方法
 */
public class SystemUtils {

    /**
     * 取反色。alpha不变。
     * 
     * @param color
     *            颜色
     * @return 反色
     */
    public static int reverseColor(int color) {
        int a = Color.alpha(color);
        int r = 255 - Color.red(color);
        int g = 255 - Color.green(color);
        int b = 255 - Color.blue(color);
        return Color.argb(a, r, g, b);
    }

    /**
     * 是否已连接网络
     * 
     * @param context
     *            Context
     * @return 是否已连接网络
     */
    public static boolean isNetConnected(Context context) {
        NetworkInfo[] infos = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getAllNetworkInfo();
        if (infos == null)
            return false;
        boolean conn = false;
        for (NetworkInfo info : infos) {
            if (info.isConnected()) {
                conn = true;
                break;
            }
        }
        return conn;
    }
}
