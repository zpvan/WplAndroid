package com.knox.http.aop;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class OKHttpAspect {

  private static final String TAG = "OKHttpAspect";

  @Around("execution(* okhttp3.internal.connection.ConnectInterceptor.intercept(..) throws IOException)")
  public Object aroundMethod(ProceedingJoinPoint pdj) {
    /*result为连接点的放回结果*/
    Object result = null;
    String methodName = pdj.getSignature().getName();

    /*前置通知方法*/
    //System.out.println("前置通知方法>目标方法名：" + methodName + ",参数为：" + Arrays.asList(pdj.getArgs()));
    Log.e(TAG, "前置通知方法>目标方法名：" + methodName);

    /*执行目标方法*/
    try {
      result = pdj.proceed();

      /*返回通知方法*/
      System.out.println("返回通知方法>目标方法名" + methodName + ",返回结果为：" + result);
    } catch (Throwable e) {
      /*异常通知方法*/
      System.out.println("异常通知方法>目标方法名" + methodName + ",异常为：" + e);
    }

    /*后置通知*/
    System.out.println("后置通知方法>目标方法名" + methodName);

    return result;
  }

  @Around("execution(void okhttp3.RealCall.enqueue(..))")
  public Object aroundMethod2(ProceedingJoinPoint pdj) {
    /*result为连接点的放回结果*/
    Object result = null;
    String methodName = pdj.getSignature().getName();

    /*前置通知方法*/
    //System.out.println("前置通知方法>目标方法名：" + methodName + ",参数为：" + Arrays.asList(pdj.getArgs()));
    Log.e(TAG, "前置通知方法>目标方法名：" + methodName);

    /*执行目标方法*/
    try {
      result = pdj.proceed();

      /*返回通知方法*/
      System.out.println("返回通知方法>目标方法名" + methodName + ",返回结果为：" + result);
    } catch (Throwable e) {
      /*异常通知方法*/
      System.out.println("异常通知方法>目标方法名" + methodName + ",异常为：" + e);
    }

    /*后置通知*/
    System.out.println("后置通知方法>目标方法名" + methodName);

    return result;
  }
}
