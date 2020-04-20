package com.health.system.feign;

import com.health.common.constant.ServiceNameConstants;
import com.health.system.feign.factory.RemoteLogFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.health.system.domain.SysLogininfor;
import com.health.system.domain.SysOperLog;


/**
 * 日志Feign服务层
 * @author zq
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService {

    @PostMapping("operLog/save")
    void insertOperlog(@RequestBody SysOperLog operLog);

    @PostMapping("logininfor/save")
    void insertLoginlog(@RequestBody SysLogininfor logininfor);
}
