package com.mollytao.cloud.greenwich.monitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadByEnvironmentController {

    // 注入对象
    @Autowired
    private Environment environment;

    @GetMapping("/port/from/environment")
    public String port() {
        return environment.getProperty("server.port");
    }
}
