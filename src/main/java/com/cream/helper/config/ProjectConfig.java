package com.cream.helper.config;


import com.cream.helper.constant.GamePlatform;
import com.cream.helper.utils.Util;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectConfig {

    private GamePlatform gamePlatform;

    private boolean useMockComponent;

    private int mockServerPort;


    public void setGamePlatform(String gamePlatform) {
        this.gamePlatform = GamePlatform.of(gamePlatform);
        if (gamePlatform == null) {
            Util.errExit();
        }
    }
}
