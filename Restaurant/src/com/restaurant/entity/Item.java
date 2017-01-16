package com.restaurant.entity;

import javax.persistence.*;

/**
 * Created by lhw on 2016/12/1.
 */
@Entity
public class Item {
    private int id;
    private Dish dish;
    private int count;
    private DishOrder dishOrder;
    private int score;
    private String assess;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @ManyToOne
    @JoinColumn(name = "order_id")
    public DishOrder getDishOrder() {
        return dishOrder;
    }

    public void setDishOrder(DishOrder dishOrder) {
        this.dishOrder = dishOrder;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAssess() {
        return assess;
    }

    public void setAssess(String assess) {
        this.assess = assess;
    }
}
