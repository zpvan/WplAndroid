package com.knox.advancealgo.optm.model;

import com.knox.advancealgo.optm.common.LogHelper;

public class ModelLogHelper {

    public static void e(String msg, SharedString... strs) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (SharedString str : strs) {
            sb.append("\n str" + i + ": " + str.get());
            i++;
        }
        LogHelper.e(msg + sb);
    }
}
