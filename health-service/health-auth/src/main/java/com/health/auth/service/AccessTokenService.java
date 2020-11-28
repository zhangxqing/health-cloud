package com.health.auth.service;

import cn.hutool.core.util.IdUtil;
import com.health.common.constant.Constants;
import com.health.common.redis.annotation.RedisEvict;
import com.health.common.redis.util.RedisUtils;
import com.health.system.domain.dto.SysUserDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("accessTokenService")
public class AccessTokenService {
    @Autowired
    private RedisUtils redis;

    /**
     * 12小时后过期
     */
    private final static long EXPIRE = 12 * 60 * 60;

    private final static String ACCESS_TOKEN = Constants.ACCESS_TOKEN;

    private final static String ACCESS_USERID = Constants.ACCESS_USERID;

    public SysUserDto queryByToken(String token) {
        return redis.get(ACCESS_TOKEN + token, SysUserDto.class);
    }

    @RedisEvict(key = "user_perms", fieldKey = "#sysUserDto.userId")
    public Map<String, Object> createToken(SysUserDto sysUserDto) {
        // 生成token
        String token = IdUtil.fastSimpleUUID();
        // 保存或更新用户token
        Map<String, Object> map = new HashMap<>(16);
        map.put("userId", sysUserDto.getUserId());
        map.put("token", token);
        map.put("expire", EXPIRE);
        // expireToken(userId);
        redis.set(ACCESS_TOKEN + token, sysUserDto, EXPIRE);
        redis.set(ACCESS_USERID + sysUserDto.getUserId(), token, EXPIRE);
        return map;
    }

    public void expireToken(long userId) {
        String token = redis.get(ACCESS_USERID + userId);
        if (StringUtils.isNotBlank(token)) {
            redis.delete(ACCESS_USERID + userId);
            redis.delete(ACCESS_TOKEN + token);
        }
    }
}
