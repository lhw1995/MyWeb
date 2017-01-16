package com.restaurant.controller.dto;

import com.restaurant.entity.Assess;
import com.restaurant.entity.AssessImage;
import com.restaurant.entity.Consumer;

import java.util.List;

/**
 * Created by lhw on 2016/12/31.
 */
public class MultiAssessDto {
    private Consumer consumer;
    private Assess assess;
    private List<AssessImage> assessImageList;

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Assess getAssess() {
        return assess;
    }

    public void setAssess(Assess assess) {
        this.assess = assess;
    }

    public List<AssessImage> getAssessImageList() {
        return assessImageList;
    }

    public void setAssessImageList(List<AssessImage> assessImageList) {
        this.assessImageList = assessImageList;
    }
}
