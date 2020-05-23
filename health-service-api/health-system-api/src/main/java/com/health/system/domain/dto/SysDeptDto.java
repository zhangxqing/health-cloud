package com.health.system.domain.dto;

import com.health.system.domain.SysDept;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhangqing
 * @version V1.0
 * @Description: 在这里简单描述该类的作用
 * @Copyright: Copyright (c) 2018
 * @company TODO广州易联众睿图信息技术有限公司
 * @date 23/5/2020 2:22 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDeptDto extends SysDept {

    /**
     * 父部门名称
     */
    private String parentName;
}
