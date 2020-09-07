package com.health.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * 类来加载要用作默认值的Spring配置文件
 * 当没有<code>spring.profiles时。活动</code>设置在环境中或作为命令行参数。
 * 如果该值在<code>应用程序中不可用。yml</code>则<code>dev</code> profile将被用作缺省值。
 */
public final class DefaultProfileUtil {

    private static final Logger log = LoggerFactory.getLogger(DefaultProfileUtil.class);

    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    private DefaultProfileUtil() {
    }

    /**
     * 设置一个默认值，当没有配置配置文件时使用。
     *
     * @param app the Spring application
     */
    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap<>(16);
        /*
        * 没有定义其他配置文件时使用的默认配置文件
        * 这不能在<code>应用程序中设置。yml > < /代码文件。
        * 参见https://github.com/spring-projects/spring-boot/issues/1219
        */
        defProperties.put(SPRING_PROFILE_DEFAULT, "dev");
        app.setDefaultProperties(defProperties);
    }

    /**
     * 获取应用程序的概要文件，否则获取默认概要文件。
     *
     * @param env spring environment
     * @return profiles
     */
    public static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) {
            return env.getDefaultProfiles();
        }
        return profiles;
    }

    public static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(contextPath)) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("无法确定主机名，使用“localhost”作为回退");
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}{}{}\n\t" +
                        "External: \t{}://{}:{}{}{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                serverPort,
                contextPath,
                "doc.html",
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                "doc.html",
                env.getActiveProfiles());
    }
}
