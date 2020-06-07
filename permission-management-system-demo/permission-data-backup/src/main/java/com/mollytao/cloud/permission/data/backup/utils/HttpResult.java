package com.mollytao.cloud.permission.data.backup.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ApiModel：用于响应类上，表示一个返回响应数据的信息
 *   （这种一般用在post创建的时候，使用@RequestBody这样的场景，
 *     请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 *  @ApiModelProperty：用在属性上，描述响应类的属性
 */
@ApiModel(description= "返回响应数据")
public class HttpResult {

    @ApiModelProperty(value = "返回code")
    private int code = 200;
    @ApiModelProperty(value = "返回消息")
    private String msg;
    @ApiModelProperty(value = "返回对象")
    private Object data;

    public static HttpResult error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static HttpResult error(String msg) {
        return error(500, msg);
    }

    public static HttpResult error(int code, String msg) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static HttpResult ok(String msg) {
        HttpResult r = new HttpResult();
        r.setMsg(msg);
        return r;
    }

    public static HttpResult ok(Object data) {
        HttpResult r = new HttpResult();
        r.setData(data);
        return r;
    }

    public static HttpResult ok() {
        return new HttpResult();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
