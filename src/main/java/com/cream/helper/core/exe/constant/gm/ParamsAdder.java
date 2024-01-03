package com.cream.helper.core.exe.constant.gm;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 同包内访问
 */
class ParamsAdder {

    /**
     * 参数列表
     */
    private final List<Param> params = new ArrayList<>();

    public List<Param> getParams() {
        return new ArrayList<>(params);
    }

    private ParamsAdder() {
    }

    public static ParamsAdder setParam(String desc) {
        return setParam(desc, ParamType.Int);
    }

    public static ParamsAdder setParam(String desc, ParamType paramType) {
        ParamsAdder paramsAdder = new ParamsAdder();
        paramsAdder.addParam(desc, paramType);
        return paramsAdder;
    }

    public ParamsAdder addParam(String desc) {
        return addParam(desc, ParamType.Int);
    }

    public ParamsAdder addParam(String desc, ParamType paramType) {
        if (StringUtils.isBlank(desc) || paramType == null) {
            throw new RuntimeException("参数设置错误");
        }
        this.params.add(new Param(desc, paramType));
        return this;
    }
}
