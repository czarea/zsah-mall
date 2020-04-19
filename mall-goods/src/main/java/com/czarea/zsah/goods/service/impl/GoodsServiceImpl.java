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
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

/**
 * @author zhouzx
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService, CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

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

    @Override
    public Response secKill(FlashSaleDTO flashSaleDTO) throws InterruptedException {
        long sleeps = RandomUtils.nextLong(910, 1002);
        Thread.sleep(sleeps);

        logger.info("next........... {}", sleeps);

        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("seckill.lua")));

        Long result = (Long) redisTemplate.execute(
            script,
            Collections.singletonList(flashSaleDTO.getGoodsId() + ""), "" + flashSaleDTO.getUserId());

        if (result == 1) {
            return Response.SUCCESS;
        }
        return Response.fail(1000, "抢购失败失败！");
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
