

# [Gradle Tutorial for Android](https://www.raywenderlich.com/249-gradle-tutorial-for-android-getting-started)



You’ll also learn about 

* gradlew tasks, 
* build types, 
* product flavors, 
* build variants, 
* and how to add additional information such as the date to the APK file name.



## What is Gradle?

与Ant跟Maven一样是自动化构建系统，automation system。除了有Ant和Maven的优势外，还弥补了它们不够concise与flexible的缺点，同时还支持Groovy与Kotlin语法的DSL。*.gradle*后缀的文件管理Project的编译过程。



## Project-level build.gradle



```gradle
/*
1 In the buildscript block you define settings needed to perform your project building.
*/
buildscript {
    /*
    2 In the repositories block you add names of the repositories that Gradle should search for the libraries you use.
    */
    repositories {
        google()
        jcenter()
    }
    /*
    3 The dependencies block contains necessary plugin dependencies, in this case the Gradle and Kotlin plugins. Do not put your module dependencies in this block.
    */
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.0'
        classpath 'org.jetbrains.kotlin:kotlin-gradle-plugin:1.1.51'
    }
}

/*
4 The structure of the allprojects block is similar to the buildscript block, but here you define repositories for all of your modules, not for Gradle itself. Usually you don’t define the dependencies section for allprojects. The dependencies for each module are different and should reside in the module-level build.gradle.
*/
allprojects {
    repositories {
        google()
        jcenter()
    }
}
```



## Module-level build.gradle



```gradle
/*
1 Specifies a list of plugins needed to build the module. The com.android.application plugin is necessary in order to setup the Android-specific settings of the build process. Here you can also use com.android.library if you’re creating a library module. The kotlin-android and kotlin-android-extensions plugins allow you to use the Kotlin language and the Kotlin Android extensions in your module.
*/
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

/*
2 In the android block you place all platform-specific options of the module.
*/
android {
    /*
    3 The compileSdkVersion option indicates the API level your app will be compiled with. In other words, you cannot use features from an API higher than this value. Here, you’ve set the value to use APIs from Android Oreo.
    */
    compileSdkVersion 27
    /*
    4 The buildToolsVersion option indicates the version of the compiler. From Gradle plugin 3.0.0 onward, this field is optional. If it is not specified, the Android SDK uses the most recent downloaded version of the Build Tools.
    */
    buildToolsVersion "26.0.2"
    /*
    5 The defaultConfig block contains options which will be applied to all build versions (e.g., debug, release, etc) of your app by default.
    */
    defaultConfig {
        /*
        6 The applicationId is the identifier of your app. It should be unique so as to successfully publish or update your app on Google Play Store.
        */
        applicationId "com.raywenderlich.socializify"
        /*
        7 In order to set the lowest API level supported, use minSdkVersion. Your app will not be available in the Play Store for the devices running on lower API levels.
        */
        minSdkVersion 21
        /*
        8 The targetSdkVersion parameter defines the maximum API level your app has been tested on. That is to say, you’re sure your app works properly on the devices with this SDK version, and it doesn’t require any backward compatibility behaviors. The best approach is to thoroughly test an app using the latest API, keeping your targetSdkVersion value equal to compileSdkVersion.
        */
        targetSdkVersion 27
        /*
        9 versionCode is a numeric value for the app version.
        */
        versionCode 1
        /*
        10 versionName is a user-friendly string for the app version.
        */
        versionName "1.0"
    }
}

/*
11 The dependencies block contains all dependencies needed for this module. Later in this tutorial, you’ll find out more about managing your project’s dependencies.
*/
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'org.jetbrains.kotlin:kotlin-stdlib-jre7:1.1.51'
    implementation 'com.android.support:appcompat-v7:27.0.1'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
}
```



## Mastering the build: Gradle Commands

*gradlew* is the *Gradle Wrapper*. You don’t need to worry about installating Gradle on your computer – the wrapper will do that for you.



```shell
cd /Users/knox/Documents/code/AnApp/WplAndroid

# 列举所有的task
./gradlew task 

./gradlew assemble

# analyzes the whole project looking for various mistakes, typos or vulnerabilities.
./gradlew lint
```



## Managing Dependencies

First, create a file named *dependencies.gradle* in the root directory of the project.

```groovy
ext {
    minSdkVersion = 17
    targetSdkVersion = 27
    compileSdkVersion = 27
    buildToolsVersion = "26.0.2"
    kotlinVersion = "1.1.51"
    supportVersion = "27.0.1"
    picassoVersion = "2.5.2"
}
```

Open the project-level *build.gradle* file (the one in the root directory, not the one in the app directory!) and add the following line on the top of the file:

```gr
apply from: 'dependencies.gradle'
```

*app module-level build.gradle*

```groovy
android {
    compileSdkVersion rootProject.compileSdkVersion
    buildToolsVersion rootProject.buildToolsVersion
    defaultConfig {
        applicationId "com.raywenderlich.socializify"
        minSdkVersion rootProject.minSdkVersion
        targetSdkVersion rootProject.targetSdkVersion
        versionCode 1
        versionName "1.0"
    }
}

dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation "com.android.support:appcompat-v7:$rootProject.supportVersion"
    implementation "com.android.support:design:$rootProject.supportVersion"
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:$rootProject.kotlinVersion"
}
```







By the end of this tutorial you should be able to

1. Build your Android apps from the command-line
2. Read through a Gradle build file
3. Create your own Gradle plugin
4. Create build flavors for profit!





# [Gradle Plugin Tutorial for Android](https://www.raywenderlich.com/22198417-gradle-plugin-tutorial-for-android-getting-started)