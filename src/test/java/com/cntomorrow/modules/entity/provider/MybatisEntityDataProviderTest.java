package com.cntomorrow.modules.entity.provider;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cntomorrow.admin.AdminApplication;
import com.cntomorrow.modules.test.entity.EntityConfigOldMapper;
import com.cntomorrow.modules.test.entity.EntityFieldConfigOldMapper;
import com.cntomorrow.core.tool.support.Kv;
import com.cntomorrow.modules.entity.dto.EntityDataQuery;
import com.cntomorrow.modules.entity.mapper.EntityConfigMapper;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class MybatisEntityDataProviderTest {

    @Resource
    private MybatisEntityDataProvider mybatisEntityDataProvider;

    @Resource
    private EntityConfigMapper entityConfigMapper;

    @Resource
    private EntityConfigOldMapper entityConfigOldMapper;

    @Resource
    private EntityFieldConfigOldMapper entityFieldConfigOldMapper;


    @Test
    public void get(){
       Map<String, Object> data =  mybatisEntityDataProvider.get("tb_user_copy_cascade", "1395187522837024768");
       System.out.println(data);
    }



    @Test
    public void page(){
        EntityDataQuery entityQuery = new EntityDataQuery();
//        Map<String, Object> queries = Maps.newHashMap();
//        queries.put("name_like", "S");
//        entityQuery.setQueries(queries);

        List<OrderItem> sorts = Lists.newArrayList();
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("id");
        orderItem.setAsc(false);
        sorts.add(orderItem);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setColumn("name");
        sorts.add(orderItem1);

        entityQuery.setSorts(sorts);

        Page<Map<String, Object>> page =  mybatisEntityDataProvider.page("tb_user_copy_cascade", entityQuery);
        System.out.println(page.getRecords());
    }

    @Test
    public void save(){
        Kv data = Kv.init()
                .set("name", "zhaoxiaodan2")
                .set("c1", "1")
                .set("c2", "2")
                .set("c3", new Date());

        Object page =  mybatisEntityDataProvider.save("tb_user_copy_cascade", data);
        System.out.println(page);
    }

    @Test
    public void update(){
        Kv data = Kv.init()
                .set("name", "zhaoxiaodan2")
                .set("c1", "11")
                .set("c2", "2");

        mybatisEntityDataProvider.update("tb_user_copy_cascade", "1395196502640562176", data);
    }

    @Test
    public void delete(){

        mybatisEntityDataProvider.delete("tb_user_copy_cascade", Arrays.asList());
    }

    public boolean getBoolean(String str){
        if(StrUtil.equalsIgnoreCase(str, "1")){
            return true;
        }else{
            return false;
        }
    }

}