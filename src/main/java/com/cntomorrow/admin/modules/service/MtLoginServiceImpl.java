package com.cntomorrow.admin.modules.service;

import com.cntomorrow.modules.sys.form.SysLoginForm;
import com.cntomorrow.modules.sys.oauth2.AuthToken;
import com.cntomorrow.modules.sys.service.impl.SysLoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author speng
 * @Description 实现自定义登录  示例
 * @date 2021/7/19 11:25
 */
@Slf4j
//@Component
public class MtLoginServiceImpl extends SysLoginServiceImpl {

    @Override
    public AuthToken login(SysLoginForm form) {
        //登录前的数据验证 TODO
        //产品登录验证
        AuthToken authToken = super.login(form);
        //登录后的处理
        return authToken;

    }
}
