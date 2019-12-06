package com.example.mylibrary.aspectj;

import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * author : wangchang
 * date   : 2019-11-29 10:55
 * desc   : aop实现监听方法
 */
@Aspect
public class AspectJController {

    /**
     * 第一个*所在的位置表示的是返回值，*表示的是任意的返回值,.. 所在位置是方法参数的位置，.. 表示的是任意类型、任意个数的参数
     * Pointcut 切入点，告诉代码注入工具，在何处注入一段特定代码的表达式。
     */
    @Pointcut("execution(* onClick(..))")
    public void onClick() {
    }

    @Around("onClick()")
    public void onClickMethodAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String className = "";
        if (target != null) {
            className = target.getClass().getName();
            if (className.contains("$")) {
                className = className.split("\\$")[0];
            }
            if (className.contains("_ViewBinding")) {
                className = className.split("_ViewBinding")[0];
            }
        }
        //获取点击事件view对象及名称，可以对不同按钮的点击事件进行统计
        Object[] args = joinPoint.getArgs();
        if (args.length >= 1 && args[0] instanceof View) {
            View view = (View) args[0];
            int id = view.getId();
            if (id < 0) {
                AspectJManager.onClick(className,"");
            } else {
                String entryName = view.getResources().getResourceEntryName(id);
                AspectJManager.onClick(className, entryName);
            }
        }
        joinPoint.proceed();//执行原来的代码
    }

    @Around("execution(* onResume()) && within(android.support.v4.app.Fragment)")
    public void onFragmentResume(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String className = target.getClass().getName();
        AspectJManager.onFragmentOpen(className);
        joinPoint.proceed();
    }

    @Around("execution(* onPause()) && within(android.support.v4.app.Fragment)")
    public void onFragmentPause(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String className = target.getClass().getName();
        AspectJManager.onFragmentClose(className);
        joinPoint.proceed();
    }

    @Around("execution(* onHiddenChanged(..))&& within(android.support.v4.app.Fragment)")
    public void onHiddenChanged(final ProceedingJoinPoint joinPoint) throws Throwable {
        //访问目标方法的参数
        Object[] args = joinPoint.getArgs();
        boolean hidden = false;
        if (args != null && args.length > 0 && args[0].getClass() == Boolean.class) {
            hidden = (boolean) args[0];
        }
        Object target = joinPoint.getTarget();
        String className = target.getClass().getName();
        if (hidden) {
            AspectJManager.onFragmentClose(className);
        } else {
            AspectJManager.onFragmentOpen(className);
        }
        joinPoint.proceed();
    }

    @Around("execution(* setUserVisibleHint(..))&& within(android.support.v4.app.Fragment)")
    public void setUserVisibleHint(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        boolean hidden = false;
        if (args != null && args.length > 0 && args[0].getClass() == Boolean.class) {
            hidden = (boolean) args[0];
        }
        Object target = joinPoint.getTarget();
        String className = target.getClass().getName();
        if (hidden) {
            AspectJManager.onFragmentOpen(className);
        } else {
            AspectJManager.onFragmentClose(className);
        }
        joinPoint.proceed();
    }

    @Pointcut("execution(* onResume()) && within(com.yunshuxie.bearword.base.BaseActivityM)")
    public void openActivity() {

    }

    @Pointcut("execution(* onDestroy()) && within(com.yunshuxie.bearword.base.BaseActivityM)")
    public void closeActivity() {
    }


    @Around("openActivity()")
    public void openActivityMethodAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String className = target.getClass().getName();
        AspectJManager.onActivityOpen(className);
        joinPoint.proceed();
    }

    @Around("closeActivity()")
    public void closeActivityMethodAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String className = target.getClass().getName();
        AspectJManager.onActivityClose(className);
        joinPoint.proceed();
    }


}
