package com.czarea.zsah.goods;

import java.util.concurrent.TimeUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouzx
 */
@RestController
@EnableFeignClients
@SpringCloudApplication
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }


    @GetMapping("/test")
    public String test() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1500);
        return "sleep some seconds";
    }

}
