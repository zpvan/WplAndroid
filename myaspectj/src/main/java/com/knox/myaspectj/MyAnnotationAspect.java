package com.knox.myaspectj;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyAnnotationAspect {

  private static final String TAG = "MyAnnotationAspect";

  @Pointcut("@annotation(com.knox.myaspectj.MyAspectjAnnotation)")
  public void annotationPointCutDefinition(MyAspectjAnnotation myAspectjAnnotation) {}

  // @After("annotationPointCutDefinition(VerifierCase)")
  // public void afterVerifierCase(JoinPoint joinPoint, VerifierCase verifierCase) {
  //    Log.i(TAG, "afterVerifierCase");
  // }
  // execution(* *(..)) && 是为了避免切面被调用两次的问题, 一次call, 一次execution
  @AfterReturning(
      pointcut = "execution(com.knox.myaspectj.MyAspectjResult *(..)) && @annotation(com.knox.myaspectj.MyAspectjAnnotation)",
      returning = "result")
  public void afterVerifierCase(JoinPoint joinPoint, MyAspectjResult result) {
    Log.i(TAG, "additional concern");
    Log.i(TAG, "Method Signature: " + joinPoint.getSignature());
    Log.i(TAG, "Result in advice: " + result);
    Log.i(TAG, "end of after returning advice...");
  }
}
