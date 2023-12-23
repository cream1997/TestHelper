package com.cream.helper.core.cmd;

import java.util.ArrayList;
import java.util.List;

public class CommandGroup {
    
    private final long id;

    private final String groupName;
    /**
     * 所有子命令
     */
    private final List<Long> commands = new ArrayList<>();

    public CommandGroup(long id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }
}
