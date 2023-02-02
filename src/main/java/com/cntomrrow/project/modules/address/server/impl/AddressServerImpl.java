package com.cntomrrow.project.modules.address.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.cntomrrow.project.modules.address.entity.Address;
import com.cntomrrow.project.modules.address.mapper.AddressMapper;
import com.cntomrrow.project.modules.address.server.AddressServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zkx
 * @date: 1/2/2023 下午7:57
 */

@Service
public class AddressServerImpl implements AddressServer {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public R getList(String uuid) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("uuid",uuid);
        queryWrapper.eq("status",1);
        return R.ok(addressMapper.selectList(queryWrapper));
    }

    @Override
    public R delAddress(List<String> list) {
        int i = addressMapper.deleteBatchIds(list);
        if (i>0){
            return R.ok("");
        }else {
            return R.failed("删除失败");
        }
    }

    @Override
    public R updateAdress(Address address) {
        Address address1 = addressMapper.selectById(address.getId());
        address1.setAddress(address.getAddress());
        addressMapper.updateById(address1);
        return R.ok("");
    }

    @Override
    public R add(Address address) {
        if (address.getAddress()==null) return R.failed("地址不能为空！");
        if (address.getUuid()==null) return R.failed("虚拟地址不能为空！");
        address.setStatus(1);
        int insert = addressMapper.insert(address);
        if (insert>0){
            return R.ok("");
        }else{
            return R.failed("新增失败！");
        }
    }
}
