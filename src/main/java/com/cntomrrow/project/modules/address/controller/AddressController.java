package com.cntomrrow.project.modules.address.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.cntomrrow.project.modules.address.entity.Address;
import com.cntomrrow.project.modules.address.server.AddressServer;
import com.cntomrrow.project.modules.login.entity.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: zkx
 * @date: 1/2/2023 下午7:57
 */


@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressServer addressServer;

    /**
     * 获取地址列表
     * @param sysUser
     * @return
     */
    @PostMapping("/getList")
    public R getList(@RequestBody SysUser sysUser){
        if (sysUser.getUuid()==null) return R.failed("uuid不能为空！");
        return addressServer.getList(sysUser.getUuid());
    }

    /**
     * 删除地址
     * @param map
     * @return
     */
    @DeleteMapping("/delAddress")
    public R delAddress(@RequestBody Map<String,List<String>> map){
        return addressServer.delAddress(map.get("ids"));
    }

    /**
     * 修改地址
     * @param address
     * @return
     */
    @PutMapping("/updateAdress")
    public R updateAdress(@RequestBody Address address){
        return addressServer.updateAdress(address);
    }

    @PostMapping("/add")
    public R add(@RequestBody Address address){
        return addressServer.add(address);
    }
}
