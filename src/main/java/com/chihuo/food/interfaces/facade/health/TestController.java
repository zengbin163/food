package com.chihuo.food.interfaces.facade.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RefreshScope
public class TestController {
	
    @Value("${server.port}")
    private Integer port;
    @Value("${name}")
    private String name;
    
	@GetMapping("/test")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("food-ok");
	}
	
    @RequestMapping("/pullConfig")
    public String pullMysqlInfo(){
    	JSONObject json = new JSONObject();
    	json.put("port", port);
    	json.put("name", name);
        return json.toJSONString();
    }

}
