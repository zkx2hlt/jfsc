package com.cntomrrow.project.modules.login.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: zkx
 * @date: 1/2/2023 下午4:36
 */

@Data
public class SysUser {

    private String username;
    @TableId
    private String uuid;
    private String phone;
    private String password;
    private String salt;
    private int status;
    private String real_password;


}
