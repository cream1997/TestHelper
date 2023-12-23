package com.cream.helper.entity.cmd;

public class RightValueExpression {
    /**
     * 值提供来源
     */
    private final int valueProviderType;
    /**
     * 读值表达式
     */
    private final String readValueExpression;


    public RightValueExpression(int valueProviderType, String readValueExpression) {
        this.valueProviderType = valueProviderType;
        this.readValueExpression = readValueExpression;
    }

    public enum ValueProviderType {
        GlobalObj(1),
        ResponseMsg(2);

        private final int value;

        ValueProviderType(int value) {
            this.value = value;
        }
    }
}
