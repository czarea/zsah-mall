package com.czarea.zsah.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czarea.zsah.goods.entity.Goods;
import com.czarea.zsah.goods.mapper.GoodsMapper;
import com.czarea.zsah.goods.service.GoodsService;
import com.czarea.zsah.common.dto.FlashSaleDTO;
import com.czarea.zsah.common.vo.Response;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhouzx
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    private final RedisTemplate redisTemplate;

    public GoodsServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Response reduceGoods(FlashSaleDTO flashSaleDTO) {
        int updateCnt = 0;
        while (updateCnt == 0) {
            Goods goods = baseMapper.selectById(flashSaleDTO.getGoodsId());
            if (goods.getStore() > 0) {
                goods.setStore(goods.getStore() - flashSaleDTO.getNumber());
                updateCnt = baseMapper.updateById(goods);
                if (updateCnt > 0) {
                    return Response.SUCCESS;
                }
            } else {
                return new Response(-600, "手慢，已经卖完！！！");
            }
        }
        return null;
    }

    @Override
    public Response preReduce(FlashSaleDTO flashSaleDTO) {
        long number = redisTemplate.opsForValue().decrement("FS_GOODS_" + flashSaleDTO.getGoodsId(), flashSaleDTO.getNumber());
        if (number < 0) {
            redisTemplate.opsForValue().increment("FS_GOODS_" + flashSaleDTO.getGoodsId(), flashSaleDTO.getNumber());
            return new Response(601, "库存不足啦！！！");
        }
        long timeOut = System.nanoTime() + 60 * 3 * 1000;
        redisTemplate.opsForZSet().add("REDUCE_GOODS", flashSaleDTO.getGoodsId() + "_" + flashSaleDTO.getOrderId(),timeOut);
        return Response.SUCCESS;
    }

    @Override
    public Response preReduceRollback(FlashSaleDTO flashSaleDTO) {
        int updateCount = 0;
        Goods goods = baseMapper.selectById(flashSaleDTO.getGoodsId());
        while (updateCount == 0) {
            updateCount = baseMapper.preReduceRollBack(flashSaleDTO.getNumber(), flashSaleDTO.getGoodsId(), goods.getVersion());
        }
        return Response.SUCCESS;
    }
}
