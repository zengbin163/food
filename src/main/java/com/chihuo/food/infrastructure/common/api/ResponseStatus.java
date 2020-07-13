package com.chihuo.food.infrastructure.common.api;

/**
 * @ClassName: ResultCode
 * @Description: 返回结果码
 * @author zengbin
 * @date 2019年11月12日 上午9:25:45
 */
public enum ResponseStatus {

    SUCCESS(200, "响应正常"),
    NETWORK_ERROR(201, "网络异常"),
    RESPONSE_TIMEOUT(202, "响应超时"),

    SAVE_FAIL(300, "新增失败"),
    UPDATE_FAIL(301, "更新失败"),
    DELETE_FAIL(302, "删除失败"),
    ILLEGAL_DATA(304,"非法数据"),
    DATA_NOT_EXISTS(305, "数据不存在"),

    ILLEGAL_ARGUMENT(400,"参数错误或者缺少必要参数"),
    NOT_EXISTS(404,"数据不存在"),

    SYSTEM_ERROR(500,"系统异常"),
    ;

    private Integer code;
    private String string;

    private ResponseStatus() {
    }

    private ResponseStatus(Integer code, String string) {
        this.code = code;
        this.string = string;
    }

    public static ResponseStatus getEnum(Integer code) {
        for (ResponseStatus retEnum : ResponseStatus.values()) {
            if (retEnum.getCode().equals(code)) {
                return retEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}

