package com.cream.helper.simpletest;

import com.cream.helper.config.configuration.exception.RunErr;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    @Test
    public void test() {
        RunErr xxx = new RunErr("xxx");
        System.out.println(xxx.getMessage());
    }
}
