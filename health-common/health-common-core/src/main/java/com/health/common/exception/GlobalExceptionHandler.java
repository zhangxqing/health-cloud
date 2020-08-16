package com.health.common.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.health.common.core.domain.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * 异常处理器
 *
 * @author zq
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public JsonResult handleException(HttpRequestMethodNotSupportedException e) {
        logger.error(e.getMessage(), e);
        return JsonResult.error("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public JsonResult notFount(RuntimeException e) {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        logger.error("运行时异常:", e);
        return JsonResult.error(e.getMessage());
    }

    @ExceptionHandler(UndeclaredThrowableException.class)
    public JsonResult notFount(BlockException e) {
        e.getMessage();
        logger.error("Too Many Request");
        return JsonResult.currentLimitingerror("请求太频繁,请稍后再试!");
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RuoyiException.class)
    public JsonResult handleWindException(RuoyiException e) {
        return JsonResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public JsonResult handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return JsonResult.error("数据库中已存在该记录");
    }

    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e) throws Exception {
        logger.error(e.getMessage(), e);
        return JsonResult.error("服务器错误，请联系管理员");
    }

    /**
     * 捕获并处理未授权异常
     *
     * @param e 授权异常
     * @return 统一封装的结果类, 含有代码code和提示信息msg
     */
    @ExceptionHandler(UnauthorizedException.class)
    public JsonResult handle401(UnauthorizedException e) {
        return JsonResult.error(401, e.getMessage());
    }

    // 验证码错误
    @ExceptionHandler(ValidateCodeException.class)
    public JsonResult handleCaptcha(ValidateCodeException e) {
        return JsonResult.error(e.getMessage());
    }
}
