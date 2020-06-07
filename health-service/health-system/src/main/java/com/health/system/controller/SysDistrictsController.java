package com.health.system.controller;

import com.health.common.auth.annotation.HasPermissions;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.JsonResult;
import com.health.common.log.annotation.OperLog;
import com.health.common.log.enums.BusinessType;
import com.health.common.utils.poi.ExcelUtil;
import com.health.system.domain.Districts;
import com.health.system.service.IDistrictsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 地区 信息操作处理
 *
 * @author ruoyi
 * @date 2018-12-19
 */
@RestController
@RequestMapping("districts")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SysDistrictsController extends BaseController {

    private final IDistrictsService districtsService;

    /**
     * 查询地区列表
     */
    @HasPermissions("system:districts:list")
    @RequestMapping("/list")
    public JsonResult list(Districts districts) {
        startPage();
        return result(districtsService.selectDistrictsList(districts));
    }

    /**
     * 导出地区列表
     */
    @HasPermissions("system:districts:export")
    @OperLog(title = "地区", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public JsonResult export(Districts districts) {
        List<Districts> list = districtsService.selectDistrictsList(districts);
        ExcelUtil<Districts> util = new ExcelUtil<Districts>(Districts.class);
        return util.exportExcel(list, "districts");
    }

    /**
     * 新增保存地区
     */
    @HasPermissions("system:districts:add")
    @OperLog(title = "地区", businessType = BusinessType.INSERT)
    @PostMapping("save")
    public JsonResult addSave(@RequestBody Districts districts) {
        districts.setPid(districts.getId() / 100);
        districts.setCreateTime(new Date());
        districts.setUpdateTime(new Date());
        districts.setOperator(getLoginName());
        return toAjax(districtsService.insertDistricts(districts));
    }

    /**
     * /**
     * 修改保存地区
     */
    @HasPermissions("system:districts:edit")
    @OperLog(title = "地区", businessType = BusinessType.UPDATE)
    @PostMapping("update")
    public JsonResult editSave(@RequestBody Districts districts) {
        districts.setPid(districts.getId() / 100);
        districts.setOperator(getLoginName());
        districts.setUpdateTime(new Date());
        return toAjax(districtsService.updateDistricts(districts));
    }

    /**
     * 删除地区
     */
    @HasPermissions("system:districts:remove")
    @OperLog(title = "地区", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public JsonResult remove(String ids) {
        return toAjax(districtsService.deleteDistrictsByIds(ids));
    }
}
