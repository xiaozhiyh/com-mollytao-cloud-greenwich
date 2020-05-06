package com.mollytao.cloud.greenwich.monitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class ReadByAnnotationController {

    // 注入配置
    @Value("${server.port}")
    private String port;

    @GetMapping("/port/from/value")
    public String portFromValue() {
        return port;
    }
}
