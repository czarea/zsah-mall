package com.czarea.zsah.goods.feign;

import com.czarea.zsah.common.constant.MicroServices;
import com.czarea.zsah.common.vo.Response;
import com.czarea.zsah.goods.feign.impl.OrderFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author zhouzx
 */
@FeignClient(value = MicroServices.GOODS_SERVICE, fallback = OrderFeignClientFallBack.class)
public interface OrderFeignClient {

    @PostMapping("/timeOut/{id}")
    Response timeOut(@PathVariable("id") long orderId);
}
