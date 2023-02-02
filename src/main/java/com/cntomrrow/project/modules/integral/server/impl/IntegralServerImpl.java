package com.cntomrrow.project.modules.integral.server.impl;

import com.cntomrrow.project.modules.integral.entity.IntegralDetails;
import com.cntomrrow.project.modules.integral.entity.UserIntegral;
import com.cntomrrow.project.modules.integral.mapper.IntegralDetailsMapper;
import com.cntomrrow.project.modules.integral.mapper.IntegralMapper;
import com.cntomrrow.project.modules.integral.server.IntegralServer;
import com.cntomrrow.project.modules.login.entity.SysUser;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author: zkx
 * @date: 1/2/2023 下午10:38
 */

@Service
public class IntegralServerImpl implements IntegralServer {

    @Autowired
    private IntegralMapper integralMapper;

    @Autowired
    private IntegralDetailsMapper integralDetailsMapper;


    @Override
    public R getIntegral(SysUser sysUser) {
        return R.ok(integralMapper.getIntegral(sysUser.getUuid()));
    }

    @Override
    @Transactional
    public R integralRecharge(Map map) {
        try {
            if (map.isEmpty()||map.get("uuid")==null) return R.failed("虚拟积分地址不能为空！");
            if (map.get("amount")==null) return R.failed("充值金额不能为空!");
            //充值积分
            String oldIntegral = integralMapper.getIntegral(map.get("uuid").toString());
            BigDecimal newInteg=new BigDecimal(map.get("amount").toString());
            BigDecimal old=new BigDecimal(oldIntegral);
            BigDecimal latitude=old.add(newInteg);
            UserIntegral user=new UserIntegral();
            user.setUuid(map.get("uuid").toString());
            user.setIntegral(latitude.doubleValue());
            user.setStatus(1);
            integralMapper.updateById(user);
            //记录明细
            IntegralDetails details=new IntegralDetails();
            details.setUuid(map.get("uuid").toString());
            details.setIdentification(0);
            details.setIntegral_val(map.get("amount").toString());
            integralDetailsMapper.insert(details);
            return R.ok("");
        } catch (Exception e) {
            return R.failed("充值失败");
        }
    }


}
