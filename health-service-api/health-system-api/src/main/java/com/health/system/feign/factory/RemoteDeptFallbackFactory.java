package com.health.system.feign.factory;

import com.health.system.domain.SysDept;
import com.health.system.feign.RemoteDeptService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zq
 */
@Slf4j
@Component
public class RemoteDeptFallbackFactory implements FallbackFactory<RemoteDeptService> {

    @Override
    public RemoteDeptService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteDeptService() {

            @Override
            public SysDept selectSysDeptByDeptId(long deptId) {
                return null;
            }
        };
    }
}
