package com.czarea.zsah.gw.config;

import com.czarea.zsah.gw.filter.RequestTimeFilter;
import com.czarea.zsah.gw.filter.TokenFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouzx
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public GatewayFilter tokenFilter() {
        return new TokenFilter();
    }

    @Bean
    public RequestTimeFilter timeFilter() {
        return new RequestTimeFilter();
    }
}
