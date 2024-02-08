package com.cream.helper.obj.common;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class List<E> extends ArrayList<E> {

    private static final AtomicLong IDX = new AtomicLong(0);

    @Getter
    private final long id;

    public List() {
        this.id = IDX.incrementAndGet();
    }

    public List(Collection<? extends E> c) {
        super(c);
        this.id = IDX.incrementAndGet();
    }
}
