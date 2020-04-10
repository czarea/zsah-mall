package com.czarea.zsah.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czarea.zsah.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author zhoux
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 订单超时
     *
     * @param orderId 订单号
     */
    @Update("update t_order t set t.status=4 where id = #{id}")
    void timeOut(@Param("id") Long orderId);
}
