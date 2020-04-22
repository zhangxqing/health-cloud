package com.health.common.auth.aspect;

import com.health.common.auth.annotation.HasPermissions;
import com.health.common.constant.Constants;
import com.health.common.exception.ForbiddenException;
import com.health.common.utils.ServletUtils;
import com.health.system.feign.RemoteMenuService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Optional;

@Aspect
@Component
@Slf4j
public class PreAuthorizeAspect {
    @Autowired
    private RemoteMenuService sysMenuClient;

    @Around("@annotation(com.health.common.auth.annotation.HasPermissions)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        HasPermissions annotation = method.getAnnotation(HasPermissions.class);
        if (annotation == null) {
            return point.proceed();
        }
        String authority = new StringBuilder(annotation.value()).toString();
        if (has(authority)) {
            return point.proceed();
        } else {
            throw new ForbiddenException();
        }
    }

    private boolean has(String authority) {
        // 用超管帐号方便测试，拥有所有权限
        HttpServletRequest request = ServletUtils.getRequest();
        String tmpUserKey = request.getHeader(Constants.CURRENT_ID);
        if (Optional.ofNullable(tmpUserKey).isPresent()) {
            Long userId = Long.valueOf(tmpUserKey);
            log.debug("userId:{}", userId);
            if (userId == 1L) {
                return true;
            }
            return sysMenuClient.selectPermsByUserId(userId).stream().anyMatch(authority::equals);
        }
        return false;
    }
}
