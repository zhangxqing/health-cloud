package com.health.system.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户和角色关联 sys_user_role
 *
 * @author zq
 */
@Data
public class SysUserRole implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;
}
