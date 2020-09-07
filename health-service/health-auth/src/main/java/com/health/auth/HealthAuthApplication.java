package com.health.auth;

import com.health.common.annotation.EnableHyFeignClients;
import com.health.common.utils.DefaultProfileUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author zq
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableHyFeignClients
@ComponentScan(value = {"com.health.*"})
public class HealthAuthApplication {
    private static final Logger log = LoggerFactory.getLogger(HealthAuthApplication.class);

    private final Environment env;

    public HealthAuthApplication(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(HealthAuthApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        DefaultProfileUtil.logApplicationStartup(env);
    }


    @PostConstruct
    public void initApplication() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains("dev") && activeProfiles.contains("pro")) {
            log.error("您的应用程序配置错误!它不应该运行,同时拥有‘dev’和‘prod’配置文件");
        }
        if (activeProfiles.contains("dev") && activeProfiles.contains("cloud")) {
            log.error("您的应用程序配置错误!它不应该同时运行‘dev’和‘cloud’配置文件。" );
        }
    }
}
