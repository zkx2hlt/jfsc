package com.cntomorrow.admin.modules.test.controller;

import com.cntomorrow.core.tool.exception.RRException;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @PostMapping("/test/post")
    public Object postTest(@RequestParam String id){
        return id;
    }

    @GetMapping("/test/get")
    public Object getTest(@RequestParam String id){

        return id;
    }

}
