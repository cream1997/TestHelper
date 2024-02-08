package com.cream.helper.obj.common;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class Map<K, V> extends HashMap<K, V> {
    private static final AtomicLong IDX = new AtomicLong(0);

    @Getter
    private final long id;

    public Map() {
        this.id = IDX.incrementAndGet();
    }

    public Map(java.util.Map<? extends K, ? extends V> m) {
        super(m);
        this.id = IDX.incrementAndGet();
    }
}
