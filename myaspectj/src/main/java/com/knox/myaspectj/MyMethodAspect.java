package com.knox.myaspectj;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // aspectj(4)
public class MyMethodAspect {

    private static final String TAG = "MyMethodAspect";

    @Pointcut("call(* com.knox.aop.aspectj.Animal.testSleep(..))") // aspectj(5)
    public void callMethod() {
    }

    @Before("callMethod()") // aspectj(6)
    public void beforeMethodCall(JoinPoint joinPoint) {
        Log.i(TAG, "before->" + joinPoint.getTarget().toString()); // aspectj(7)
    }
}
