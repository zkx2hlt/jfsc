package com.cntomrrow.project.modules.login.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cntomrrow.project.modules.login.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Author: zkx
 * @date: 31/1/2023 下午7:48
 */
@Mapper
public interface LoginMapper extends BaseMapper<SysUser> {

    Map getUer();
}
