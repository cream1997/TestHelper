package com.cream.helper.obj.vo;

public class Result {

    private final Status status;
    private final Object data;

    private final String info;

    private Result(Status status, Object data, String info) {
        this.status = status;
        this.data = data;
        this.info = info;
    }

    public static Result success(Object data) {
        return new Result(Status.SUCCESS, data, null);
    }

    public static Result fail(Object data, String errorInfo) {
        return new Result(Status.ERROR, data, errorInfo);
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
