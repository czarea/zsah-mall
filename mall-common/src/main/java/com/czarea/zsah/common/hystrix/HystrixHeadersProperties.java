package com.czarea.zsah.common.hystrix;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

/**
 * Hystrix Headers 配置
 *
 * @author zhouzx
 */
@Getter
@Setter
@RefreshScope
@Configuration
@ConfigurationProperties("hystrix.headers")
public class HystrixHeadersProperties {

    /**
     * 用于 聚合层 向调用层传递用户信息 的请求头，默认：x-blade-account
     */
    private String account = "X-Blade-Account";

    /**
     * RestTemplate 和 Fegin 透传到下层的 Headers 名称表达式
     */
    @Nullable
    private String pattern = "cloud*";

    /**
     * RestTemplate 和 Fegin 透传到下层的 Headers 名称列表
     */
    private List<String> allowed = Arrays
        .asList("X-Real-IP", "x-forwarded-for", "authorization", "cloud-auth", "Authorization");

}
