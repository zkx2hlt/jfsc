package com.cntomorrow.admin.config;

import com.cntomorrow.core.boot.handler.DefaultGlobalExceptionHandler;
import com.cntomorrow.core.tool.api.ApiErrorCode;
import com.cntomorrow.core.tool.api.R;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author speng
 * @date 2021/1/12 17:11
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends DefaultGlobalExceptionHandler {

    @ExceptionHandler(PSQLException.class)
    public R pSQLException(PSQLException ex, HttpServletRequest request) {
        log.warn("PSQLException:", ex);
        return R.failed(ApiErrorCode.SQL_EX.getCode(), ApiErrorCode.SQL_EX.getMsg());
    }

    @Override
    @ExceptionHandler(IllegalArgumentException.class)
    public R illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        log.warn("IllegalArgumentException:", ex);
        return R.failed(ApiErrorCode.ILLEGALA_ARGUMENT_EX.getCode(), ex.getMessage());
    }

}

