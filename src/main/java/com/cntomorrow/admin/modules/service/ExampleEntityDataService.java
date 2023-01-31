package com.cntomorrow.admin.modules.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cntomorrow.admin.modules.mapper.ExampleEntityDataMapper;
import com.cntomorrow.modules.entity.bean.EntityPage;
import com.cntomorrow.modules.entity.dto.EntityDataQuery;
import com.cntomorrow.modules.entity.entity.EntityConfig;
import com.cntomorrow.modules.entity.provider.DynamicEntityDataProvider;
import com.cntomorrow.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;

/**
 * 重写产品分页接口 示例
 * @Author: speng
 * @date: 2021/6/29 16:06
 */

@Service
public class ExampleEntityDataService extends DynamicEntityDataProvider{

    @Resource
    private ExampleEntityDataMapper exampleEntityDataMapper;

    @Override
    public boolean supports(String tag) {
        return tag.equals("example");
    }

    /**
     * 自定义分页示例
     * @param tag
     * @param entityDataQuery
     * @return
     */
    @Override
    public Page page(String tag, EntityDataQuery entityDataQuery) {
        Consumer<EntityPage> consumer = entityPage -> {
            EntityConfig entity = entityPage.getEntity();
            Page page= entityPage.getPage();
            //查询列，可以手动添加
            List<String> cols = entityPage.getCols();
            //查询条件，可以手动修改
            QueryWrapper queryWrapper = entityPage.getQueryWrapper();
            //当前访问用户
            SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            //此处执行业务系统自定义查询sql 具体可参考 MybatisEntityDataProvider page方法
            exampleEntityDataMapper.page(page, entity.getTableName(), cols, queryWrapper);
        };
        return super.page(tag,entityDataQuery, consumer);
    }
}
