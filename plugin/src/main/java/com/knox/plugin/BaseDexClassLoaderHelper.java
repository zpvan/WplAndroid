package com.knox.plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;

/**
 * 把插件dex都合并到宿主的dex中，那么宿主App对应的ClassLoader就可以加载插件中的任意类。
 *
 * 宿主App加载插件中的类，一共有三种解决方案。此为其一。
 */
public final class BaseDexClassLoaderHelper {

	public static void patchClassLoader(ClassLoader cl, File apkFile, File optDexFile)
			throws IOException {

		// 获取 BaseDexClassLoader : pathList
		Object pathListObj = RefInvoke.getFieldObject(DexClassLoader.class.getSuperclass(), cl, "pathList");

		// 获取 PathList : Element[] dexElements
		Object[] dexElements = (Object[]) RefInvoke.getFieldObject(pathListObj.getClass(), pathListObj, "dexElements");

		// Element 类型
		Class<?> elementClass = dexElements.getClass().getComponentType();

		// 创建一个数组, 用来替换原始的数组
		Object[] newElements = (Object[]) Array.newInstance(elementClass, dexElements.length + 1);

		// 构造插件Element(File file, boolean isDirectory, File zip, DexFile dexFile)这个构造函数
		Class[] p1 = {File.class, boolean.class, File.class, DexFile.class};
		Object[] v1 = {apkFile, false, apkFile,
				DexFile.loadDex(apkFile.getCanonicalPath(), optDexFile.getAbsolutePath(), 0)};
		Object o = RefInvoke.createObject(elementClass, p1, v1);

		Object[] toAddElementArray = new Object[] {o};
		// 把原始的elements复制进去
		System.arraycopy(dexElements, 0, newElements, 0, dexElements.length);
		// 插件的那个element复制进去
		System.arraycopy(toAddElementArray, 0, newElements, dexElements.length, toAddElementArray.length);

		// 替换
		RefInvoke.setFieldObject(pathListObj, "dexElements", newElements);
	}
}
