package com.health.system.controller;

import com.health.common.auth.annotation.HasPermissions;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.JsonResult;
import com.health.common.log.annotation.OperLog;
import com.health.common.log.enums.BusinessType;
import com.health.system.domain.SysRole;
import com.health.system.domain.SysRoleDept;
import com.health.system.domain.dto.SysRoleDto;
import com.health.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色 提供者
 *
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("role")
public class SysRoleController extends BaseController {
    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 查询角色
     */
    @GetMapping("get/{roleId}")
    public SysRoleDto get(@PathVariable("roleId") Long roleId) {
        return sysRoleService.selectRoleById(roleId);
    }

    /**
     * 查询角色列表
     */
    @GetMapping("list")
    public JsonResult list(SysRoleDto sysRole) {
        startPage();
        return result(sysRoleService.selectRoleList(sysRole));
    }

    @GetMapping("all")
    public JsonResult all() {
        return JsonResult.ok().put("rows", sysRoleService.selectRoleAll());
    }

    /**
     * 新增保存角色
     */
    @PostMapping("save")
    @OperLog(title = "角色管理", businessType = BusinessType.INSERT)
    public JsonResult addSave(@RequestBody SysRoleDto sysRole) {
        return toAjax(sysRoleService.insertRole(sysRole));
    }

    /**
     * 修改保存角色
     */
    @OperLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("update")
    public JsonResult editSave(@RequestBody SysRoleDto sysRole) {
        return toAjax(sysRoleService.updateRole(sysRole));
    }

    /**
     * 修改保存角色
     */
    @OperLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("status")
    public JsonResult status(@RequestBody SysRoleDto sysRole) {
        return toAjax(sysRoleService.changeStatus(sysRole));
    }

    /**
     * 保存角色分配数据权限
     */
    @HasPermissions("system:role:edit")
    @OperLog(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/authDataScope")
    public JsonResult authDataScopeSave(@RequestBody SysRoleDto role) {
        role.setUpdateBy(getLoginName());
        if (sysRoleService.authDataScope(role) > 0) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    /**
     * 删除角色
     *
     * @throws Exception
     */
    @OperLog(title = "角色管理", businessType = BusinessType.DELETE)
    @PostMapping("remove")
    public JsonResult remove(String ids) throws Exception {
        return toAjax(sysRoleService.deleteRoleByIds(ids));
    }
}
