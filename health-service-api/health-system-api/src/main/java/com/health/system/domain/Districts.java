package com.health.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.health.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 地区表 districts
 *
 * @author zq
 * @date 2018-12-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Districts extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Integer id;

    /**
     * 上级编号
     */
    private Integer pid;

    /**
     * 层级
     */
    private Integer deep;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级名称
     */
    private String pname;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 拼音缩写
     */
    private String pinyinShor;

    /**
     * 扩展名
     */
    private String extName;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;
}
