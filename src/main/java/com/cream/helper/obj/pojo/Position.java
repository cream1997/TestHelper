package com.cream.helper.obj.pojo;

public class Position {
    public final String mapName;
    public final int line;
    public final XY xy;

    public Position(String mapName, int line, int x, int y) {
        this.mapName = mapName;
        this.line = line;
        this.xy = new XY(x, y);
    }
}
