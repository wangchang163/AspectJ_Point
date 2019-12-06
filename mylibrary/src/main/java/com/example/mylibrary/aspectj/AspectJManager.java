package com.example.mylibrary.aspectj;

/**
 * author : wangchang
 * date   : 2019-11-29 10:49
 * desc   : 定义调用
 */
public class AspectJManager {

    private static AspectJCallBack callBack;


    public static void init(AspectJCallBack trackPointCallBack) {
        callBack = trackPointCallBack;
    }

    static void onClick(String className, String id) {
        if (callBack != null) {
            callBack.onClick(className, id);
        }
    }

    static void onActivityOpen(String className) {
        if (callBack != null) {
            callBack.onActivityOpen(className);
        }
    }

    static void onActivityClose(String className) {
        if (callBack != null) {
            callBack.onActivityClose(className);
        }
    }

    static void onFragmentOpen(String className) {
        if (callBack != null) {
            callBack.onFragmentOpen(className);
        }
    }

    static void onFragmentClose(String className) {
        if (callBack != null) {
            callBack.onFragmentClose(className);
        }
    }
}
