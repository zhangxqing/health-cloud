package com.health.system.service;

import com.health.system.domain.dto.SysConfigDto;

import java.util.List;

/**
 * 参数配置 服务层
 *
 * @author ruoyi
 */
public interface ISysConfigService {
    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    SysConfigDto selectConfigById(Long configId);

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数键值
     */
    String selectConfigByKey(String configKey);

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    List<SysConfigDto> selectConfigList(SysConfigDto config);

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    int insertConfig(SysConfigDto config);

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    int updateConfig(SysConfigDto config);

    /**
     * 批量删除参数配置信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteConfigByIds(String ids);

    /**
     * @param key
     * @param configValue
     * @author zmr
     */
    int updateValueByKey(String key, String configValue);
}
