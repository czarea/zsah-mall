package com.czarea.zsah.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czarea.zsah.goods.entity.Goods;
import com.czarea.zsah.common.dto.FlashSaleDTO;
import com.czarea.zsah.common.vo.Response;

/**
 * @author zhouzx
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 减库存
     */
    Response reduceGoods(FlashSaleDTO flashSaleDTO);

    /**
     * 预减库存
     */
    Response preReduce(FlashSaleDTO flashSaleDTO);

    /**
     * 超时未支付新增库存
     */
    Response preReduceRollback(FlashSaleDTO flashSaleDTO);

    Response secKill(FlashSaleDTO flashSaleDTO);
}
