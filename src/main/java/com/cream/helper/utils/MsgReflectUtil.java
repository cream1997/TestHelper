package com.cream.helper.utils;

import com.cream.helper.core.net.msg.base.Message;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

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
}
