package com.cream.helper.core.gm;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.cream.helper.core.gm.ParamsAdder.setParam;


public enum GmCommand {

    AddExp("加经验", setParam("经验值")),
    SetLevel("设置等级", setParam("等级值")),
    LevelUp("升级", setParam("升级值"));
    /**
     * 描述说明
     */
    public final String desc;
    /**
     * 参数列表
     */
    private final List<Param> params = new ArrayList<>();

    public List<Param> getParams() {
        return new ArrayList<>(params);
    }

    GmCommand(String desc) {
        this.desc = desc;
    }

    GmCommand(String desc, ParamsAdder paramsAdder) {
        this.desc = desc;
        if (CollectionUtils.isEmpty(paramsAdder.getParams())) {
            throw new RuntimeException("设置参数异常（参数为空时，应使用一个参数的构造器创建枚举）");
        }
        params.addAll(paramsAdder.getParams());
    }
}
