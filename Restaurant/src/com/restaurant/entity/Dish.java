package com.restaurant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lhw on 2016/12/1.
 */
@Entity
public class Dish {
    private int id;
    private String dishName;    //菜名
    private float price;    //价格
    private int count;      //销量
    private int inventory;  //库存
    private String classify;    //类型
    private float score;        //评分
    private String cover;       //封面

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishName='" + dishName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Dish dish = (Dish)obj;
        if (dish.getId() == this.id && dish.getDishName().equals(this.getDishName())) {
            return true;
        }
        return false;
    }
}
