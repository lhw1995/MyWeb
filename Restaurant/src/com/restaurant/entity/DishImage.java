package com.restaurant.entity;

import javax.persistence.*;

/**
 * Created by lhw on 2016/12/1.
 */
@Entity
public class DishImage {
    private int id;
    private Dish dish;
    private String image;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
