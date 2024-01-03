package com.cream.helper.core.exe.constant.gm;

import com.cream.helper.utils.NullUtil;

import java.util.*;


public enum GmCmdType {

    AddExp("加经验", ParamsAdder.setParam("经验值")),
    SetLevel("设置等级", ParamsAdder.setParam("等级值")),
    LevelUp("升级", ParamsAdder.setParam("升级值"));
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

    GmCmdType(String desc) {
        this.desc = desc;
    }

    GmCmdType(String desc, ParamsAdder paramsAdder) {
        this.desc = desc;

        if (NullUtil.isEmpty(paramsAdder.getParams())) {
            throw new RuntimeException("设置参数异常（参数为空时，应使用一个参数的构造器创建枚举）");
        }
        params.addAll(paramsAdder.getParams());
    }

    private static final Map<String, GmCmdType> allGmCmd;

    static {
        Map<String, GmCmdType> allGmCmdMap = new HashMap<>();
        for (GmCmdType value : GmCmdType.values()) {
            allGmCmdMap.put(value.name(), value);
        }
        // 外部不可修改
        allGmCmd = Collections.unmodifiableMap(allGmCmdMap);
    }

    public static Collection<GmCmdType> getAllGmCmd() {
        return allGmCmd.values();
    }
}
