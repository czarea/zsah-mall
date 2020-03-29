package com.czarea.zsah.common.vo;

import java.io.Serializable;
import java.util.Collection;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应类
 *
 * @author zhouzx
 */
@Data
@NoArgsConstructor
public class Response<T> implements Serializable {

    public static final Response SERVICE_FALL_BACK = new Response(700, "微服务调用异常！！！");
    private static final long serialVersionUID = -1574275447878553270L;

    public static final Response<Void> SUCCESS = new Response<>(200, "success");
    public static final Response<Void> SYSTEM_ERROR = new Response<>(500, "系统错误！");

    protected int code = 200;

    protected String msg = "success";

    protected T data;

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return this.code == 200;
    }

    public static <T> Response<T> fail(int code, String msg) {
        return new Response<>(code, msg, null);
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setData(Collection<T> data) {
        this.data = (T) data;
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

    public T getData() {
        return data;
    }

    public Response(T data) {
        this.data = data;
    }

    public static Response<String> data(String msg) {
        return new Response<>(msg);
    }
}
