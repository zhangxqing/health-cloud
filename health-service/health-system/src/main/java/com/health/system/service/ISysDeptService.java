package com.health.system.service;


import com.health.system.domain.dto.SysDeptDto;

import java.util.List;
import java.util.Set;

/**
 * 部门管理 服务层
 *
 * @author ruoyi
 */
public interface ISysDeptService {
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    List<SysDeptDto> selectDeptList(SysDeptDto dept);

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    int deleteDeptById(Long deptId);

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    int insertDept(SysDeptDto dept);

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    int updateDept(SysDeptDto dept);

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    SysDeptDto selectDeptById(Long deptId);


    /**
     * 根据角色ID查询部门编号
     *
     * @param roleId 角色编号
     * @return 部门编号
     */
    Set<String> roleDeptIds(Long roleId);
}
