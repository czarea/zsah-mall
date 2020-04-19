package com.czarea.zsah.common.config.feign;

import com.czarea.zsah.common.util.JsonUtil;
import com.czarea.zsah.common.util.ObjectUtil;
import com.czarea.zsah.common.vo.Response;
import com.fasterxml.jackson.databind.JsonNode;
import feign.FeignException;
import java.lang.reflect.Method;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.lang.Nullable;

/**
 * fallBack 代理处理
 *
 * @author zhouzx
 */
@Slf4j
@AllArgsConstructor
public class MyFeignFallback<T> implements MethodInterceptor {

    private static final String CODE = "code";

    private final Class<T> targetType;
    private final String targetName;
    private final Throwable cause;

    @Nullable
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String errorMessage = cause.getMessage();
        log.error("BladeFeignFallback:[{}.{}] serviceId:[{}] message:[{}]", targetType.getName(), method.getName(), targetName,
            errorMessage);
        Class<?> returnType = method.getReturnType();
        if (Response.class != returnType) {
            return null;
        }
        // 非 FeignException
        if (!(cause instanceof FeignException)) {
            return Response.fail(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
        }
        FeignException exception = (FeignException) cause;
        byte[] content = exception.content();
        // 如果返回的数据为空
        if (ObjectUtil.isEmpty(content)) {
            return Response.fail(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
        }

        JsonNode resultNode = JsonUtil.readTree(content);
        // 判断是否 R 格式 返回体
        if (resultNode.has(CODE)) {
            return JsonUtil.getInstance().convertValue(resultNode, Response.class);
        }
        return Response.fail(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, resultNode.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyFeignFallback<?> that = (MyFeignFallback<?>) o;
        return targetType.equals(that.targetType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetType);
    }
}
