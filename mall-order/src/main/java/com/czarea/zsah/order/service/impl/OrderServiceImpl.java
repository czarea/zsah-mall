package com.czarea.zsah.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czarea.zsah.order.entity.Order;
import com.czarea.zsah.order.feign.GoodsFeignClient;
import com.czarea.zsah.order.mapper.OrderMapper;
import com.czarea.zsah.order.service.OrderService;
import com.czarea.zsah.common.dto.FlashSaleDTO;
import com.czarea.zsah.common.vo.Response;
import org.springframework.stereotype.Service;

/**
 * @author zhouzx
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final GoodsFeignClient goodsFeignClient;

    public OrderServiceImpl(GoodsFeignClient goodsFeignClient) {
        this.goodsFeignClient = goodsFeignClient;
    }

    @Override
    public Response<Void> preOrder(Order order) {
        FlashSaleDTO flashSale = new FlashSaleDTO();
        flashSale.setGoodsId(order.getGoodsId());
        flashSale.setNumber(order.getNumber());
        flashSale.setUserId(order.getUserId());
        Response res = goodsFeignClient.preReduce(flashSale);
        //状态为初始化
        order.setStatus(0);

        if (res.getCode() == 200) {
            baseMapper.insert(order);
            return Response.SUCCESS;
        }

        return res;
    }

    @Override
    public Response<Void> commit(Long orderId) {
        FlashSaleDTO flashSale = new FlashSaleDTO();
        Order order = baseMapper.selectById(orderId);
        flashSale.setNumber(order.getNumber());
        flashSale.setUserId(order.getUserId());
        flashSale.setGoodsId(order.getGoodsId());
        Response res = goodsFeignClient.reduceGoods(flashSale);
        if (res.isSuccess()) {
            //设置为完成状态
            order.setStatus(2);
            baseMapper.updateById(order);
            return Response.SUCCESS;
        } else {
            order.setStatus(3);
            return res;
        }
    }

    @Override
    public Response<Void> timeOut(FlashSaleDTO flashSaleDTO) {
        Order order = new Order();
        order.setUserId(flashSaleDTO.getUserId());
        order.setGoodsId(flashSaleDTO.getGoodsId());
        order.setNumber(flashSaleDTO.getNumber());
        Wrapper<Order> query = Wrappers.query(order);

        Order uOrder = new Order();
        uOrder.setStatus(4);
        baseMapper.update(uOrder, query);
        return Response.SUCCESS;
    }
}
