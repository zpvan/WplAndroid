package com.knox.memory.util;

import com.knox.memory.clazz.ByteArrClazz;

public class StaticClazz {
    private static ByteArrClazz sByteArrClazz;

    public static void setByteArrClazz(ByteArrClazz byteArrClazz) {
        sByteArrClazz = byteArrClazz;
    }
}
