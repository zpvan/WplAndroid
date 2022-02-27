package com.knox.plugin.hookacti;

import android.content.ComponentName;
import android.content.Intent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class MockClass1 implements InvocationHandler {

	private static final String TAG = "MockClass1";

	Object mBase;

	public MockClass1(Object base) {
		mBase = base;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		if ("startActivity".equals(method.getName())) {
			/**
			 * 只拦截这个方法
			 * 替换参数，找到参数里面的第一个 Intent 对象
			 */
			Intent raw;
			int index = 0;

			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof Intent) {
					index = i;
					break;
				}
			}
			raw = (Intent) args[index];

			Intent newIntent = new Intent();

			// 替身 Activity 的包名
			String stubPackage = "com.knox.plugin.hookacti.StubActivity";

			// 这里把启动的 Activity 临时替换为 StubActivity
			ComponentName componentName = new ComponentName(stubPackage, StubActivity.class.getName());
			newIntent.setComponent(componentName);

			// 把原始要启动的 TargetActivity 存起来
			newIntent.putExtra(AMSHookHelper.EXTRA_TARGET_INTENT, raw);

			// 替换掉 Intent, 达到欺骗 AMS 的目的
			args[index] = newIntent;

			return method.invoke(mBase, args);
		}

		return null;
	}
}
