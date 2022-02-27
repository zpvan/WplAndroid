package com.knox.plugin.hookacti;

import dalvik.system.DexClassLoader;

public class CustomClassLoader extends DexClassLoader {

	public CustomClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
		super(dexPath, optimizedDirectory, librarySearchPath, parent);
	}
}
