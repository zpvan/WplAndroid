package com.knox.aop.dynamicproxy;

import org.junit.Test;

public class DynamicProxyUnitTest {

  @Test
  public void letTest() {
    System.out.println("DynamicProxyUnitTest Begin");
    VerifierReport report =
        VerifierUtil.get(
                new IVerifierTest<Integer>() {
                  @Override
                  public VerifierReport verify(Integer integer) {
                    System.out.println("target verify, parameter: " + integer);
                    VerifierReport verifierReport = new VerifierReport();
                    System.out.println("target verify, report: " + verifierReport);
                    return verifierReport;
                  }
                })
            .verify(1);
    System.out.println("DynamicProxyUnitTest End, report: " + report);
  }
}
