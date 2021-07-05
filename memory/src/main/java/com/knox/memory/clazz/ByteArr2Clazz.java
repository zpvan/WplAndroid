package com.knox.memory.clazz;

public class ByteArr2Clazz {

    private byte[] a;

    public ByteArr2Clazz() {
        a = new byte[4096*10];
        for (byte b : a) {
            b = 10;
        }
    }
}
