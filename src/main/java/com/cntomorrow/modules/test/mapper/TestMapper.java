package com.cntomorrow.modules.test.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cntomorrow.core.tool.support.Kv;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface TestMapper {

    /**
     * 自定义分页查询
     *
     * @param page
     * @param queryWrapper
     * @param kv
     */
    Page<Map<String, Object>> pageWithWrapper(Page page, @Param(Constants.WRAPPER) Wrapper queryWrapper, @Param("kv") Kv kv);


    /**
     * 自定义分页查询
     *
     * @param page
     * @param params
     */
    Page<Map<String, Object>> pageWithMap(Page page, @Param("kv") Map<String, Object> params);
}
