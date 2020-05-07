package com.health.system.service;

import com.health.system.domain.SysUserOnline;

import java.util.Date;
import java.util.List;

/**
 * 在线用户 服务层
 *
 * @author ruoyi
 */
public interface ISysUserOnlineService {
    /**
     * 通过会话序号查询信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    SysUserOnline selectOnlineById(String sessionId);
}
