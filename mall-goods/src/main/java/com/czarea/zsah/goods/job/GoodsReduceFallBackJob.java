package com.czarea.zsah.goods.job;

import com.alibaba.nacos.client.logger.Logger;
import com.alibaba.nacos.client.logger.LoggerFactory;
import com.czarea.zsah.common.dto.FlashSaleDTO;
import com.czarea.zsah.goods.feign.OrderFeignClient;
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
public class GoodsReduceFallBackJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(GoodsReduceFallBackJob.class);

    private final RedisTemplate<String, Object> redisTemplate;
    private final OrderFeignClient orderFeignClient;

    @Autowired
    public GoodsReduceFallBackJob(RedisTemplate redisTemplate, OrderFeignClient orderFeignClient) {
        this.redisTemplate = redisTemplate;
        this.orderFeignClient = orderFeignClient;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("开始清理！！！");

        Set<Object> timeOutPreOrder = redisTemplate.opsForSet().members("PRE_REDUCE");

        timeOutPreOrder.stream().forEach(key -> {
            String userIdAndGoodsIdAndNumber = (String) key;
            long timeOut = (long) redisTemplate.opsForValue().get(userIdAndGoodsIdAndNumber);
            if (System.currentTimeMillis() >= timeOut) {
                String[] strArray = userIdAndGoodsIdAndNumber.split("_");
                String userId = strArray[0];
                Long goodsId = Long.valueOf(strArray[1]);
                int number = Integer.parseInt(strArray[2]);
                FlashSaleDTO flashSaleDTO = new FlashSaleDTO();
                flashSaleDTO.setGoodsId(goodsId);
                flashSaleDTO.setUserId(Long.valueOf(userId));
                flashSaleDTO.setNumber(number);
                logger.info(userIdAndGoodsIdAndNumber + " 支付超时！");
                orderFeignClient.timeOut(flashSaleDTO);

                //三条命令最好一个事务比较好，有可能线程不安全
                redisTemplate.opsForValue().increment("FS_GOODS_" + flashSaleDTO.getGoodsId(), flashSaleDTO.getNumber());
                redisTemplate.delete(userIdAndGoodsIdAndNumber);
                redisTemplate.opsForSet().remove("PRE_REDUCE", userIdAndGoodsIdAndNumber);
            }
        });

        logger.info("清理完成！！！");
    }
}
