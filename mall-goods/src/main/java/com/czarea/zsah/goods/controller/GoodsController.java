package com.czarea.zsah.goods.controller;

import com.czarea.zsah.goods.entity.Goods;
import com.czarea.zsah.goods.service.GoodsService;
import com.czarea.zsah.common.dto.FlashSaleDTO;
import com.czarea.zsah.common.vo.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouzx
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    /**
     * 商品详情
     */
    @GetMapping("/{id}")
    public Goods getGoods(@PathVariable("id") Long id) {
        return goodsService.getById(id);
    }

    /**
     * 真实减库存
     */
    @PostMapping("/rdg")
    public Response reduceGoods(@RequestBody FlashSaleDTO flashSaleDTO) {
        return goodsService.reduceGoods(flashSaleDTO);
    }

    /**
     * 预减库存
     */
    @PostMapping("/prd")
    public Response preReduce(@RequestBody FlashSaleDTO flashSaleDTO) {
        return goodsService.preReduce(flashSaleDTO);
    }

    @PostMapping("/seckill")
    public Response secKill(@RequestBody FlashSaleDTO flashSaleDTO) {
        return goodsService.secKill(flashSaleDTO);
    }

    /**
     * 支付超时回滚新增库存
     */
    @PostMapping("/prr")
    public Response preReduceRollback(@RequestBody FlashSaleDTO flashSaleDTO) {
        return goodsService.preReduceRollback(flashSaleDTO);
    }
}
