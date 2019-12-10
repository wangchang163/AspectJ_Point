package com.example.mylibrary.time;

/**
 * author : wangchang
 * date   : 2019-12-10 12:12
 * desc   :
 */
public interface CallBack {

    void getTime(String className, String methodName, String time);

    void getMethodTime(String className, String methodName, String time);
}
