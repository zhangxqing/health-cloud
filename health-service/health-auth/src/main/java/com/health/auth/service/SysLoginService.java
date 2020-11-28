package com.health.auth.service;

import com.health.common.constant.Constants;
import com.health.common.constant.UserConstants;
import com.health.common.enums.UserStatus;
import com.health.common.exception.user.UserBlockedException;
import com.health.common.exception.user.UserDeleteException;
import com.health.common.exception.user.UserNotExistsException;
import com.health.common.exception.user.UserPasswordNotMatchException;
import com.health.common.log.publish.PublishFactory;
import com.health.common.utils.DateUtils;
import com.health.common.utils.IpUtils;
import com.health.common.utils.MessageUtils;
import com.health.common.utils.ServletUtils;
import com.health.system.domain.dto.SysUserDto;
import com.health.system.feign.RemoteUserService;
import com.health.system.util.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统登陆服务
 *
 * @author zq
 */
@Component
public class SysLoginService {

    @Autowired
    private RemoteUserService userService;

    /**
     * 登录
     */
    public SysUserDto login(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)) {
            PublishFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null"));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            PublishFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            PublishFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }
        // 查询用户信息
        SysUserDto userDto = userService.selectSysUserByUsername(username);
        if (userDto == null) {
            PublishFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists"));
            throw new UserNotExistsException();
        }
        if (UserStatus.DELETED.getCode().equals(userDto.getDelFlag())) {
            PublishFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.password.delete"));
            throw new UserDeleteException();
        }
        if (UserStatus.DISABLE.getCode().equals(userDto.getStatus())) {
            PublishFactory.recordLogininfor(username, Constants.LOGIN_FAIL,
                    MessageUtils.message("user.blocked", userDto.getRemark()));
            throw new UserBlockedException();
        }
        if (!PasswordUtil.matches(userDto, password)) {
            throw new UserPasswordNotMatchException();
        }
        PublishFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"));
        recordLoginInfo(userDto);
        return userDto;
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUserDto userDto) {
        userDto.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        userDto.setLoginDate(DateUtils.getNowDate());
        userService.updateUserLoginRecord(userDto);
    }

    public void logout(String loginName) {
        PublishFactory.recordLogininfor(loginName, Constants.LOGOUT, MessageUtils.message("user.logout.success"));
    }
}
