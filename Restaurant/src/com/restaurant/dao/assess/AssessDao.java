package com.restaurant.dao.assess;

import com.restaurant.entity.Assess;
import com.restaurant.entity.AssessImage;
import com.restaurant.entity.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by lhw on 2016/12/31.
 */

public interface AssessDao {
    //保存评价
    void saveAssess(Assess assess);
    //保存评价图
    void saveAssessImg(AssessImage assessImage);
    //获得评价
    Map<String,Object> getAssess(int dishId);
    //保存评分
    void updateScore(Item item);
}
