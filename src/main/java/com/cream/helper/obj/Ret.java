package com.cream.helper.obj;

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

    public static <T> Ret<T> err(Status status) {
        return new Ret<>(null, status, status.desc);
    }

    public enum Status {
        SUCCESS(0),
        ERROR(1),
        NotLoginAccount(2, "未登录Account"),
        ;
        public final int value;
        public final String desc;

        Status(int value) {
            this.value = value;
            this.desc = null;
        }

        Status(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }
}
