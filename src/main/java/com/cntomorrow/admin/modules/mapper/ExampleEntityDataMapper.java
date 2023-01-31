package com.cntomorrow.admin.modules.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExampleEntityDataMapper {

    /**
     * 分页查询
     */
    Page<Map<String, Object>> page(Page page, @Param("table") String table, @Param("cols") List<String> cols, @Param(Constants.WRAPPER) Wrapper queryWrapper);
}
