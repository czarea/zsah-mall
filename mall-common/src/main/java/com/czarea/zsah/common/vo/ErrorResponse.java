package com.czarea.zsah.common.vo;

import org.springframework.http.HttpStatus;

/**
 * @author zhouzx
 */
public class ErrorResponse {

    private int code = 500;
    private String msg = "Internal Server Error";

    public static final ErrorResponse SERVER_ERROR = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    public static final ErrorResponse NO_LOGIN = new ErrorResponse(HttpStatus.NON_AUTHORITATIVE_INFORMATION);

    public ErrorResponse() {
    }

    public ErrorResponse(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.msg = httpStatus.getReasonPhrase();
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
}
