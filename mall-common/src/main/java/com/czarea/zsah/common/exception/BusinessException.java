package com.czarea.zsah.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 业务异常类
 *
 * @author zhouzx
 */
public class BusinessException extends RuntimeException {

    private Integer code = 500;
    private String reason;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.reason = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause,
        boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BusinessException(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase());
        this.code = httpStatus.value();
        this.reason = httpStatus.getReasonPhrase();
    }

    public BusinessException(HttpStatus httpStatus, Throwable cause) {
        super(httpStatus.getReasonPhrase(), cause);
        this.code = httpStatus.value();
        this.reason = httpStatus.getReasonPhrase();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
