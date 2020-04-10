package com.czarea.zsah.order.feign.fallback;

import com.czarea.zsah.order.feign.GoodsFeignClient;
import com.czarea.zsah.common.dto.FlashSaleDTO;
import com.czarea.zsah.common.vo.Response;
import org.springframework.stereotype.Component;

/**
 * @author zhouzx
 */
@Component
public class GoodsFeignClientFallBack implements GoodsFeignClient {

    @Override
    public Response preReduce(FlashSaleDTO flashSaleDTO) {
        return Response.SERVICE_FALL_BACK;
    }

    @Override
    public Response reduceGoods(FlashSaleDTO flashSaleDTO) {
        return Response.SERVICE_FALL_BACK;
    }

    @Override
    public Response preReduceRollback(FlashSaleDTO flashSaleDTO) {
        return Response.SERVICE_FALL_BACK;
    }

    @Override
    public Response secKill(FlashSaleDTO flashSale) {
        return Response.SERVICE_FALL_BACK;
    }
}
