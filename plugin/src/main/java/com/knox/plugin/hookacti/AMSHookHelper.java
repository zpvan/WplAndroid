package com.knox.plugin.hookacti;

import android.os.Handler;

import com.knox.plugin.RefInvoke;

import java.lang.reflect.Proxy;

/**
 * AMSHookHelper类用来完成欺上瞒下的工作
 * <p>
 * ======上半场======
 * AMSHookHelper 的 hookAMN 方法，把真正要启动的插件 Activity 临时替换为在 AndroidManifest.xml 中声明的替身
 * StubActivity，进而骗过 AMS。通过对 AMN 进行 Hook，替换为 MockClass1。
 * <p>
 * ======下半场 第1部分======
 * AMSHookHelper 的 hookActivity 方法，则是把 StubActivity 再换回为真正要启动的插件 Activity。
 * 通过对 ActivityThread 进行 Hook，替换为 MockClass2。
 * <p>
 * ======下班场 第2部分======
 * 欺骗 AMS 后，App 进程真正去"启动"插件的 Activity 时，仍然还有一些问题：
 * 1) LoadApk 类中存的 ClassLoader 需要是插件的 ClassLoader, 才能加载到插件中的 Activity
 *    ==> 类 LoadedApkClassLoaderHookHelper 做的事情
 * 2)
 */
public class AMSHookHelper {

	public static final String EXTRA_TARGET_INTENT = "extra_target_intent";

	/**
	 * Hook AMS
	 * 主要完成的操作是：把真正要启动的 Activity 临时替换为在 AndroidManifest.xml 中声明的
	 * 替身 Activity，进而骗过 AMS
	 */
	public static void hookAMN() throws ClassNotFoundException {

		// 获取 AMN 的 gDefault，gDefault 是 final 静态的
		Object gDefault = RefInvoke.getStaticFieldObject(
				"android.app.ActivityManagerNative", "gDefault");

		// gDefault 是一个 android.util.Singleton<T> 对象；我们取出这个单例里面的 mInstance 字段
		Object mInstance = RefInvoke.getFieldObject("android.util.Singleton",
				gDefault, "mInstance");

		// 创建一个这个对象的代理对象 MockClass1，然后替换这个字段，让我们的代理对象帮忙干活
		Class<?> classB2Interface = Class.forName("android.app.IActivityManager");
		Object proxy = Proxy.newProxyInstance(
				Thread.currentThread().getContextClassLoader(),
				new Class<?>[]{classB2Interface},
				new MockClass1(mInstance));

		// 把 gDefault 的 mInstance 字段，修改为 proxy
		RefInvoke.setFieldObject("android.util.Singleton", gDefault,
				"mInstance", proxy);
	}

	/**
	 * 由于之前用替身 Activity 欺骗了 AMS；现在需要换回真正想启动的 Activity
	 * 到最终要启动 Activity 的时候，会交给 ActivityThread 的一个内部类叫做 H 来完成
	 * H 会完成这个消息转发；最终调用它的 callback
	 */
	public static void hookActivityThread() {

		// 先获取到当前的 ActivityThread 对象
		Object currentActivityThread = RefInvoke.getStaticFieldObject(
				"android.app.ActivityThread", "sCurrentActivityThread");

		// 由于 ActivityThread 一个进程只有一个，获取这个对象的 mH
		Handler mH = (Handler) RefInvoke.getFieldObject(
				currentActivityThread, "mH");

		// 把 Handler 的 mCallback 字段，替换为 new MockClass2(mH)
		RefInvoke.setFieldObject(Handler.class, mH, "mCallback", new MockClass2(mH));
	}
}
