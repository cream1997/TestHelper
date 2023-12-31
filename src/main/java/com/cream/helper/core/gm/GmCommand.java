package com.cream.helper.core.gm;

import com.cream.helper.utils.NullUtil;

import java.util.*;

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

        if (NullUtil.isEmpty(paramsAdder.getParams())) {
            throw new RuntimeException("设置参数异常（参数为空时，应使用一个参数的构造器创建枚举）");
        }
        params.addAll(paramsAdder.getParams());
    }

    private static final Map<String, GmCommand> allGmCmd;

    static {
        Map<String, GmCommand> allGmCmdMap = new HashMap<>();
        for (GmCommand value : GmCommand.values()) {
            allGmCmdMap.put(value.name(), value);
        }
        // 外部不可修改
        allGmCmd = Collections.unmodifiableMap(allGmCmdMap);
    }

    public static Collection<GmCommand> getAllGmCmd() {
        return allGmCmd.values();
    }
}
