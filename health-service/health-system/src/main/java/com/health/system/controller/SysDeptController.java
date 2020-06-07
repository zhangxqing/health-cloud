package com.health.system.controller;

import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.JsonResult;
import com.health.system.domain.SysDept;
import com.health.system.domain.dto.SysDeptDto;
import com.health.system.service.ISysDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * 部门 提供者
 *
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("dept")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SysDeptController extends BaseController {

    private final ISysDeptService sysDeptService;

    /**
     * 查询部门
     */
    @GetMapping("get/{deptId}")
    public SysDeptDto get(@PathVariable("deptId") Long deptId) {
        return sysDeptService.selectDeptById(deptId);
    }

    /**
     * 查询部门列表
     */
    @GetMapping("list")
    public JsonResult list(SysDeptDto sysDept) {
        startPage();
        return result(sysDeptService.selectDeptList(sysDept));
    }

    /**
     * 查询所有可用部门
     */
    @GetMapping("list/enable")
    public JsonResult listEnable(SysDeptDto sysDept) {
        sysDept.setStatus("0");
        return result(sysDeptService.selectDeptList(sysDept));
    }

    /**
     * 新增保存部门
     */
    @PostMapping("save")
    public JsonResult addSave(@RequestBody SysDeptDto sysDept) {
        return toAjax(sysDeptService.insertDept(sysDept));
    }

    /**
     * 修改保存部门
     */
    @PostMapping("update")
    public JsonResult editSave(@RequestBody SysDeptDto sysDept) {
        return toAjax(sysDeptService.updateDept(sysDept));
    }

    /**
     * 删除部门
     */
    @PostMapping("remove/{deptId}")
    public JsonResult remove(@PathVariable("deptId") Long deptId) {
        return toAjax(sysDeptService.deleteDeptById(deptId));
    }

    /**
     * 加载角色部门（数据权限）列表树
     */
    @GetMapping("/role/{roleId}")
    public Set<String> deptTreeData(@PathVariable("roleId") Long roleId) {
        if (null == roleId || roleId <= 0) return null;
        return sysDeptService.roleDeptIds(roleId);
    }
}
