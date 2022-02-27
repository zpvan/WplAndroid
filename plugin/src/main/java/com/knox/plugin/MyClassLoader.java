package com.knox.plugin;

import java.util.ArrayList;
import java.util.List;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

class MyClassLoader extends PathClassLoader {
	private List<DexClassLoader> mClassLoaderList = new ArrayList<>();

	public MyClassLoader(String dexPath, ClassLoader parent) {
		super(dexPath, parent);
	}

	/**
	 * 添加一个插件到当前的 ClassLoader 中
	 */
	protected void addPluginClassLoader(DexClassLoader dexClassLoader) {
		mClassLoaderList.add(dexClassLoader);
	}

	@Override
	protected Class<?> loadClass(String className, boolean resolve) throws ClassNotFoundException {
		Class<?> clazz = null;

		try {
			// 先查找 parent ClassLoader，这里实际就是系统创建的 ClassLoader，目标对应为宿主 apk
			clazz = getParent().loadClass(className);
		} catch (ClassNotFoundException ignored) {
		}

		if (clazz != null) {
			return clazz;
		}

		// 遍历查找
		if (mClassLoaderList != null) {
			for (DexClassLoader classLoader : mClassLoaderList) {
				if (classLoader == null) continue;
				try {
					// 这里只查找插件自己的 apk，不需要查找 parent
					clazz = getParent().loadClass(className);
					if (clazz != null) {
						return clazz;
					}
				} catch (ClassNotFoundException ignored) {
				}
			}
		}
		throw new ClassNotFoundException(className + " in loader " + this);
	}
}
