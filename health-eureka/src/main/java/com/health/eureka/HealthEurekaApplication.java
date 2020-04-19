package com.health.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class HealthEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthEurekaApplication.class, args);
    }

}
