package com.restaurant.dao.recommend;

import com.restaurant.entity.Consumer;
import com.restaurant.entity.Dish;

import java.util.Map;

/**
 * Created by lhw on 2017/1/2.
 */
public interface RecommendDao {
    //用户点过的菜的评分
    Map<Integer,Double> getScore(Consumer consumer);
}
