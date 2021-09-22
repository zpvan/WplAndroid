package com.knox.leakcanary;

import android.util.Log;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

class ReferenceQueueUtil {

  static ReferenceQueue mReferenceQueue = new ReferenceQueue<>();

  private static final String TAG = "ReferenceQueueUtil";

  public static void test() {
    // 定义一个对象
    Object o = new Object();
    // 定义一个弱引用对象引用 o,并指定引用队列为 mReferenceQueue
    WeakReference<Object> weakReference = new WeakReference<Object>(o, mReferenceQueue);
    Log.e(TAG, "create ref: " + weakReference);
    // 去掉强引用
    o = null;
    // 触发应用进行垃圾回收
    Runtime.getRuntime().gc();
    // hack: 延时100ms,等待gc完成
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Reference ref = null;
    // 遍历 mReferenceQueue，取出所有弱引用
    while ((ref = mReferenceQueue.poll()) != null) {
      // System.out.println("============ \n ref in queue");
      Log.e(TAG, "ref: " + ref + " in queue");
    }
  }
}
