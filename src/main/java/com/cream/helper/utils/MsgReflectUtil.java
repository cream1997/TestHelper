package com.cream.helper.utils;

import com.cream.helper.core.net.handler.base.MsgHandler;
import com.cream.helper.core.net.msg.base.Message;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

@Slf4j
public class MsgReflectUtil {

    @SuppressWarnings("unchecked")
    public static Message<?> newMsgInstance(Class<? extends Message<?>> msgClass) {
        Constructor<? extends Message<?>> constructor = (Constructor<? extends Message<?>>) msgClass.getConstructors()[0];
        constructor.setAccessible(true);
        Object[] params = new Object[constructor.getParameterCount()];
        try {
            if (params.length > 0) {
                return constructor.newInstance(params);
            } else {
                return constructor.newInstance();
            }
        } catch (Exception e) {
            log.error("反射创建Message实例异常", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static int getMsgId(MsgHandler<?> msgHandler) {
        ParameterizedType parameterizedType = (ParameterizedType) msgHandler.getClass().getGenericSuperclass();
        Class<Message<?>> msgClass = (Class<Message<?>>) parameterizedType.getActualTypeArguments()[0];
        try {
            Message<?> message = MsgReflectUtil.newMsgInstance(msgClass);
            return message.getMsgId();
        } catch (Exception e) {
            log.error("获取msgId异常 msgHandler:{}", msgHandler.getClass());
            throw e;
        }
    }


}
