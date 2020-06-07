package com.health.system.resolver;

import com.health.common.annotation.LoginUser;
import com.health.common.constant.Constants;
import com.health.system.domain.dto.SysUserDto;
import com.health.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 * @author zq
 */
@Configuration
public class LoginUserHandlerResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private ISysUserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(SysUserDto.class)
                && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory factory) {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        // 获取用户ID
        assert request != null;
        Long userid = Long.valueOf(request.getHeader(Constants.CURRENT_ID));
        return userService.selectUserById(userid);
    }
}
