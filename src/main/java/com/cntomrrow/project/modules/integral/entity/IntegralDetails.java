package com.cntomrrow.project.modules.integral.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: zkx
 * @date: 1/2/2023 下午11:23
 */

@Data
@TableName("user_integral_details")
public class IntegralDetails {
    @TableId
    private Long id;
    private String uuid;
    private String integral_val;
    private int identification;
    private String order_number;
}
