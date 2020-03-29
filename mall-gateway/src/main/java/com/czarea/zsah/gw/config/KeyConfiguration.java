package com.czarea.zsah.gw.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author zhouzx
 */
@Configuration
public class KeyConfiguration {

    /**
     * 前端nginx 会设置head ip
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(
            exchange.getRequest()
                .getHeaders()
                .getFirst("X-Forwarded-For")
        );
    }
}
