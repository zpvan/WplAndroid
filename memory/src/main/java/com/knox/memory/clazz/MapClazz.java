package com.knox.memory.clazz;

import java.util.HashMap;
import java.util.Map;

public class MapClazz {
    private Map<String, String> a = new HashMap<>();

    public MapClazz() {
        for (int i = 0; i < 1000; i++) {
            a.put("key" + i, "value" + i);
        }
    }
}
