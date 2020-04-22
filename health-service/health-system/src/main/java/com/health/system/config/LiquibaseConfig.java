package com.health.system.config;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author zq
 */
@Configuration
public class LiquibaseConfig {

    private static final Logger log = LoggerFactory.getLogger(LiquibaseConfig.class);
    private final DataSource dataSource;

    @Value("${spring.liquibase.change-log}")
    private String changeLog;
    @Value("${spring.liquibase.enabled}")
    private Boolean enabled;

    @Autowired
    public LiquibaseConfig(@Qualifier("dynamicDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SpringLiquibase liquibase() {
        log.info("grademed服务liquibase初始化成功");
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(changeLog);
        liquibase.setContexts("development,test,preproduction,production");
        //用来配置liquibase是否需要启动
        liquibase.setShouldRun(enabled);
        return liquibase;
    }

}
