package com.health.system.controller;

import cn.hutool.core.convert.Convert;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.health.common.annotation.LoginUser;
import com.health.common.auth.annotation.HasPermissions;
import com.health.common.constant.UserConstants;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.JsonResult;
import com.health.common.log.annotation.OperLog;
import com.health.common.log.enums.BusinessType;
import com.health.common.result.annotation.ResponseResult;
import com.health.common.utils.RandomUtil;
import com.health.system.domain.SysUser;
import com.health.system.domain.dto.SysUserDto;
import com.health.system.service.ISysMenuService;
import com.health.system.service.ISysUserService;
import com.health.system.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * 用户 提供者
 *
 * @author zq
 * @date 2019-05-20
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SysUserController extends BaseController {
    private final ISysUserService sysUserService;

    private final ISysMenuService sysMenuService;

    @Autowired
    @Qualifier("jasyptStringEncryptor")
    private StringEncryptor stringEncryptor;

    @SentinelResource(value = "/user/test")
    @GetMapping("/test")
    @ResponseResult
    public String get() {
        return "111111";
    }

    @GetMapping("/header")
    public JsonResult host(@RequestBody SysUserDto sysUserDto) {
        return JsonResult.ok("天下武功,唯快不破,header版");
    }

    @GetMapping("/encrypt")
    public String encrypt(String str){
        return stringEncryptor.decrypt(str);
    }

    /**
     * 查询用户
     */
    @GetMapping("get/{userId}")
    public SysUserDto get(@PathVariable("userId") Long userId) {
        return sysUserService.selectUserById(userId);
    }

    @GetMapping("info")
    public SysUserDto info(@LoginUser SysUserDto sysUserDto) {
        sysUserDto.setButtons(sysMenuService.selectPermsByUserId(sysUserDto.getUserId()));
        return sysUserDto;
    }

    /**
     * 查询用户
     */
    @GetMapping("find/{username}")
    public SysUserDto findByUsername(@PathVariable("username") String username) {
        return sysUserService.selectUserByLoginName(username);
    }

    /**
     * 查询拥有当前角色的所有用户
     */
    @GetMapping("hasRoles")
    public Set<Long> hasRoles(String roleIds) {
        Long[] arr = Convert.toLongArray(roleIds);
        return sysUserService.selectUserIdsHasRoles(arr);
    }

    /**
     * 查询所有当前部门中的用户
     */
    @GetMapping("inDepts")
    public Set<Long> inDept(String deptIds) {
        Long[] arr = Convert.toLongArray(deptIds);
        return sysUserService.selectUserIdsInDepts(arr);
    }

    /**
     * 查询用户列表(分页)
     */
    @GetMapping("list")
    public JsonResult list(SysUserDto sysUser) {
        startPage();
        return result(sysUserService.selectUserList(sysUser));
    }

    /**
     * 新增保存用户
     */
    @HasPermissions("system:user:add")
    @PostMapping("save")
    @OperLog(title = "用户管理", businessType = BusinessType.INSERT)
    public JsonResult addSave(@RequestBody SysUserDto sysUserDto) {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(sysUserService.checkLoginNameUnique(sysUserDto.getLoginName()))) {
            return JsonResult.error("新增用户'" + sysUserDto.getLoginName() + "'失败，登录账号已存在");
        } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(sysUserDto))) {
            return JsonResult.error("新增用户'" + sysUserDto.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(sysUserService.checkEmailUnique(sysUserDto))) {
            return JsonResult.error("新增用户'" + sysUserDto.getLoginName() + "'失败，邮箱账号已存在");
        }
        sysUserDto.setSalt(RandomUtil.randomStr(6));
        sysUserDto.setPassword(
                PasswordUtil.encryptPassword(sysUserDto.getLoginName(), "123456", sysUserDto.getSalt()));
        sysUserDto.setCreateBy(getLoginName());
        return toAjax(sysUserService.insertUser(sysUserDto));
    }

    /**
     * 修改保存用户
     */
    @HasPermissions("system:user:edit")
    @OperLog(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("update")
    public JsonResult editSave(@RequestBody SysUserDto sysUser) {
        if (null != sysUser.getUserId() && SysUser.isAdmin(sysUser.getUserId())) {
            return JsonResult.error("不允许修改超级管理员用户");
        } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(sysUser))) {
            return JsonResult.error("修改用户'" + sysUser.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(sysUserService.checkEmailUnique(sysUser))) {
            return JsonResult.error("修改用户'" + sysUser.getLoginName() + "'失败，邮箱账号已存在");
        }
        return toAjax(sysUserService.updateUser(sysUser));
    }

    /**
     * 查询用户信息
     *
     * @param sysUser
     * @return
     * @author zmr
     */
    @HasPermissions("system:user:edit")
    @PostMapping("update/info")
    @OperLog(title = "用户管理", businessType = BusinessType.UPDATE)
    public JsonResult updateInfo(@RequestBody SysUserDto sysUser) {
        return toAjax(sysUserService.updateUserInfo(sysUser));
    }

    /**
     * 记录登陆信息
     *
     * @param sysUser
     * @return
     * @author zmr
     */
    @PostMapping("update/login")
    public JsonResult updateLoginRecord(@RequestBody SysUserDto sysUser) {
        return toAjax(sysUserService.updateUser(sysUser));
    }

    @HasPermissions("system:user:resetPwd")
    @OperLog(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    public JsonResult resetPwdSave(@RequestBody SysUserDto user) {
        user.setSalt(RandomUtil.randomStr(6));
        user.setPassword(PasswordUtil.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        return toAjax(sysUserService.resetUserPwd(user));
    }

    /**
     * 修改状态
     *
     * @param sysUser
     * @return
     * @author zmr
     */
    @HasPermissions("system:user:edit")
    @PostMapping("status")
    @OperLog(title = "用户管理", businessType = BusinessType.UPDATE)
    public JsonResult status(@RequestBody SysUserDto sysUser) {
        return toAjax(sysUserService.changeStatus(sysUser));
    }

    /**
     * 删除用户
     *
     * @throws Exception
     */
    @HasPermissions("system:user:remove")
    @OperLog(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("remove")
    public JsonResult remove(String ids) throws Exception {
        return toAjax(sysUserService.deleteUserByIds(ids));
    }
}
