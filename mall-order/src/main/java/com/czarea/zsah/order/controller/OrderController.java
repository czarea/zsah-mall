package com.czarea.zsah.order.controller;

import com.czarea.zsah.order.entity.Order;
import com.czarea.zsah.order.service.OrderService;
import com.czarea.zsah.common.vo.Response;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouzx
 */
@RestController("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/pre")
    public Response<Void> preOrder(@RequestBody Order order) {
        return orderService.preOrder(order);
    }

    @PostMapping("/commit/{id}")
    public Response<Void> order(@PathVariable("id") Long orderId) {
        return orderService.commit(orderId);
    }

    @PostMapping("/timeOut/{id}")
    public Response<Void> timeOut(@PathVariable("id") Long orderId) {
        return orderService.timeOut(orderId);
    }
}
