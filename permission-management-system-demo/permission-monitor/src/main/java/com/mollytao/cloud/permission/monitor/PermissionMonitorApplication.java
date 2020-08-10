package com.mollytao.cloud.permission.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class PermissionMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PermissionMonitorApplication.class, args);
    }

}
