package com.health.system.feign.factory;

import com.health.system.domain.SysRole;
import com.health.system.feign.RemoteRoleService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zq
 */
@Slf4j
@Component
public class RemoteRoleFallbackFactory implements FallbackFactory<RemoteRoleService> {

    @Override
    public RemoteRoleService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteRoleService() {
            @Override
            public SysRole selectSysRoleByRoleId(long roleId) {
                return null;
            }
        };
    }
}
