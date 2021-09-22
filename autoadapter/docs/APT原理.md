# Annotation Process Tools

* 用于生成 Java 代码



总所周知，`ButterKnife、Dagger、GreenDao、Protocol Buffers` 这些常用的注解生成框架都会在编译过程中生成代码。而 **使用 AndroidAnnotation 结合 APT 技术 来生成代码的时机，是在编译最开始的时候介入的。但是 AOP 是在编译完成后生成 dex 文件之前的时候，直接通过修改 .class 文件的方式，来直接添加或者修改代码逻辑的**。

使用 `APT` 技术生成 `Java` 代码的方式具有如下 **两方面** 的优势：

- 1）、**隔离了框架复杂的内部实现，使得开发更加地简单高效**。
- 2）、**大大减少了手工重复的工作量，降低了开发时出错的机率**。



## 参考资料

[深入探索编译插桩技术（二、AspectJ）](https://juejin.cn/post/6844904112396615688)

