package com.example.mylibrary.time;

import com.example.mylibrary.BuildConfig;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.sql.Time;

/**
 * author : wangchang
 * date   : 2019-12-10 10:08
 * desc   : 无埋点监控setContentView，以及半埋点监控所需要方法
 */
@Aspect
public class ExecutionTime {

    @Around("execution(* android.app.Activity.setContentView(..))")
    public void setContentView(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            if (BuildConfig.DEBUG) {
                Object target = joinPoint.getTarget();
                String name = joinPoint.getSignature().getName();
                long start_time = System.currentTimeMillis();
                joinPoint.proceed();
                long end_time = System.currentTimeMillis();
                String time = String.valueOf(end_time - start_time);
                TimeManager.getViewTime(target.getClass().getName(), name, time);
            } else {
                joinPoint.proceed();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            joinPoint.proceed();
        }

    }


    @Pointcut("execution(@com.example.mylibrary.impl.AspectJMethodAnalyze * *(..))")
    public void methodAnalyze() {

    }

    @Around("methodAnalyze()")
    public void analyzeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            if (BuildConfig.DEBUG) {
                Object target = joinPoint.getTarget();
                String name = joinPoint.getSignature().getName();
                long start_time = System.currentTimeMillis();
                joinPoint.proceed();
                long end_time = System.currentTimeMillis();
                String time = String.valueOf(end_time - start_time);
                TimeManager.getMethodTime(target.getClass().getName(), name, time);
            } else {
                joinPoint.proceed();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            joinPoint.proceed();
        }

    }


}
