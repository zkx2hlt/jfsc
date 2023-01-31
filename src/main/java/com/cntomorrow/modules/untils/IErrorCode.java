package com.cntomorrow.modules.untils;

/**
 * REST API 错误码接口
 *
 * @author zhxiaodan
 * @since 2018-06-05
 */
public interface IErrorCode {

    /**
     *
     * 错误编码 -1、失败 0、成功
     * @author zhxiaodan
     * @date 2020/12/30 11:38
     * @return long
     */
    long getCode();

    /**
     *
     * 错误描述
     * @author zhxiaodan
     * @date 2020/12/30 11:38
     * @return java.lang.String
     */
    String getMsg();
}