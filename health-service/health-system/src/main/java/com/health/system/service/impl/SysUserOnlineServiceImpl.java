package com.health.system.service.impl;

import com.health.common.utils.DateUtils;
import com.health.common.utils.StringUtils;
import com.health.system.domain.SysUserOnline;
import com.health.system.mapper.SysUserOnlineMapper;
import com.health.system.service.ISysUserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 在线用户 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysUserOnlineServiceImpl implements ISysUserOnlineService {
    @Autowired
    private SysUserOnlineMapper userOnlineDao;

    /**
     * 通过会话序号查询信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public SysUserOnline selectOnlineById(String sessionId) {
        return userOnlineDao.selectOnlineById(sessionId);
    }
}
