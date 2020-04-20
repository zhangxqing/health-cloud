package com.health.system.feign.factory;

import com.health.system.domain.SysLogininfor;
import com.health.system.domain.SysOperLog;
import com.health.system.feign.RemoteLogService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService>
{
    @Override
    public RemoteLogService create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new RemoteLogService()
        {
            @Override
            public void insertOperlog(SysOperLog operLog)
            {
            }

            @Override
            public void insertLoginlog(SysLogininfor logininfor)
            {
            }
        };
    }
}
