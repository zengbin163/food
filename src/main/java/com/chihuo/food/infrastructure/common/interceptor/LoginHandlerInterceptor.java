package com.chihuo.food.infrastructure.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.chihuo.food.infrastructure.common.api.ResponseStatus;
import com.chihuo.food.infrastructure.common.exception.SystemException;
import com.chihuo.food.infrastructure.common.session.SessionToolComponent;
import com.chihuo.food.infrastructure.common.session.SessionValue;

public class LoginHandlerInterceptor implements HandlerInterceptor {
	
    public static final Logger log = LoggerFactory.getLogger(LoginHandlerInterceptor.class);
    @Autowired
    private SessionToolComponent sessionToolComponent;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();
    	log.info("==========LoginHandlerInterceptor.preHandle.requestURI:{}", requestURI);
		//需要跳过的验证请求
    	if (StringUtils.isEmpty(requestURI) || requestURI.equals("/")) {
			return true;
		}
    	String token = request.getParameter("token");
    	if(StringUtils.isEmpty(token)) {
    		throw new IllegalArgumentException("传入token不能为空");
    	}
    	SessionValue session = this.sessionToolComponent.get(token);
		if(null == session) {
			throw new SystemException(ResponseStatus.NOT_LOGIN, "请先登录!");
		} else {
			this.sessionToolComponent.setSession(session);
		}
    	return true;
    }

}
