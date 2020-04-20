package com.health.system.feign.factory;

import com.health.system.domain.SysRole;
import com.health.system.feign.RemoteRoleService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteRoleFallbackFactory implements FallbackFactory<RemoteRoleService>
{/* (non-Javadoc)
  * @see feign.hystrix.FallbackFactory#create(java.lang.Throwable)
  */
    @Override
    public RemoteRoleService create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new RemoteRoleService()
        {
            @Override
            public SysRole selectSysRoleByRoleId(long roleId)
            {
                return null;
            }
        };
    }
}
