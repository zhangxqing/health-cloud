package com.health.system.domain.dto;

import com.health.system.domain.SysDept;
import com.health.system.domain.SysRole;
import com.health.system.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

/**
 * @author zhangqing
 * @version V1.0
 * @Description: 在这里简单描述该类的作用
 * @Copyright: Copyright (c) 2018
 * @company TODO广州易联众睿图信息技术有限公司
 * @date 27/4/2020 9:24 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDto extends SysUser {

    /**
     * 部门对象
     */
    private SysDept dept;

    private List<SysRole> roles;
    /**
     * 部门父ID
     */
    private Long parentId;

    /**
     * 角色组
     */
    private List<Long> roleIds;

    /**
     * 岗位组
     */
    private Long[] postIds;

    private Set<String> buttons;
}
