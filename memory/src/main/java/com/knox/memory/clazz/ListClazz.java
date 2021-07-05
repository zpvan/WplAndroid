package com.knox.memory.clazz;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListClazz {
    private List<Integer> a;

    public ListClazz() {
        a = Stream.iterate(10, i -> i).limit(10240).collect(Collectors.toList());
    }
}
