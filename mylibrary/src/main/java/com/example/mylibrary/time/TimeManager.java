package com.example.mylibrary.time;

/**
 * author : wangchang
 * date   : 2019-12-10 12:14
 * desc   : 方法耗时管理器
 */
public class TimeManager {

    public static CallBack callBack;

    public static void init(CallBack trackPointCallBack) {
        callBack = trackPointCallBack;
    }

    static void getViewTime(String className, String methodName, String time) {
        if (callBack != null) {
            callBack.getTime(className, methodName, time);
        }
    }

    static void getMethodTime(String className, String methodName, String time) {
        if (callBack != null) {
            callBack.getMethodTime(className, methodName, time);
        }
    }
}
