package com.health.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.health.system.domain.Districts;

import java.util.List;

/**
 * 地区 数据层
 *
 * @author zq
 * @date 2018-12-19
 */
public interface DistrictsMapper extends BaseMapper<Districts> {
    /**
     * 查询地区信息
     *
     * @param id 地区ID
     * @return 地区信息
     */
    Districts selectDistrictsById(Integer id);

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
     * 删除地区
     *
     * @param id 地区ID
     * @return 结果
     */
    int deleteDistrictsById(Integer id);

    /**
     * 批量删除地区
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteDistrictsByIds(String[] ids);

}
