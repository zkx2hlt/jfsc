package com.cntomorrow.modules.login;

import com.cntomorrow.modules.untils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zkx
 * @date: 31/1/2023 下午4:20
 */


@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/register")
    public R register(){
        return R.ok("成功");
    }
}
