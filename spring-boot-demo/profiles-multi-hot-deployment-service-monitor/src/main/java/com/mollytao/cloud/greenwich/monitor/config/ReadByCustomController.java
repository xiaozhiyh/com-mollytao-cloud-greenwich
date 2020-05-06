package com.mollytao.cloud.greenwich.monitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadByCustomController {

    @Autowired
    private CustomConfig customConfig;

    @GetMapping("/hello")
    public String hello() {
        return customConfig.getName();
    }
}
