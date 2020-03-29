package com.czarea.zsah.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czarea.zsah.goods.entity.Goods;
import com.czarea.zsah.goods.mapper.GoodsMapper;
import com.czarea.zsah.goods.service.GoodsService;
import com.czarea.zsah.common.dto.FlashSaleDTO;
import com.czarea.zsah.common.vo.Response;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhouzx
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService, CommandLineRunner {

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
                    String key = flashSaleDTO.getUserId() + "_" + flashSaleDTO.getGoodsId() + "_" + flashSaleDTO.getNumber();
                    redisTemplate.delete(key);
                    redisTemplate.opsForSet().remove("PRE_REDUCE", key);
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
        String key = flashSaleDTO.getUserId() + "_" + flashSaleDTO.getGoodsId() + "_" + flashSaleDTO.getNumber();
        if (redisTemplate.opsForValue().get(key) != null) {
            return new Response(600, "已经抢购，不能重复抢购，请快速支付！");
        }

        long number = redisTemplate.opsForValue().decrement("FS_GOODS_" + flashSaleDTO.getGoodsId(), flashSaleDTO.getNumber());
        if (number < 0) {
            redisTemplate.opsForValue().increment("FS_GOODS_" + flashSaleDTO.getGoodsId(), flashSaleDTO.getNumber());
            return new Response(601, "库存不足啦！！！");
        }
        redisTemplate.opsForSet().add("PRE_REDUCE", key);
        redisTemplate.opsForValue().set(key, System.currentTimeMillis() + 60000);
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

    /**
     * 简化，启动的时候缓存商品信息到redis
     */
    @Override
    public void run(String... args) throws Exception {
        List<Goods> all = Collections.unmodifiableList(baseMapper.selectList(null));
        all.stream().forEach(key -> {
            redisTemplate.opsForValue().set("FS_GOODS_" + key.getId(), key.getStore());
        });
    }
}
