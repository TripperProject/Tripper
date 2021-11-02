package com.tripper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tripper")
public class AdminApplication {
    public static void main(String[] args){
        SpringApplication.run(AdminApplication.class, args);
    }
}
