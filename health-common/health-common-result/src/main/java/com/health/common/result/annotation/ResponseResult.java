package com.health.common.result.annotation;

import java.lang.annotation.*;

/**
 * @author zhangqing
 * @version V1.0
 * @date 8/9/2020 9:22 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {
}
