package com.czarea.zsah.common.config;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

import com.czarea.zsah.common.exception.BusinessException;
import com.czarea.zsah.common.vo.ErrorResponse;
import com.czarea.zsah.common.vo.Response;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类 将业务异常以json的格式返回
 *
 * @author zhouzx
 */
@RestControllerAdvice
@ConditionalOnWebApplication(type = SERVLET)
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)
    public ErrorResponse bizExceptionHandler(BusinessException e, HttpServletResponse response) {
        logger.error("", e);
        ErrorResponse exceptionResponse = new ErrorResponse();
        exceptionResponse.setMsg(e.getReason());
        exceptionResponse.setCode(e.getCode());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return exceptionResponse;
    }

    @ExceptionHandler(value = Exception.class)
    public ErrorResponse allExceptionHandler(Exception e) {
        logger.error(e.getMessage(), e);
        ErrorResponse exceptionResponse = new ErrorResponse();
        exceptionResponse.setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        exceptionResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return exceptionResponse;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response exception(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();
        for (FieldError error : fieldErrors) {
            builder.append(error.getDefaultMessage()).append("\n");
        }
        logger.error("", exception);
        return new Response<>(HttpStatus.BAD_REQUEST.value(), builder.toString());
    }

}
