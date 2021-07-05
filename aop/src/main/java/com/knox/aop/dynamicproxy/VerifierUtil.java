package com.knox.aop.dynamicproxy;

import java.lang.reflect.Proxy;

public class VerifierUtil {

    public static <T> IVerifierTest<T> get(IVerifierTest<T> target) {
        return (IVerifierTest<T>) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new VerifierHandler(target));
    }

}
