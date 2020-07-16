package com.chihuo.food.interfaces.facade.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chihuo.food.infrastructure.client.rest.CfgBean;

@RestController
@RequestMapping("/consumer")
public class UidController {

    //多个方法调用只需改一处就ok
    public static  final String URL_PREFIX = "http://localhost:7002";
    
    @Autowired
    private CfgBean cfgBean;

	@RequestMapping(value = "/uid/getUID", method = RequestMethod.GET)
	public Long getUID() {
	    //调用远程服务 http请求
		String url = URL_PREFIX + "/provider/uid/getUID";
	    return cfgBean.getRestTemplate().getForObject(url, Long.class );
	}

}
