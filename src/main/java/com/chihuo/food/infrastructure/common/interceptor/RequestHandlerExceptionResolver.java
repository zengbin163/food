package com.chihuo.food.infrastructure.common.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chihuo.food.infrastructure.common.api.Response;
import com.chihuo.food.infrastructure.common.api.ResponseStatus;
import com.chihuo.food.infrastructure.common.exception.BusinessException;
import com.chihuo.food.infrastructure.common.exception.SystemException;
import com.chihuo.food.infrastructure.util.LoggerUtil;

/**
 * @author Bin.Zeng1
 * @date 2019年11月12日 上午9:25:45
 **/
public class RequestHandlerExceptionResolver implements HandlerExceptionResolver, Serializable {

    private static final long serialVersionUID = 5397821535379381427L;
    private String defaultErrorView;

    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
        if (exception.getClass().isAssignableFrom(IllegalArgumentException.class)) {
            String detailMessage = exception.getMessage();
            if(StringUtils.isEmpty(detailMessage)) {
                detailMessage = ResponseStatus.ILLEGAL_ARGUMENT.getString();
            }
            LoggerUtil.ERROR(logger, String.format("[=========IllegalArgumentException======params : %s ; message : %s] ", ResponseStatus.ILLEGAL_ARGUMENT , detailMessage), exception);
            String errorMsg = JSON.toJSONString(Response.failed(ResponseStatus.ILLEGAL_ARGUMENT , detailMessage), SerializerFeature.WriteMapNullValue).toString();
            this.printJson(response, errorMsg);
            return null;
        }else if (exception.getClass().isAssignableFrom(NoSuchMethodException.class)) {
            String detailMessage = exception.getMessage();
            if(StringUtils.isEmpty(detailMessage)) {
                detailMessage = ResponseStatus.ILLEGAL_ARGUMENT.getString();
            }
            LoggerUtil.ERROR(logger, String.format("[=========NoSuchMethodException======params : %s ; message : %s] ", ResponseStatus.NOT_EXISTS , detailMessage), exception);
            String errorMsg = JSON.toJSONString(Response.failed(ResponseStatus.NOT_EXISTS , detailMessage), SerializerFeature.WriteMapNullValue).toString();
            this.printJson(response, errorMsg);
            return null;
        }else if (exception.getClass().isAssignableFrom(SystemException.class)) {
            SystemException ex = (SystemException)exception;
            LoggerUtil.ERROR(logger, String.format("[=========SystemException======ResultCode : %s] ", ex.getResultCode()), exception);
            String errorMsg = JSON.toJSONString(Response.failed(ex.getResultCode() , ex.getResultCode().getString()), SerializerFeature.WriteMapNullValue).toString();
            this.printJson(response, errorMsg);
            return null;
        }else if (exception.getClass().isAssignableFrom(BusinessException.class)) {
            BusinessException ex = (BusinessException)exception;
            LoggerUtil.ERROR(logger, String.format("[=========BusinessException======ResultCode : %s] ", ex.getResultCode()), exception);
            String errorMsg = JSON.toJSONString(Response.failed(ex.getResultCode() , ex.getResultCode().getString()), SerializerFeature.WriteMapNullValue).toString();
            this.printJson(response, errorMsg);
            return null;
        } else {
            LoggerUtil.ERROR(logger, String.format("[=========OtherException======ResultCode : %s] ", ResponseStatus.SYSTEM_ERROR), exception);
            String errorMsg = JSON.toJSONString(Response.failed(ResponseStatus.SYSTEM_ERROR , ResponseStatus.SYSTEM_ERROR.getString()), SerializerFeature.WriteMapNullValue).toString();
            this.printJson(response, errorMsg);
            return null;
        }
    }

    private void printJson(HttpServletResponse response, String jsonData) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Charset", "UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LoggerUtil.ERROR(logger, String.format(e.getMessage()));
        }
        out.write(jsonData);
        out.flush();
        out.close();
    }

    public String getDefaultErrorView() {
        return defaultErrorView;
    }

    public void setDefaultErrorView(String defaultErrorView) {
        this.defaultErrorView = defaultErrorView;
    }
}

