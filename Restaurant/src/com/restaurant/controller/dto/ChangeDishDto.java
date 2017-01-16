package com.restaurant.controller.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lhw on 2016/12/22.
 */
public class ChangeDishDto {
    private String dishName;
    private float price;
    private int inventory;
    private String classify;
    private MultipartFile cover;

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public MultipartFile getCover() {
        return cover;
    }

    public void setCover(MultipartFile cover) {
        this.cover = cover;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
