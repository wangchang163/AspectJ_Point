package com.example.mylibrary.impl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : wangchang
 * date   : 2019-12-10 13:35
 * desc   : 半埋点方法注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AspectJMethodAnalyze {

}
