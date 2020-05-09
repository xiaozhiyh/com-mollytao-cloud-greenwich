package com.mollytao.cloud.permission.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.mollytao.cloud"})
public class PermissionAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PermissionAdminApplication.class, args);
    }

}
