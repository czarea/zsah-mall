package com.czarea.zsah.gw.config;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import reactor.core.publisher.Mono;

/**
 * @author zhouzx
 */
@Configuration
public class KeyConfiguration {


    /**
     * 前端nginx 会设置head ip
     * 本地开发没有使用nginx，直接是ip
     */
    @Bean
    public KeyResolver ipKeyResolver(ConfigurableEnvironment env) {
        String profiles = Arrays.stream(env.getActiveProfiles()).collect(Collectors.joining());
        if (profiles.contains("dev")) {
            return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
        } else {
            return exchange -> Mono.just(
                exchange.getRequest()
                    .getHeaders()
                    .getFirst("X-Forwarded-For")
            );
        }
    }

}
