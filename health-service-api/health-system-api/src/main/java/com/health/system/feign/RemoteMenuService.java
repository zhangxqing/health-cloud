package com.health.system.feign;

import com.health.system.feign.factory.RemoteMenuFallbackFactory;
import com.ruoyi.common.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;


/**
 * 菜单 Feign服务层
 * @author zq
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteMenuFallbackFactory.class)
public interface RemoteMenuService {

    @GetMapping("menu/perms/{userId}")
    Set<String> selectPermsByUserId(@PathVariable("userId") Long userId);
}
