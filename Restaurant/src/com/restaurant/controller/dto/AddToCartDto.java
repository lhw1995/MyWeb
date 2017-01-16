package com.restaurant.controller.dto;

/**
 * Created by lhw on 2016/12/25.
 */
public class AddToCartDto {
    private String dishId;
    private String buyCount;

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(String buyCount) {
        this.buyCount = buyCount;
    }
}
