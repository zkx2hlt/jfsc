package com.cntomrrow.project.modules.address.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: zkx
 * @date: 1/2/2023 下午7:58
 */

@Data
@TableName("user_address")
public class Address {
    @TableId
    private int id;

    private String uuid;
    private String address;
    private int  status;
}
