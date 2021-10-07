package com.knox.advancealgo.optm;

import com.knox.advancealgo.optm.common.LogHelper;
import com.knox.advancealgo.optm.operations.Operation;

public class OpLogHelper {
    public static void d(String msg, Operation... ops) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Operation op : ops) {
            sb.append("\n op" + i + ": " + op);
            i++;
        }
        LogHelper.d(msg + sb);
    }

    public static void e(String msg, Operation... ops) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Operation op : ops) {
            sb.append("\n op" + i + ": " + op);
            i++;
        }
        LogHelper.e(msg + sb);
    }
}
