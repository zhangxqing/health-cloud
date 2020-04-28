package com.health.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.health.common.annotation.Excel;
import com.health.common.annotation.Excel.Type;
import com.health.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 *
 * @author zq
 */
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Excel(name = "用户序号", prompt = "用户编号")
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 部门ID
     */
    @Excel(name = "部门编号", type = Type.IMPORT)
    @Column(name = "dept_id",insertable=false,updatable=false)
    private Long deptId;

    /**
     * 登录名称
     */
    @Excel(name = "登录名称")
    @Column(name = "login_name")
    private String loginName;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    @Excel(name = "用户名称")
    private String userName;

    /**
     * 用户邮箱
     */
    @Column(name = "email")
    @Excel(name = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Column(name = "phonenumber")
    @Excel(name = "手机号码")
    private String phonenumber;

    /**
     * 用户性别
     */
    @Column(name = "sex")
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /**
     * 用户头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 盐加密
     */
    @Column(name = "salt")
    private String salt;

    /**
     * 帐号状态（0正常 1停用）
     */
    @Column(name = "status")
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @Column(name = "del_flag")
    private String delFlag;

    /**
     * 最后登陆IP
     */
    @Column(name = "login_ip")
    @Excel(name = "最后登陆IP", type = Type.EXPORT)
    private String loginIp;

    /**
     * 最后登陆时间
     */
    @Column(name = "login_date")
    @Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;

    /**
     * 部门对象
     */
    @OneToOne
    @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT)
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id")
    private SysDept dept;

    @ManyToMany
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")})
    private List<SysRole> roles;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public SysDept getDept() {
        if (dept == null) {
            dept = new SysDept();
        }
        return dept;
    }

    public void setDept(SysDept dept) {
        this.dept = dept;
    }


    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("userId", getUserId())
                .append("deptId", getDeptId()).append("loginName", getLoginName()).append("userName", getUserName())
                .append("email", getEmail()).append("phonenumber", getPhonenumber()).append("sex", getSex())
                .append("avatar", getAvatar()).append("password", getPassword()).append("salt", getSalt())
                .append("status", getStatus()).append("delFlag", getDelFlag()).append("loginIp", getLoginIp())
                .append("loginDate", getLoginDate()).append("createBy", getCreateBy())
                .append("createTime", getCreateTime()).append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime()).append("remark", getRemark()).append("dept", getDept())
                .toString();
    }
}
