package com.health.system.service;

import com.health.common.core.domain.Ztree;
import com.health.system.domain.SysMenu;
import com.health.system.domain.SysRole;
import com.health.system.domain.dto.SysUserDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单 业务层
 *
 * @author ruoyi
 */
public interface ISysMenuService {
    /**
     * 根据用户ID查询菜单
     *
     * @param userDto 用户信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenusByUser(SysUserDto userDto);

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectPermsByUserId(Long userId);


    /**
     * 根据角色ID查询菜单ID
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    List<SysMenu> selectMenuIdsByRoleId(Long roleId);

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    int deleteMenuById(Long menuId);

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    SysMenu selectMenuById(Long menuId);

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int insertMenu(SysMenu menu);

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    int updateMenu(SysMenu menu);
}
