package com.health.auth;

import com.health.common.annotation.EnableHyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zq
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableHyFeignClients
@ComponentScan(value = {"com.health.*"})
public class HealthAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthAuthApplication.class, args);
    }

}
