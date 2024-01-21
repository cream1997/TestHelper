package com.cream.helper.core.net.msg.base;

import com.cream.helper.core.net.common.constant.MsgMeta;
import com.cream.helper.core.net.common.constant.MsgType;
import com.cream.helper.utils.Util;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.function.Supplier;

@Slf4j
@Getter
public abstract class Message<T extends GeneratedMessageV3> {
    private int msgId;
    private MsgType type;
    /**
     * 消息体（内容）
     */
    private final T data;

    public Message(Supplier<T> dataBuilder) {
        this.data = dataBuilder == null ? null : dataBuilder.get();
        init();
    }


    /**
     * 留给解码器反射使用
     */
    private void setData(byte[] data) {
        try {
            Field dataField = this.getClass().getField("data");
            dataField.setAccessible(true);
            dataField.set(this, parseData(data));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 即使通过元信息已经能够获取msgId和type了，也仍然需要保留这两个字段；
     * 否则预计序列化、反序列化会出问题
     */
    public final void init() {
        MsgMeta msgMeta = getMsgMeta();
        if (msgMeta == null) {
            log.error("消息元信息未定义 class:{}", this.getClass());
            Util.errExit();
        }
        this.msgId = msgMeta.msgId;
        this.type = msgMeta.msgType;
    }

    public abstract MsgMeta getMsgMeta();

    public abstract Class<T> getDataClass();

    @SuppressWarnings("unchecked")
    private T parseData(byte[] dataBytes) {
        if (dataBytes == null) {
            return null;
        }
        Class<T> dataClass = getDataClass();
        T dataDefaultInstance;
        try {
            dataDefaultInstance = dataClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Parser<? extends GeneratedMessageV3> parserForType = dataDefaultInstance.getParserForType();
        try {
            GeneratedMessageV3 protoInstance = parserForType.parseFrom(dataBytes);
            return (T) protoInstance;
        } catch (InvalidProtocolBufferException e) {
            log.error("解析proto字节出错 {}", Util.getStackTrace(), e);
        }
        return null;
    }
}
