package com.knox.plugin;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.knox.plugin.hookacti.CustomClassLoader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;

public class PluginUtil {

	// 加载插件中的dex
	public static DexClassLoader getDexClassLoader(Context context, String apkName) {
		File extractFile = context.getFileStreamPath(apkName);
		String dexPath = extractFile.getPath();

		File fileRelease = context.getDir("dex", 0);// 0 表示Context.MODE_PRIVATE

		DexClassLoader classLoader = new DexClassLoader(dexPath, fileRelease.getAbsolutePath(),
				null, context.getClassLoader());

		// 由此生成的 DexClassLoader, 使用它的 loadClass 方法可以加载插件中的任何一个类
		return classLoader;
	}

	// 解析出插件中的自定义 Application 的名称
	public static String loadApplication(Context context, File apkFile) {
		// 首先调用 parsePackage 获取到 apk 对象对应的 Package 对象
		Object packageParser = RefInvoke.createObject("android.content.pm.PackageParser");
		Class[] p1 = {File.class, int.class};
		Object[] v1 = {apkFile, PackageManager.GET_RECEIVERS};
		Object packageObj = RefInvoke.invokeInstanceMethod(packageParser, "parsePackage", p1, v1);
		Object obj = RefInvoke.getFieldObject(packageObj, "applicationInfo");
		ApplicationInfo applicationInfo = (ApplicationInfo) obj;
		return applicationInfo.className;
	}

	// 把 assets 目录下的插件复制到 /data/data/files 目录下
	public static void extractAssets(Context context, String apkName) {

	}

	public static File getPluginOptDexDir(String packageName) {
		return null;
	}

	public static File getPluginLibDir(String packageName) {
		return null;
	}

	/**
	 * 加载插件中的类有3种方案：
	 * 1）为每个插件创建一个 ClassLoader
	 *    缺点：这种方案非常麻烦，为此要反射出一堆类型的对象，而且还要适配各种 Android 版本
	 * 2) 合并多个 dex。把插件的 dex，手动添加到宿主的 dexElements 数组中。
	 *    热修复框架 Nuwa 也是这个思路，它发现把热修复 dex 和宿主 dex 合并成一个新的 Elements 数组，
	 *    如果这两个 dex 有相同的类和方法，那么位于数组前面的 dex 中的类和方法将生效，而后面那个不会生效。
	 *    于是，就把热修复 dex 刻意放到新的 Elements 数组前面。
	 * 1）与 2）共同缺点，不支持资源
	 * 3）修改 App 原生的 ClassLoader
	 */
	public static void loadPluginClass1(File apkFile) {
		/**
		 * 1）为每个插件创建一个 ClassLoader
		 *
		 * 每个插件就是一个 LoadedApk 对象，把 LoadedApk 的 mClassLoader 字段，修改为自定义的 ClassLoader，
		 * 也就是 CustomClassLoader，其实就是 DexClassLoader 的子类。
		 * 通过 CustomClassLoader，就可以加载插件中的 Activity，从宿主 App 进入到插件 App 的 Activity。
		 * 在此之后，只要还在这个插件中，就会加载这个插件中的各个类，都是通过这个 CustomClassLoader 加载的。
		 */
		ApplicationInfo applicationInfo = new ApplicationInfo();
		Object loadedApk = null;

		String odexPath = PluginUtil.getPluginOptDexDir(applicationInfo.packageName).getPath();
		String libDir = PluginUtil.getPluginLibDir(applicationInfo.packageName).getPath();

		CustomClassLoader classLoader = new CustomClassLoader(apkFile.getPath(), odexPath, libDir,
				ClassLoader.getSystemClassLoader());
		RefInvoke.setFieldObject(loadedApk, "mClassLoader", classLoader);
	}

	public static void loadPluginClass2(ClassLoader cl, File apkFile, File optDexFile) throws IOException {
		/**
		 * 2) 合并多个 dex。把插件的 dex，手动添加到宿主的 dexElements 数组中。
		 *
		 * 步骤如下：
		 * （1）根据宿主的 ClassLoader，获取宿主的 dexElements 字段：
		 *     （1.1）
		 *     （1.2）
		 * （2）根据插件的 apkFile，反射出一个 Element 类型的对象，这就是插件 dex。
		 * （3）把插件 dex 和宿主 dexElements 合并成一个新的 dex 数组，替换宿主之前的 dexElements 字段。
		 */

		// 获取 BaseDexClassLoader : pathList
		Object pathListObj = RefInvoke.getFieldObject(DexClassLoader.class.getSuperclass(),
				cl, "pathList");

		// 获取 PathList : Element[] dexElements
		Object[] dexElements = (Object[]) RefInvoke.getFieldObject(pathListObj,
				"dexElements");

		// Element 类型
		Class<?> elementClass = dexElements.getClass().getComponentType();

		// 创建一个数组，用来替换原始的数组
		Object[] newElements = (Object[]) Array.newInstance(elementClass,
				dexElements.length + 1);

		// 构造插件 Element(File file, boolean isDirectory, File zip, DexFile dexFile) 这个构造函数
		Class[] p1 = {File.class, boolean.class, File.class, DexFile.class};
		Object[] v1 = {apkFile, false, apkFile,
				DexFile.loadDex(apkFile.getCanonicalPath(), optDexFile.getAbsolutePath(), 0)};
		Object o = RefInvoke.createObject(elementClass, p1, v1);

		Object[] toAddElementArray = new Object[] {o};
		// 把原始的 Element 复制进去
		System.arraycopy(dexElements, 0, newElements, 0, dexElements.length);
		// 把插件的那个 element 复制进去
		System.arraycopy(toAddElementArray, 0, newElements,
				dexElements.length, toAddElementArray.length);

		// 替换
		RefInvoke.setFieldObject(pathListObj, "dexElements", newElements);
	}

	public static void loadPluginClass3(Context context) {
		/**
		 * 直接把系统的 ClassLoader 替换为 MyClassLoader，并且 MyClassLoader 能担当宿主 ClassLoader 的角色。
		 */

		MyClassLoader myClassLoader = new MyClassLoader(context.getPackageCodePath(),
				context.getClassLoader());

	}
}
