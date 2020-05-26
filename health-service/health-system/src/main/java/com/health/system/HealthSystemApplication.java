package com.health.system;

import com.health.common.annotation.EnableHyFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zq
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableHyFeignClients
@MapperScan("com.health.*.mapper")
@ComponentScan(value = {"com.health.*"})
@EnableDiscoveryClient
public class HealthSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthSystemApplication.class, args);
    }

}
