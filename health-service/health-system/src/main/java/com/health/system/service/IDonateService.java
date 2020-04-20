package com.health.system.service;

import com.health.system.domain.Donate;

import java.util.List;

/**
 * 捐赠 服务层
 */
public interface IDonateService {
    /**
     * 查询捐赠列表
     *
     * @param donate 捐赠信息
     * @return 捐赠集合
     */
    public List<Donate> selectDistrictsList(Donate donate);
}
