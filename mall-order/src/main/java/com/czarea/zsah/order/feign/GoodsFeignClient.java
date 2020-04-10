package com.czarea.zsah.order.feign;

import com.czarea.zsah.order.feign.fallback.GoodsFeignClientFallBack;
import com.czarea.zsah.common.constant.MicroServices;
import com.czarea.zsah.common.dto.FlashSaleDTO;
import com.czarea.zsah.common.vo.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zhouzx
 */
@FeignClient(value = MicroServices.GOODS_SERVICE, fallback = GoodsFeignClientFallBack.class)
public interface GoodsFeignClient {

    @PostMapping("/goods/prd")
    Response preReduce(@RequestBody FlashSaleDTO flashSaleDTO);

    @PostMapping("/goods/rdg")
    Response reduceGoods(@RequestBody FlashSaleDTO flashSaleDTO);

    @PostMapping("/goods/prr")
    Response preReduceRollback(@RequestBody FlashSaleDTO flashSaleDTO);

    @PostMapping("/goods/seckill")
    Response secKill(@RequestBody FlashSaleDTO flashSale);
}
