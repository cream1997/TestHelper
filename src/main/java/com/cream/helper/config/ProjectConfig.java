package com.cream.helper.config;


import com.cream.helper.constant.GamePlatform;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectConfig {

    private boolean useMockComponent;

    private final GamePlatform gamePlatform;

    public ProjectConfig(@Value("${project.game-platform}") String gamePlatform) {
        this.gamePlatform = GamePlatform.of(gamePlatform);
        if (gamePlatform == null) {
            System.exit(1);
        }
    }
}
