package com.cntomrrow.project.modules.integral.server;

import com.cntomrrow.project.modules.login.entity.SysUser;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: zkx
 * @date: 1/2/2023 下午10:37
 */


public interface IntegralServer {
    R getIntegral(SysUser sysUser);

    R integralRecharge(Map map);

}
