package com.health.system.controller;

import com.health.common.core.controller.BaseController;
import com.health.system.domain.SysPost;
import com.health.system.service.ISysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位 提供者
 *
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("/sys/sysPost")
public class SysPostClient extends BaseController {

    @Autowired
    private ISysPostService sysPostService;

    /**
     * 查询岗位
     */
    @GetMapping("get/{postId}")
    public SysPost get(@PathVariable("postId") Long postId) {
        return sysPostService.selectPostById(postId);

    }

    /**
     * 查询岗位列表
     */
    @PostMapping("list")
    public List<SysPost> list(SysPost sysPost) {
        startPage();
        return sysPostService.selectPostList(sysPost);
    }


    /**
     * 新增保存岗位
     */
    @PostMapping("save")
    public int addSave(SysPost sysPost) {
        return sysPostService.insertPost(sysPost);
    }

    /**
     * 修改保存岗位
     */
    @PostMapping("update")
    public int editSave(SysPost sysPost) {
        return sysPostService.updatePost(sysPost);
    }

    /**
     * 删除岗位
     *
     * @throws Exception
     */
    @PostMapping("remove")
    public int remove(String ids) throws Exception {
        return sysPostService.deletePostByIds(ids);
    }

}
