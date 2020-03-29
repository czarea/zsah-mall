package com.czarea.zsah.goods.feign.impl;

import com.czarea.zsah.common.vo.Response;
import com.czarea.zsah.goods.feign.OrderFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author zhouzx
 */
@Component
public class OrderFeignClientFallBack implements OrderFeignClient {

    @Override
    public Response timeOut(long orderId) {
        return Response.SERVICE_FALL_BACK;
    }
}
