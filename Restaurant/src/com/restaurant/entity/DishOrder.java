package com.restaurant.entity;

import javax.persistence.*;

/**
 * Created by lhw on 2016/12/1.
 */
@Entity
public class DishOrder implements Comparable<DishOrder>{
    private int id;
    private Consumer consumer;
    private float price;
    private String time;
    private String status;  //客户收货状态
    private String deliver; //餐厅发货状态

    @Override
    public int compareTo(DishOrder o) {
        return o.getId() - getId();
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getId());
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }
}
