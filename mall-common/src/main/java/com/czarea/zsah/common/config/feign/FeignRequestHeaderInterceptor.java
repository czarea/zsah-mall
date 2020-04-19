package com.czarea.zsah.common.config.feign;

import com.czarea.zsah.common.hystrix.HttpHeadersContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;

/**
 * feign 传递Request header
 *
 * <p>
 * https://blog.csdn.net/u014519194/article/details/77160958
 * https://github.com/Netflix/Hystrix/issues/92#issuecomment-260548068
 * </p>
 *
 * @author zhouzx
 */
public class FeignRequestHeaderInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpHeaders headers = HttpHeadersContextHolder.get();
        if (headers != null && !headers.isEmpty()) {
            headers.forEach((key, values) -> {
                values.forEach(value -> requestTemplate.header(key, value));
            });
        }
    }

}
