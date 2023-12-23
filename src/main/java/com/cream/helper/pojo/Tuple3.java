package com.cream.helper.pojo;

/**
 * 三元组
 *
 * @param <V1>
 * @param <V2>
 * @param <V3>
 */
public class Tuple3<V1, V2, V3> {
    public final V1 v1;
    public final V2 v2;
    public final V3 v3;

    public Tuple3(V1 v1, V2 v2, V3 v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }
}
