package com.health.system.feign;

import com.health.common.constant.ServiceNameConstants;
import com.health.common.core.domain.JsonResult;
import com.health.system.domain.SysUser;
import com.health.system.domain.dto.SysUserDto;
import com.health.system.feign.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * 用户 Feign服务层
 *
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {
    @GetMapping("user/get/{userId}")
    SysUser selectSysUserByUserId(@PathVariable("userId") long userId);

    @GetMapping("user/find/{username}")
    SysUserDto selectSysUserByUsername(@PathVariable("username") String username);

    @PostMapping("user/update/login")
    JsonResult updateUserLoginRecord(@RequestBody SysUserDto userDto);

    /**
     * 查询拥有当前角色的所有用户
     *
     * @param roleIds 角色id集合
     * @return
     * @author zmr
     */
    @GetMapping("user/hasRoles")
    Set<Long> selectUserIdsHasRoles(@RequestParam("roleIds") String roleIds);

    /**
     * 查询所有当前部门中的用户
     *
     * @param deptIds 部门id集合
     * @return
     * @author zmr
     */
    @GetMapping("user/inDepts")
    Set<Long> selectUserIdsInDepts(@RequestParam("deptIds") String deptIds);
}
