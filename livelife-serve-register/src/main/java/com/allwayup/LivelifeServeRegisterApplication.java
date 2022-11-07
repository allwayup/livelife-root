package com.allwayup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class LivelifeServeRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivelifeServeRegisterApplication.class, args);
    }
}