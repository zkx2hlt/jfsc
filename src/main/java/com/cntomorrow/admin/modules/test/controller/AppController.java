package com.cntomorrow.admin.modules.test.controller;

import com.cntomorrow.core.tool.api.R;
import com.cntomorrow.modules.sys.entity.SysUserEntity;
import com.cntomorrow.modules.sys.oauth2.AuthToken;
import com.cntomorrow.modules.sys.service.SysUserTokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    SysUserTokenService sysUserTokenService;

    @PostMapping("/login")
    public R<AuthToken> login(String username, String passwrod) {

        // TODO 验证逻辑

        SysUserEntity user = null;

        //生成token，并保存到数据库
        AuthToken authToken = sysUserTokenService.createToken(user.getId());
        authToken.setUserInfo(user);

        return R.ok(authToken);
    }

}
