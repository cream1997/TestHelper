package com.cream.helper.obj.vo;

import lombok.Getter;

@Getter
public class Ret<T> {

    private final T data;
    private final Status status;
    private final String info;

    private Ret(T data, Status status, String info) {
        this.status = status;
        this.data = data;
        this.info = info;
    }

    public static <T> Ret<T> ok(T data) {
        return new Ret<>(data, Status.SUCCESS, null);
    }

    public static <T> Ret<T> err(String errorInfo) {
        return new Ret<>(null, Status.ERROR, errorInfo);
    }

    public static <T> Ret<T> err(T data, String errorInfo) {
        return new Ret<>(data, Status.ERROR, errorInfo);
    }

    private enum Status {
        SUCCESS(0),
        ERROR(1);
        public final int value;

        Status(int value) {
            this.value = value;
        }
    }
}
