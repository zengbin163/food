package com.famiao.member.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.nacos.api.config.annotation.NacosValue;

@RestController
public class HelloController {
    
    @Value("${member.notes}")
    private String memberNotes;
    
    @NacosValue(value = "${userName}", autoRefreshed = true)
    private String userName;
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(value = "name", defaultValue = "zhangsan") String name) {
        return String.format("你好 %s! %s", name + " & " + userName, memberNotes);
    }

}
