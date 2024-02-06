package com.cream.helper.simpletest;

import com.alibaba.fastjson2.JSON;
import com.cream.helper.core.net.msg.req.ReqLoginMsg;
import com.cream.helper.core.net.proto.clazz.CommonProto;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class SimpleTest {

    @Test
    public void test() {
        ReqLoginMsg reqLoginMsg = new ReqLoginMsg(() -> null);
        Class<CommonProto.LoginReq> dataClass = reqLoginMsg.getDataClass();
        try {
            Constructor<CommonProto.LoginReq> declaredConstructor = dataClass.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            CommonProto.LoginReq data = declaredConstructor.newInstance();
            System.out.println(JSON.toJSONString(data));
            Field[] fields = data.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().endsWith("_")) {
                    System.out.println(field.getName());
                    System.out.println(field.getType());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
