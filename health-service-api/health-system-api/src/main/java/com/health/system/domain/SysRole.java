package com.health.system.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色表 sys_role
 *
 * @author ruoyi
 */
@Entity
@Table(name = "sys_role")
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @Id
    @Column(name = "role_id")
    @Excel(name = "角色序号")
    private Long roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    @Excel(name = "角色名称")
    private String roleName;

    /**
     * 角色权限
     */
    @Column(name = "role_key")
    @Excel(name = "角色权限")
    private String roleKey;

    /**
     * 角色排序
     */
    @Column(name = "role_sort")
    @Excel(name = "角色排序")
    private String roleSort;

    /**
     * 数据范围（1：所有数据权限；2：自定数据权限）
     */
    @Column(name = "data_scope")
    @Excel(name = "数据范围", readConverterExp = "1=所有数据权限,2=自定义数据权限")
    private String dataScope;

    /**
     * 角色状态（0正常 1停用）
     */
    @Column(name = "status")
    @Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @Column(name = "del_flag")
    private String delFlag;

    /**
     * 用户是否存在此角色标识 默认不存在
     */
    @Transient
    private boolean flag = false;

    /**
     * 菜单组
     */
    @Transient
    private List<Long> menuIds;

    /**
     * 部门组（数据权限）
     */
    @Transient
    private Long[] deptIds;

    @ManyToMany(mappedBy = "roles")
    private List<SysUser> users;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(String roleSort) {
        this.roleSort = roleSort;
    }

    public String getStatus() {
        return status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }

    public Long[] getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(Long[] deptIds) {
        this.deptIds = deptIds;
    }

    public List<SysUser> getUsers() {
        return users;
    }

    @JsonBackReference
    public SysRole setUsers(List<SysUser> users) {
        this.users = users;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("roleId", getRoleId())
                .append("roleName", getRoleName())
                .append("roleKey", getRoleKey())
                .append("roleSort", getRoleSort())
                .append("dataScope", getDataScope())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
