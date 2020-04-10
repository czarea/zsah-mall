package com.czarea.zsah.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czarea.zsah.common.dto.FlashSaleDTO;
import com.czarea.zsah.order.entity.Order;
import com.czarea.zsah.common.vo.Response;

/**
 * @author zhouzx
 */
public interface OrderService extends IService<Order> {

    Response<Void> preOrder(Order order);

    Response<Void> commit(Long orderId);

    Response<Void> timeOut(FlashSaleDTO flashSaleDTO);

    Response<Void> secKill(Order order);
}
