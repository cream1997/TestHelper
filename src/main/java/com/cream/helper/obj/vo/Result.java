package com.cream.helper.obj.vo;

import lombok.Getter;

@Getter
public class Result<T> {

    private final T data;
    private final Status status;
    private final String info;

    private Result(T data, Status status, String info) {
        this.status = status;
        this.data = data;
        this.info = info;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data, Status.SUCCESS, null);
    }

    public static <T> Result<T> fail(T data, String errorInfo) {
        return new Result<>(data, Status.ERROR, errorInfo);
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
