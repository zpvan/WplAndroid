package com.knox.aop.dynamicproxy;

interface IVerifierTest<T> {
    VerifierReport verify(T t);
}
