package com.cream.helper.config;


import com.cream.helper.constant.GamePlatform;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectConfig {

    private boolean useMockComponent;

    private GamePlatform gamePlatform;

    public void setGamePlatform(String platformName) {
        this.gamePlatform = GamePlatform.of(platformName);
    }
}
