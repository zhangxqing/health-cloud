package com.health.system.service;


import com.health.system.domain.Districts;

import java.util.List;

/**
 * 地区 服务层
 *
 * @author ruoyi
 * @date 2018-12-19
 */
public interface IDistrictsService {

    /**
     * 查询地区列表
     *
     * @param districts 地区信息
     * @return 地区集合
     */
    List<Districts> selectDistrictsList(Districts districts);

    /**
     * 新增地区
     *
     * @param districts 地区信息
     * @return 结果
     */
    int insertDistricts(Districts districts);

    /**
     * 修改地区
     *
     * @param districts 地区信息
     * @return 结果
     */
    int updateDistricts(Districts districts);

    /**
     * 删除地区信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteDistrictsByIds(String ids);

}
