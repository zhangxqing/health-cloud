package com.health.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.health.system.domain.SysDept;
import com.health.system.domain.SysRole;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangqing
 * @version V1.0
 * @Description: 在这里简单描述该类的作用
 * @Copyright: Copyright (c) 2018
 * @company TODO广州易联众睿图信息技术有限公司
 * @date 27/4/2020 9:24 PM
 */
@Data
public class SysUserDto implements Serializable {

    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐加密
     */
    private String salt;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 最后登陆IP
     */
    private String loginIp;

    /**
     * 最后登陆时间
     */
    private Date loginDate;

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

    private String searchValue;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    private String beginTime;

    private String endTime;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
