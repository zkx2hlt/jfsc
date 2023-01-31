package com.cntomorrow.modules.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 实体配置表
 * @author speng
 */
@Data
@TableName("sys_entity_field_config_old")
public class EntityFieldConfigOld {

    @TableId
    private String id;

    /**
     * 实体tag
     */
    private String tag;

    /**
     * 物理表名
     */
    private String tableName;

    /**
     * 列名
     */
    private String columnName;

    /**
     *列排序（升序）
     */
    private BigDecimal columnSort;
    /**
     *类型
     */
    private String columnType;

    /**
     *列标签名
     */
    private String columnLabel;

    /**
     *所属分组
     */
    private String columnGroup;
    
    /**
     *列宽
     */
    @TableField(exist=false)
    private String columnWidth;

    /**
     *列备注说明
     */
    private String comments;

    /**
     *类的属性名
     */
    private String attrName;
    /**
     *类的属性类型
     */
    private String attrType;
     
    /**
     *是否主键
     */
    private String isPk;
    /**
     *是否必填 1-必填，0-不必填
     */
    private String isNull;
    /**
     *是否插入字段
     */
    private String isInsert;
    /**
     *是否更新字段
     */
    private String isUpdate;
    /**
     *是否列表字段
     */
    private String isList;
    /**
     *是否查询字段
     */
    private String isQuery;
    /**
     *查询方式
     */
    private String queryType;

    /**
     * 详情是否显示
     */
    private String isDetail;

    /**
     *  是否编辑字段
     *  0-不显示
     *  1-显示可编辑
     *  2-显示只读
     */
    private String isAdd;
    /**
     *  是否编辑字段
     *  0-不显示
     *  1-显示可编辑
     *  2-显示只读
     */
    private String isEdit;

    /**
     *  表单类型
     */
    private String showType;

    /**
     * 字典
     */
    private String dictName;

    /**
     *字段校验
     */
    private String fieldValid;

    /**
     * 是否为链接
     */
    private String isLink;

    /**
     * 链接地址
     */
    private String linkUrl;

    /**
     * 默认值
     */
    private String defaultValue;
    
    /**
     * 新起一行
     */
    private String newLine;

    /**
     * 栅格
     */
    private String grid;

    /**
     *其它生成选项
     */
    @ApiModelProperty(value = "其它生成选项")
    private String options;
}
