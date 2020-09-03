package com.health.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@ComponentScan(value = {"com.health.*"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HealthGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthGatewayApplication.class, args);
    }

}
