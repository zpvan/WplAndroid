package com.knox.plugin.hookacti;

import android.content.pm.ApplicationInfo;

import com.knox.plugin.PluginUtil;
import com.knox.plugin.RefInvoke;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class LoadedApkClassLoaderHookHelper {

	/**
	 * 1）为插件创建一个 LoadedApk 对象，并把它"事先"放到 mPackages 缓存中。
	 * 这样 getPackageInfo 方法就会直接返回这个插件的 LoadedApk 对象，也就是永远命中缓存，永远不会走创建 LoadedApk 对象的逻辑。
	 * 2）反射得到插件的 LoadedApk 对象的 mClassLoader 字段，设置为插件的 ClassLoader。
	 */
	public static Map<String, Object> sLoadedApk = new HashMap<>();

	public static void hookLoadedApkInActivityThread(File apkFile) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

		// 先获取到当前 ActivityThread 对象
		Object currentActivityThread = RefInvoke.getStaticFieldObject(
				"android.app.ActivityThread",
				"currentActivityThread");

		// 获取到 mPackages 这个成员变量，这里缓存了 dex 包的信息
		Map mPackages = (Map) RefInvoke.getFieldObject(currentActivityThread,
				"mPackages");

		// 准备两个参数
		// android.content.res.CompatibilityInfo
		Object defaultCompatibilityInfo = RefInvoke.getStaticFieldObject(
				"android.content.res.CompatibilityInfo",
				"DEFAULT_COMPATIBILITY_INFO");
		// 从 apk 中取得 ApplicationInfo 信息
		ApplicationInfo applicationInfo = generateApplicationInfo(apkFile);

		// 调用 ActivityThread 的 getPackageInfoNoCheck 方法 loadedApk，上面得到的两个数据都是用来做参数的
		Class[] p1 = {ApplicationInfo.class, Class.forName("android.content.res.CompatibilityInfo")};
		Object[] v1 = {applicationInfo, defaultCompatibilityInfo};
		Object loadedApk = RefInvoke.invokeInstanceMethod(currentActivityThread,
				"getPackageInfoNoCheck", p1, v1);

		// 为插件造一个新的 ClassLoader
		String odexPath = PluginUtil.getPluginOptDexDir(applicationInfo.packageName).getPath();
		String libDir = PluginUtil.getPluginLibDir(applicationInfo.packageName).getPath();
		ClassLoader classLoader = new CustomClassLoader(apkFile.getPath(), odexPath, libDir, ClassLoader.getSystemClassLoader());
		RefInvoke.setFieldObject(loadedApk, "mClassLoader", classLoader);

		// 把插件的 LoadedApk 对象放入缓存
		WeakReference weakReference = new WeakReference(loadedApk);
		mPackages.put(applicationInfo.packageName, weakReference);

		// 由于是弱引用，因此我们必须在某个地方存一份，不然容易被GC，就前功尽弃了。
		sLoadedApk.put(applicationInfo.packageName, loadedApk);
	}

	private static ApplicationInfo generateApplicationInfo(File apkFile) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

		// 找出需要反射的核心类：android.content.pm.PackageParser
		Class<?> packageParserClass = Class.forName("android.content.pm.PackageParser");
		Class<?> packageParser$PackageClass = Class.forName("android.content.pm.packageParser$PackageClass");
		Class<?> packageUserStateClass = Class.forName("android.content.pm.packageUserState");

		/**
		 * 首先拿到我们的终极目标：generateApplicationInfo 方法
		 * API 23!
		 * public static ApplicationInfo generateApplicationInfo(Package p, int flags,
		 *     PackageUserState state) {
		 * 其他 Android 版本不保证也是如此
		 */

		/**
		 * 首先，创建一个 Package 对象供这个方法调用
		 * 该对象可以通过 android.content.pm.PackageParser#parsePackage 方法返回的 Package 对象的字段获取得到
		 * 创建出一个 PackageParser 对象供使用
		 */
		Object packageParser = packageParserClass.newInstance();

		/**
		 * 调用 PackageParser.parsePackage 解析 apk 的信息
		 * 实际上是一个 android.content.pm.PackageParser.Package 对象
		 */
		Class[] p1 = {File.class, int.class};
		Object[] v1 = {apkFile, 0};
		Object packageObj = RefInvoke.invokeInstanceMethod(packageParser,
				"parsePackage", p1, v1);

		// 第三个参数 mDefaultPackageUserState 可直接使用默认构造函数构造
		Object defaultPackageUserState = packageUserStateClass.newInstance();

		// 万事俱备
		Class[] p2 = {packageParser$PackageClass, int.class, packageUserStateClass};
		Object[] v2 = {packageObj, 0, defaultPackageUserState};
		ApplicationInfo applicationInfo = (ApplicationInfo) RefInvoke.invokeInstanceMethod(packageParser,
				"generateApplicationInfo", p2, v2);

		String apkPath = apkFile.getPath();
		applicationInfo.sourceDir = apkPath;
		applicationInfo.publicSourceDir = apkPath;

		return applicationInfo;
	}
}
