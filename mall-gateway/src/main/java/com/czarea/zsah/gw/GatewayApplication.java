package com.czarea.zsah.gw;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

/**
 * 网关启动类
 *
 * @author zhouzx
 */
@SpringCloudApplication
@EnableRedisWebSession(redisNamespace = "s:cloud", redisFlushMode = RedisFlushMode.IMMEDIATE)
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
