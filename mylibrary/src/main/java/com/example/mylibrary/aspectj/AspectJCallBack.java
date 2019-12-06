package com.example.mylibrary.aspectj;

/**
 * author : wangchang
 * date   : 2019-11-29 10:50
 * desc   :
 */
public interface AspectJCallBack {

    void onClick(String className, String id);

    void onActivityOpen(String className);

    void onActivityClose(String className);

    void onFragmentOpen(String className);

    void onFragmentClose(String className);
}
