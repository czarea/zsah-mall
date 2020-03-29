package com.czarea.zsah.goods.job;

import com.alibaba.nacos.client.logger.Logger;
import com.alibaba.nacos.client.logger.LoggerFactory;
import com.czarea.zsah.common.dto.FlashSaleDTO;
import com.czarea.zsah.goods.feign.OrderFeignClient;
import com.czarea.zsah.goods.mapper.GoodsMapper;
import com.czarea.zsah.goods.service.GoodsService;
import java.util.Set;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 分布式定时任务清理过期未支付的抢购
 *
 * @author zhouzx
 */
public class GoodsReduceFallback implements Job {

    private static final Logger logger = LoggerFactory.getLogger(GoodsReduceFallback.class);

    private final RedisTemplate<String, Object> redisTemplate;
    private final GoodsService goodsService;
    private final OrderFeignClient orderFeignClient;

    @Autowired
    public GoodsReduceFallback(RedisTemplate redisTemplate, GoodsMapper goodsMapper,
        GoodsService goodsService, OrderFeignClient orderFeignClient) {
        this.redisTemplate = redisTemplate;
        this.goodsService = goodsService;
        this.orderFeignClient = orderFeignClient;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("开始清理！！！");
        long begin = System.nanoTime() - 60 * 3 * 1000 - 30;
        long end = System.nanoTime() - 60 * 3 * 1000;
        Set<Object> timeOutPreOrder = redisTemplate.opsForZSet().rangeByScore("REDUCE_GOODS", begin, end);

        timeOutPreOrder.stream().forEach(key -> {
            String goodsIdAndOrderIdAndNumber = (String) key;
            String[] strArray = goodsIdAndOrderIdAndNumber.split("_");
            Long goodsId = Long.valueOf(strArray[0]);
            String orderId = strArray[1];
            int number = Integer.parseInt(strArray[2]);
            FlashSaleDTO flashSaleDTO = new FlashSaleDTO();
            flashSaleDTO.setGoodsId(goodsId);
            flashSaleDTO.setNumber(number);
            goodsService.preReduceRollback(flashSaleDTO);
            orderFeignClient.timeOut(Long.parseLong(orderId));
        });
        redisTemplate.opsForZSet().remove("REDUCE_GOODS", timeOutPreOrder);

        logger.info("清理完成！！！");
    }
}
