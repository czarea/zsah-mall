package com.czarea.zsah.order;

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
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }


    @GetMapping("/test")
    public String test() throws InterruptedException {
        System.out.println("test.....");
        TimeUnit.MILLISECONDS.sleep(1500);
        return "sleep some seconds";
    }

}
