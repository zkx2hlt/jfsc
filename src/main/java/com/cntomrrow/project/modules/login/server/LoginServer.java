package com.cntomrrow.project.modules.login.server;

import com.baomidou.mybatisplus.extension.api.R;
import com.cntomrrow.project.modules.login.entity.SysUser;

import java.util.Map;

/**
 * @Author: zkx
 * @date: 31/1/2023 下午7:47
 */


public interface LoginServer {

    Map getUer();

    R register(SysUser sysUser);

    R login(SysUser sysUser);

    R resetPassword(SysUser sysUser);
}
