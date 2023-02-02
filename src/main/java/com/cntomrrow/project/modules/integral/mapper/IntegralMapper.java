package com.cntomrrow.project.modules.integral.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cntomrrow.project.modules.integral.entity.UserIntegral;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Author: zkx
 * @date: 1/2/2023 下午10:37
 */

@Mapper
public interface IntegralMapper extends BaseMapper<UserIntegral> {

    String getIntegral(String uuid);

    void integralRecharge(Map map);
}
