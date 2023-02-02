package com.cntomrrow.project.modules.login.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.cntomrrow.project.modules.login.entity.SysUser;
import com.cntomrrow.project.modules.login.server.LoginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Author: zkx
 * @date: 31/1/2023 下午4:20
 */


@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginServer loginServer;


    /**
     * 注册获取UUID
     * @return
     */
    @GetMapping("/registerUuid")
    public R registerUuid(){
        return R.ok(UUID.randomUUID());
    }

    /**
     * 注册
     * @return
     */
    @PostMapping("/register")
    public R register(@RequestBody SysUser sysUser){
        return loginServer.register(sysUser);
    }

    /**
     * 登录
     * @param sysUser
     * @return
     */
    @PostMapping("/signIn")
    public R login(@RequestBody SysUser sysUser){
        return loginServer.login(sysUser);
    }

    /**
     * 重置密码
     * @param sysUser
     * @return
     */
    @PostMapping("/resetPassword")
    public R resetPassword(@RequestBody SysUser sysUser){
        return loginServer.resetPassword(sysUser);
    }



}
