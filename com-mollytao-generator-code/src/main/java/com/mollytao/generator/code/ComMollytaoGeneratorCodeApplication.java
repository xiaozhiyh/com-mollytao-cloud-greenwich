package com.mollytao.generator.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mollytao.generator.code.**"})
public class ComMollytaoGeneratorCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComMollytaoGeneratorCodeApplication.class, args);
    }

}
