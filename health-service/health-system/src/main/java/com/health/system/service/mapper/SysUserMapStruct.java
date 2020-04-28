package com.health.system.service.mapper;

import com.health.system.domain.SysUser;
import com.health.system.domain.dto.SysUserDto;
import org.mapstruct.Mapper;

/**
 * @author zhangqing
 * @version V1.0
 * @Description: 在这里简单描述该类的作用
 * @Copyright: Copyright (c) 2018
 * @company TODO广州易联众睿图信息技术有限公司
 * @date 27/4/2020 9:25 PM
 */
@Mapper(componentModel="spring")
public interface SysUserMapStruct extends EntityMapper<SysUserDto, SysUser>{

}
