package com.knox.memory.util;

import android.content.Context;

public class ContextClazz {
    private static Context sContext;

    public static void setContext(Context context) {
        sContext = context;
    }
}
