package com.knox.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class HostActivity extends AppCompatActivity {

	private String apkName = "plugin1.apk";
	private AssetManager mAssetManager;
	private Resources mResources;
	private Resources.Theme mTheme;

	/**
	 * 在宿主 App 中读取插件里的一个字符串资源
	 * <p>
	 * /res/values/strings.xml
	 * <resources>
	 * <string name="my_plugin_hello_world">Hello World</string>
	 * </resources>
	 */
	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(newBase);
		PluginUtil.extractAssets(newBase, apkName);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_host);

		//==========
		/**
		 * （3）加载外部的插件，生成这个插件对应的ClassLoader
		 */
		File extractFile = this.getFileStreamPath(apkName);
		String dexPath = extractFile.getPath();

		File fileRelease = getDir("dex", 0);// 0 表示 Context.MODE_PRIVATE

		DexClassLoader classLoader = new DexClassLoader(dexPath, fileRelease.getAbsolutePath(), null, getClassLoader());

		//==========
		loadResources(dexPath);

		//==========
		try {
			/**
			 * （4）通过反射，获取插件中的类，构造出插件类的对象dynamicObject，然后就可以让插件中的类读取插件中的资源了。
			 */
			Class<?> loadClassDynamic = classLoader.loadClass("com.knox.plugin.PluginDynamic");
			Object dynamicObject = loadClassDynamic.newInstance();
			IDynamic dynamic = (IDynamic) dynamicObject;
			String ans = dynamic.getStringForResId(this);
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
	}

	private void loadResources(String dexPath) {
		/**
		 * （1）通过反射，创建AssetManager对象，调用addAssetPath方法，把插件Plugin的路径添加到这个AssetManager对象中。
		 * 从此，这个AssetManager对象只为插件Plugin1服务了。
		 * 在这个AssetManager对象的基础上，创建相应的Resources和Theme对象。
		 */
		try {
			AssetManager assetManager = AssetManager.class.newInstance();
			Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
			addAssetPath.invoke(assetManager, dexPath);
			mAssetManager = assetManager;
		} catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}

		mResources = new Resources(mAssetManager, super.getResources().getDisplayMetrics(), super.getResources().getConfiguration());
		mTheme = mResources.newTheme();
		mTheme.setTo(super.getTheme());
	}

	@Override
	public AssetManager getAssets() {
		/**
		 * （2）mAssetManager是指向插件的，如果这个对象为空，就调用父类ContextImpl的getAssets方法，
		 * 这时候得到的AssetManager对象，就指向宿主HostApp，读取的资源也是HostApp中的资源。
		 */
		if (mAssetManager == null) {
			return super.getAssets();
		}
		return mAssetManager;
	}

	@Override
	public Resources getResources() {
		if (mResources == null) {
			return super.getResources();
		}
		return mResources;
	}

	@Override
	public Resources.Theme getTheme() {
		if (mTheme == null) {
			return super.getTheme();
		}
		return mTheme;
	}
}