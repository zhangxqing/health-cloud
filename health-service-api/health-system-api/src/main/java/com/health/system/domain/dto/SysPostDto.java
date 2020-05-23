package com.health.system.domain.dto;

import com.health.system.domain.SysPost;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 岗位表 sys_post
 *
 * @author zq
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPostDto extends SysPost {

    /**
     * 用户是否存在此岗位标识 默认不存在
     */
    private boolean flag = false;

}
