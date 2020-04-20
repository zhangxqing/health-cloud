package com.health.system.feign;

import com.health.common.constant.ServiceNameConstants;
import com.health.system.domain.SysDept;
import com.health.system.feign.factory.RemoteDeptFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户 Feign服务层
 *
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteDeptFallbackFactory.class)
public interface RemoteDeptService {

    @GetMapping("dept/get/{deptId}")
    SysDept selectSysDeptByDeptId(@PathVariable("deptId") long deptId);
}
