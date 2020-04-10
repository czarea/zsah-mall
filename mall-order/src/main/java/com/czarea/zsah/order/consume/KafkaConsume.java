package com.czarea.zsah.order.consume;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.czarea.zsah.order.entity.Order;
import com.czarea.zsah.order.mapper.OrderMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zhouzx
 */
@Component
public class KafkaConsume {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsume.class);

    private final OrderMapper orderMapper;

    public KafkaConsume(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @KafkaListener(topics = "topic1", groupId = "test")
    public void listen(ConsumerRecord record) {
        logger.info("receive a msg {}", record.value());
        Order order = JSON.parseObject(record.value().toString(), Order.class);
        orderMapper.insert(order);
    }
}
