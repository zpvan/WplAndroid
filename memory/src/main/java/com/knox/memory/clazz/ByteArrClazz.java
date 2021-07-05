package com.knox.memory.clazz;

import java.util.stream.Stream;

public class ByteArrClazz {
    private Byte[] a;

    public ByteArrClazz() {
        a = Stream.iterate(10, i -> i).limit(4096* 10)
                .map(i -> Byte.valueOf(String.valueOf(i)))
                .toArray(Byte[]::new);
    }
}
