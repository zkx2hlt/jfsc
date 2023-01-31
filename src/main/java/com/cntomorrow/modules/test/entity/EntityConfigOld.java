package com.cntomorrow.modules.test.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.cntomorrow.modules.entity.entity.EntityFieldConfig;
import lombok.Data;

/**
 * 实体配置表
 * @author speng
 */
@Data
@TableName("sys_entity_config")
public class EntityConfigOld {

    @TableId()
    private String id;

    /**
     * 实体tag
     */
    private String tag;

    /**
     * 实体名称
     */
    private String name;

    /**
     * 物理表名
     */
    @TableField("table_name")
    private String tableName;

    /**
     * 模板类型
     */
    @TableField("tpl_type")
    private String tplType;

    /**
     * 备注
     */
    private String remarks;
    
    /**
     * 扩展参数
     */
    private String options;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
    
    @TableField(exist = false)
    private List<EntityFieldConfig> fields;
}
