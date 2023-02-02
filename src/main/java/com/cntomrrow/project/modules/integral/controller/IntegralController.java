package com.cntomrrow.project.modules.integral.controller;

import com.cntomrrow.project.modules.integral.server.IntegralServer;
import com.cntomrrow.project.modules.login.entity.SysUser;
import com.baomidou.mybatisplus.extension.api.R;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: zkx
 * @date: 1/2/2023 下午10:36
 */

@RestController
@RequestMapping("/integral")
public class IntegralController {

    @Autowired
    private IntegralServer integralServer;

    /**
     * 获取用户积分信息
     * @param sysUser
     * @return
     */
    @PostMapping("/getIntegral")
    public R getIntegral(@RequestBody SysUser sysUser){
        return integralServer.getIntegral(sysUser);
    }

    /**
     * 积分充值
     * @param map
     * @return
     */
    @PostMapping("/integralRecharge")
    public R integralRecharge(@RequestBody Map map){
        return integralServer.integralRecharge(map);
    }


}
