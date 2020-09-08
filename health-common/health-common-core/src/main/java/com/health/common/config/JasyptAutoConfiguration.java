package com.health.common.config;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zq
 */
@Configuration
@EnableEncryptableProperties
public class JasyptAutoConfiguration {
    public JasyptAutoConfiguration() {
    }

    @Bean({"jasyptStringEncryptor"})
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("HEALTHPWDYML@!123");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    @Bean(name = {"encryptablePropertyDetector"})
    public EncryptablePropertyDetector encryptablePropertyDetector() {
        return new JasyptAutoConfiguration.MyEncryptablePropertyDetector();
    }

    private static class MyEncryptablePropertyDetector implements EncryptablePropertyDetector {
        private MyEncryptablePropertyDetector() {
        }

        @Override
        public boolean isEncrypted(String value) {
            return value != null ? value.startsWith("HEALTHCODER@") : false;
        }

        @Override
        public String unwrapEncryptedValue(String value) {
            return value.substring("HEALTHCODER@".length());
        }
    }
}
