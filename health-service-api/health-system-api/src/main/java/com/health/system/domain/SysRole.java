package com.health.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 角色表 sys_role
 *
 * @author zq
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId
    @Excel(name = "角色序号")
    private Long roleId;

    /**
     * 角色名称
     */
    @Excel(name = "角色名称")
    private String roleName;

    /**
     * 角色权限
     */
    @Excel(name = "角色权限")
    private String roleKey;

    /**
     * 角色排序
     */
    @Excel(name = "角色排序")
    private String roleSort;

    /**
     * 数据范围（1：所有数据权限；2：自定数据权限）
     */
    @Excel(name = "数据范围", readConverterExp = "1=所有数据权限,2=自定义数据权限")
    private String dataScope;

    /**
     * 角色状态（0正常 1停用）
     */
    @Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

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
}
