package com.health.auth.controller;

import com.health.auth.form.LoginForm;
import com.health.auth.service.AccessTokenService;
import com.health.auth.service.SysLoginService;
import com.health.common.core.domain.JsonResult;
import com.health.system.domain.SysUser;
import com.health.system.domain.dto.SysUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zq
 */
@RestController
public class TokenController {
    @Autowired
    private AccessTokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public JsonResult login(@RequestBody LoginForm form) {
        // 用户登录
        SysUserDto userDto = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return JsonResult.ok(tokenService.createToken(userDto));
    }

    @PostMapping("logout")
    public JsonResult logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser user = tokenService.queryByToken(token);
        if (null != user) {
            sysLoginService.logout(user.getLoginName());
            tokenService.expireToken(user.getUserId());
        }
        return JsonResult.ok();
    }
}
