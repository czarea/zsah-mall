package com.czarea.zsah.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czarea.zsah.goods.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author zhoux
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    @Update("update t_goods t set t.store = t.store + #{number} where t.id=#{goodsId} and t.version=#{version}")
    int preReduceRollBack(@Param("number") Integer number,@Param("goodsId") Long goodsId,@Param("version") Integer version) ;

}
