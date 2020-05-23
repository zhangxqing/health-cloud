package com.health.system.domain.dto;

import com.health.system.domain.SysRole;
import com.health.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhangqing
 * @version V1.0
 * @Description: 在这里简单描述该类的作用
 * @Copyright: Copyright (c) 2018
 * @company TODO广州易联众睿图信息技术有限公司
 * @date 17/5/2020 2:30 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleDto extends SysRole {
    /**
     * 用户是否存在此角色标识 默认不存在
     */
    private boolean flag = false;

    /**
     * 菜单组
     */
    private List<Long> menuIds;

    /**
     * 部门组（数据权限）
     */
    private Long[] deptIds;

    private List<SysUser> users;
}
