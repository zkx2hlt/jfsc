package com.cntomrrow.project.modules.integral.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: zkx
 * @date: 1/2/2023 下午11:14
 */

@Data
@TableName("user_integral")
public class UserIntegral {
    @TableId
    private String uuid;

    private Double integral;
    private int status;
}
