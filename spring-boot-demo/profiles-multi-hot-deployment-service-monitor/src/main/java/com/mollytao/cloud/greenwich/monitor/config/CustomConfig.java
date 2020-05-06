package com.mollytao.cloud.greenwich.monitor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// 自定义配置类
@ConfigurationProperties(prefix = "com.mollytao")
@Component
public class CustomConfig {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
