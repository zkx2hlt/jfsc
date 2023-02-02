package com.cntomrrow.project.modules.address.server;

import com.baomidou.mybatisplus.extension.api.R;
import com.cntomrrow.project.modules.address.entity.Address;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: zkx
 * @date: 1/2/2023 下午7:57
 */


public interface AddressServer {
    R getList(String uuid);

    R delAddress(List<String> list);

    R updateAdress(Address address);

    R add(Address address);

}
