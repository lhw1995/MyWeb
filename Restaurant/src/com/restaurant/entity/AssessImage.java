package com.restaurant.entity;

import javax.persistence.*;

/**
 * Created by lhw on 2016/12/1.
 */
@Entity
public class AssessImage {
    private int id;
    private Assess assess;
    private String Image;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    public Assess getAssess() {
        return assess;
    }

    public void setAssess(Assess assess) {
        this.assess = assess;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
