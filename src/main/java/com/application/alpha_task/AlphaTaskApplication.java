package com.application.alpha_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AlphaTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlphaTaskApplication.class, args);
    }

}