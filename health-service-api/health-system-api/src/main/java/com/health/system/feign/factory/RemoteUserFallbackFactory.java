package com.health.system.feign.factory;

import com.health.common.core.domain.R;
import com.health.system.domain.SysUser;
import com.health.system.feign.RemoteUserService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author zq
 */
@Slf4j
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {
    @Override
    public RemoteUserService create(Throwable throwable) {
        log.error(throwable.getMessage());
        return new RemoteUserService() {
            @Override
            public SysUser selectSysUserByUsername(String username) {
                return null;
            }

            @Override
            public R updateUserLoginRecord(SysUser user) {
                return R.error();
            }

            @Override
            public SysUser selectSysUserByUserId(long userId) {
                SysUser user = new SysUser();
                user.setUserId(0L);
                user.setLoginName("no user");
                return user;
            }

            @Override
            public Set<Long> selectUserIdsHasRoles(String roleId) {
                return null;
            }

            @Override
            public Set<Long> selectUserIdsInDepts(String deptIds) {
                return null;
            }
        };
    }
}
