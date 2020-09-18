package com.health.system.controller;

import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.JsonResult;
import com.health.system.domain.dto.SysConfigDto;
import com.health.system.service.ISysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 参数配置 提供者
 *
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("config")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SysConfigController extends BaseController {

    private final ISysConfigService sysConfigService;

    /**
     * 查询参数配置
     */
    @GetMapping("get/{configId}")
    public SysConfigDto get(@PathVariable("configId") Long configId) {
        return sysConfigService.selectConfigById(configId);

    }

    /**
     * 查询参数配置列表
     */
    @GetMapping("list")
    public JsonResult list(SysConfigDto sysConfig) {
        startPage();
        return result(sysConfigService.selectConfigList(sysConfig));
    }


    /**
     * 新增保存参数配置
     */
    @PostMapping("save")
    public JsonResult addSave(@RequestBody SysConfigDto sysConfig) {
        return toAjax(sysConfigService.insertConfig(sysConfig));
    }

    /**
     * 修改保存参数配置
     */
    @PostMapping("update")
    public JsonResult editSave(@RequestBody SysConfigDto sysConfig) {
        return toAjax(sysConfigService.updateConfig(sysConfig));
    }

    /**
     * 删除参数配置
     */
    @PostMapping("remove")
    public JsonResult remove(String ids) {
        return toAjax(sysConfigService.deleteConfigByIds(ids));
    }

}
