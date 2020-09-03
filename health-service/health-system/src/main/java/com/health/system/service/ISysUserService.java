package com.health.system.service;

import com.health.system.domain.SysUser;
import com.health.system.domain.dto.SysUserDto;

import java.util.List;
import java.util.Set;

/**
 * 用户 业务层
 *
 * @author ruoyi
 */
public interface ISysUserService {
    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUserDto> selectUserList(SysUserDto user);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUserDto selectUserByLoginName(String userName);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    SysUserDto selectUserById(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    int deleteUserByIds(String ids) throws Exception;

    /**
     * 保存用户信息
     *
     * @param userDto 用户信息
     * @return 结果
     */
    int insertUser(SysUserDto userDto);

    /**
     * 保存用户信息
     *
     * @param userDto 用户信息
     * @return 结果
     */
    int updateUser(SysUserDto userDto);

    /**
     * 修改用户详细信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUserInfo(SysUserDto user);

    /**
     * 修改用户密码信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int resetUserPwd(SysUserDto user);

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    String checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    String checkPhoneUnique(SysUserDto user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    String checkEmailUnique(SysUserDto user);

    /**
     * 用户状态修改
     *
     * @param user 用户信息
     * @return 结果
     */
    int changeStatus(SysUserDto user);

    /**
     * 查询拥有当前角色的所有用户
     *
     * @param roleIds
     * @return
     * @author zmr
     */
    Set<Long> selectUserIdsHasRoles(Long[] roleIds);

    /**
     * 查询所有当前部门中的用户
     *
     * @param deptIds
     * @return
     * @author zmr
     */
    Set<Long> selectUserIdsInDepts(Long[] deptIds);
}
