package com.chihuo.food.infrastructure.consumer;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class UidClientComponentFallback implements UidClientComponent {

	@Override
	public long getUID() {
		return -1;
	}

	@Override
	public String parseUID(long uid) {
		JSONObject json = new JSONObject();
		json.put("uid", -1);
		json.put("fallback", "服务器异常进行熔断");
		return json.toJSONString();
	}

}
