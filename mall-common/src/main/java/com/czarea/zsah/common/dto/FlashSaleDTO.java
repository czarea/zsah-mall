package com.czarea.zsah.common.dto;

/**
 * @author zhouzx
 */
public class FlashSaleDTO {
    private Long orderId;
    private Long userId;
    private Long goodsId;
    private Integer number;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "FlashSaleDTO{" +
            "orderId=" + orderId +
            ", userId=" + userId +
            ", goodsId=" + goodsId +
            ", number=" + number +
            '}';
    }
}
