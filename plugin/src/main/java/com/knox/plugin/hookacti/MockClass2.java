package com.knox.plugin.hookacti;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.knox.plugin.RefInvoke;

class MockClass2 implements Handler.Callback {

	Handler mBase;

	public MockClass2(Handler base) {
		mBase = base;
	}

	@Override
	public boolean handleMessage(@NonNull Message msg) {

		switch (msg.what) {
			// ActivityThread 里面 "LAUNCH_ACTIVITY" 这个字段的值是100
			// 使用反射的方式获取最好，这里为了简便直接使用硬编码
			case 100:
				handleLaunchActivity(msg);
				break;
		}

		return true;
	}

	private void handleLaunchActivity(Message msg) {
		Object obj = msg.obj;

		// 把替身恢复成真身
		Intent raw = (Intent) RefInvoke.getFieldObject(obj, "intent");

		Intent target = raw.getParcelableExtra(AMSHookHelper.EXTRA_TARGET_INTENT);
		raw.setComponent(target.getComponent());

		// 为后续 loadedApk 流程做准备
		ActivityInfo activityInfo = (ActivityInfo) RefInvoke.getFieldObject(obj,
				"activityInfo");
		activityInfo.applicationInfo.packageName = target.getPackage() == null ?
				target.getComponent().getPackageName() : target.getPackage();
	}
}
