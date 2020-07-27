package com.chihuo.food.infrastructure.consumer;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class UidClientComponentFallback implements UidClientComponent {

	@Override
	public Long getUID() {
		return -1L;
	}

	@Override
	public String parseUID(Long uid) {
		JSONObject json = new JSONObject();
		json.put("uid", -1);
		json.put("fallback", "服务器异常进行熔断");
		return json.toJSONString();
	}

}
