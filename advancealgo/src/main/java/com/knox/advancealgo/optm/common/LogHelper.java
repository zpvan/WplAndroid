package com.knox.advancealgo.optm.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogHelper {

  private static Date date = new Date();
  private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSSS");

  public static void d(String msg) {
    date.setTime(System.currentTimeMillis());
    System.out.println(sdf.format(date) + " " + Thread.currentThread().getId() + " D " + msg);
  }

  public static void e(String msg) {
    date.setTime(System.currentTimeMillis());
    System.err.println(sdf.format(date) + " " + Thread.currentThread().getId() + " D " + msg);
  }
}
