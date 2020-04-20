package com.health.system.feign;

import com.health.system.domain.SysRole;
import com.health.system.feign.factory.RemoteRoleFallbackFactory;
import com.ruoyi.common.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 角色 Feign服务层
 * @author zq
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteRoleFallbackFactory.class)
public interface RemoteRoleService {
    @GetMapping("role/get/{roleId}")
    SysRole selectSysRoleByRoleId(@PathVariable("roleId") long roleId);
}
